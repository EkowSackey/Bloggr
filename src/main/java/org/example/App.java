package org.example;

import Config.MongoConfig;
import DAO.PostRepository;
import DAO.UserRepository;
import com.mongodb.client.MongoClient;
import domain.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        MongoClient client = MongoConfig.getClient();
//        UserRepository urepo = new UserRepository(client);
//
//        User u = new User("newTestUser", "email@example.com", "password", String.valueOf(Role.REGULAR));
////        urepo.createUser(u);
//        urepo.getAllUsers();
////        System.out.println(urepo.findById("694a784aed1a924700ad81ef"));
////        System.out.println(urepo.findByUsername("testUser"));
////        urepo.deleteUser("694a784aed1a924700ad81ef");
////        urepo.deleteUser("694a814852a9f062f812ea5f");
////        urepo.updateUser("694a9986051efa4540ce1354", "username", "updatedUsername");
////        urepo.deleteUser("694a8bc52b686e4befd60123");
//        System.out.println(urepo.findByUsername("testUser"));

        PostRepository prepo = new PostRepository(client);

        Tag tag1 = new Tag("article");
        Tag tag2 = new Tag("noise");
        List<Tag> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);
//        Post post = new Post("decorated monkeys", "content of post rawr", new Date(), new ObjectId("694a8bc52b686e4befd60123"), new ArrayList<>(), tags, new ArrayList<>() );


//        prepo.createPost(post);
//        prepo.getAllPosts();
//        System.out.println(prepo.getPostById("694aac1b70a63338ce148243"));
//        prepo.getPostsByTag(tag2);

//        prepo.getPostsByTitle("monkey");
        Comment c = new Comment("yayaya", new ObjectId("694a8bb753b86984679fe103"), new ObjectId("695798f3bef8cd4867b182d7"), new ArrayList<>(), new Date());
//        prepo.updatePost("694ab3f4b226de790f645e94", "title", "updated title hurrah!");

//        Review rev = new Review(3.5, new ObjectId("694a8bb753b86984679fe103"), new ObjectId("694ab3f4b226de790f645e94") );
//        prepo.addPostReview(rev.postId().toHexString(), rev);
//        prepo.addSubComment("694aac1b70a63338ce148243",c);
//        prepo.deleteCommentById("694aac1b70a63338ce148243","6957942912052fd2d99b1e8b");
//        prepo.deletePost("694ab23570711a221c1f6950");
//        prepo.getPostsByAuthor("694a8bc52b686e4befd60123");
    }
}
