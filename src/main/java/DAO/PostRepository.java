package DAO;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import domain.*;
import exceptions.PostNotFoundException;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Updates.push;

public class PostRepository {
    private final MongoCollection<Document> collection;

    public PostRepository(MongoClient client){
        this.collection = client.getDatabase("blogrdb").getCollection("posts");
    }

    private Document toDoc(Post post){
        var d = new Document();
        d.put("title", post.title());
        d.put("content", post.content());
        d.put("dateCreated", post.dateCreated());
        d.put("lastUpdate", post.lastUpdate());
        d.put("authorId", post.authorId());
        d.put("comments", post.comments());
        d.put("tags", post.tags());
        d.put("reviews", post.reviews());

        return d;
    }

    private Post toDomain(Document d){
        return new Post(
                d.getString("title"),
                d.getString("content"),
                d.getDate("dateCreated"),
                d.getDate("lastUpdate"),
                d.getObjectId("authorId"),
                d.getList("comments", Comment.class),
                d.getList("tags", Tag.class),
                d.getList("reviews", Review.class)
        );
    }

    public void createPost(Post post){
        var doc = toDoc(post);
        InsertOneResult result = collection.insertOne(doc);
        System.out.println("Inserted a document with id: "
                + Objects.requireNonNull(result.getInsertedId())
                .asObjectId().getValue());
    }

    public void getAllPosts(){
        FindIterable<Document> docs = collection.find();
        docs.forEach( doc -> System.out.println("post_id: "
                + doc.getObjectId("_id")
                + " title: "
                + doc.getString("title")));
    }

    public Post getPostById(String id){
        if (!ObjectId.isValid(id)) throw new IllegalArgumentException("Invalid ObjectId format");

        Bson filter = Filters.eq("_id", new ObjectId(id));
        Document doc = collection.find(filter).first();
        if (doc != null)
            return toDomain(doc);

        else throw new PostNotFoundException("Post with ID: " + id + " does not exist!");
    }

    public void getPostsByTag(Tag tag){
        Bson filter = Filters.eq("tags", tag);
        FindIterable<Document> docs = collection.find(filter);
        docs.forEach( doc -> System.out.println("post_id: "
                + doc.getObjectId("_id")
                + " title: "
                + doc.getString("title")

        ));
    }

    public void getPostsByTitle(String title){
        Bson filter = Filters.eq("title", Pattern.compile(Pattern.quote(title), Pattern.CASE_INSENSITIVE));
        FindIterable<Document> docs = collection.find(filter);
        docs.forEach( doc -> System.out.println("post_id: "
                + doc.getObjectId("_id")
                + " title: "
                + doc.getString("title")
        ));
    }

    public void updatePost(String id, String field, String value){
        Bson filter = Filters.eq("_id", new ObjectId(id));
        Bson updates  = Updates.set(field, value);

        UpdateResult result = collection.updateOne(filter, updates);
        System.out.println("Modified fields:" + result.getModifiedCount());
    }

    public void addPostReview(String postId, Review review){
        Bson filter = Filters.eq("_id", new ObjectId(postId));
        UpdateResult result = collection.updateOne(filter, push("reviews", review));

    }

    public void deletePostReview(){

    }

    public void addComment(){

    }

    public void deletePost(){

    }

    public void deleteComment(){

    }
}
