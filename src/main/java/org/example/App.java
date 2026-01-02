package org.example;

import Config.MongoConfig;
import DAO.PostRepository;
import DAO.UserRepository;
import com.mongodb.client.MongoClient;
import domain.*;
import javafx.application.Application;
import javafx.stage.Stage;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader loader = new FXMLLoader();
    }
}
