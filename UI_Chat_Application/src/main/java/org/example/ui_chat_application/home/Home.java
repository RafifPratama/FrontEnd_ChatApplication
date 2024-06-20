package org.example.ui_chat_application.home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            VBox root = loader.load();
            Scene scene = new Scene(root, 400, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Chat Application");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
