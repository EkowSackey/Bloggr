package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertManyResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("blogrdb");
            MongoCollection<Document> collection = database.getCollection("examplellection");

            List<Document> documents = new ArrayList<>();
            Document doc1 = new Document("color", "red").append("qty", 5);
            Document doc2 = new Document("color", "purple").append("qty", 40);

            documents.add(doc1);
            documents.add(doc2);

            InsertManyResult res = collection.insertMany(documents);

            List<ObjectId> inserteds = new ArrayList<>();
            res.getInsertedIds().values()
                    .forEach(doc -> inserteds.add(doc.asObjectId().getValue()));

            System.out.println(inserteds);
        }
    }
}
