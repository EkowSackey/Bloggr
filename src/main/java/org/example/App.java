package org.example;

import Config.MongoConfig;
import DAO.UserRepository;
import com.mongodb.client.MongoClient;
import domain.Role;
import domain.User;

public class App 
{
    public static void main( String[] args )
    {
        MongoClient client = MongoConfig.getClient();
        UserRepository urepo = new UserRepository(client);

        User u = new User("newTestUser", "email@example.com", "password", String.valueOf(Role.REGULAR));
//        urepo.createUser(u);
        urepo.getAllUsers();
//        System.out.println(urepo.findById("694a784aed1a924700ad81ef"));
//        System.out.println(urepo.findByUsername("testUser"));
//        urepo.deleteUser("694a784aed1a924700ad81ef");
//        urepo.deleteUser("694a814852a9f062f812ea5f");
//        urepo.updateUser("694a9986051efa4540ce1354", "username", "updatedUsername");
//        urepo.deleteUser("694a8bc52b686e4befd60123");
        System.out.println(urepo.findByUsername("testUser"));


    }
}
