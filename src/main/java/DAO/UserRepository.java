package DAO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import domain.Post;
import domain.User;
import exceptions.UserNotFoundException;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository {
    private final MongoCollection<Document> collection;

    public UserRepository(MongoClient client){
        this.collection = client.getDatabase("blogrdb").getCollection("users");
    }

    private Document toDoc(User user){
        var d = new Document();
        d.put("username", user.username());
        d.put("email", user.email());
        d.put("role", user.role());
//        todo: hash password
        d.put("password", user.password());

        return d;
    }

    private User toDomain(Document d){
        return new User(d.getString("username"),
                d.getString("email"),
                d.getString("password"),
                d.getString("role"));
    }

    public void createUser(User user){
        var doc = toDoc(user);
        InsertOneResult result = collection.insertOne(doc);
        System.out.println("Inserted a document with the following id: "
                + Objects.requireNonNull(result.getInsertedId())
                .asObjectId().getValue());
    }

    public List<User> getAllUsers(){
        FindIterable<Document> docs = collection.find();
        List<User> users = new ArrayList<>();
        docs.forEach( doc -> users.add(toDomain(doc)));
        return users;
    }

    public User findById(String id){
        if (!ObjectId.isValid(id)) throw new IllegalArgumentException("Invalid ObjectId format");

        Bson filter = Filters.eq("_id", new ObjectId(id));
        Document doc = collection.find(filter).first();
        if (doc != null)
            return toDomain(doc);
        else throw new UserNotFoundException("User with ID: " + id + " does not exist!");
    }

    public User findByUsername(String username){
        Bson filter = Filters.eq("username", username);
        Document doc = collection.find(filter).first();
        if(doc != null)
            return toDomain(doc);
        else throw new UserNotFoundException("User with username: " + username + " does not exist");
    }

    public void updateUser(String id, String field, String value){
        Bson filter = Filters.eq("_id", new ObjectId(id));
        Bson update = Updates.set(field, value);

        UpdateResult result = collection.updateOne(filter, update);
        System.out.println("Modified fields:" + result.getModifiedCount());
    }

    public void deleteUser(String id){
        User u = findById(id);

        if (u != null)
            collection.deleteOne(toDoc(u));
        else throw new UserNotFoundException("User with ID: " + id + " does not exist");
    }
}
