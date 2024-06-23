package org.example.ui_chat_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import org.example.client.IClient;

public class ChatRoomController {
    IClient client;
    private Parent root;
    private Stage stage;
	private Scene scene;

    @FXML
    private TextField tfChat;
    @FXML
    private Button sendButton;
    @FXML
    private Button backButton;

    public void init() {

    }

    public void setClient(IClient client){
        this.client = client;
    }

    public void backButtonOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            root = loader.load();
            HomeController homeController = loader.getController();
            homeController.setClient(client);
            homeController.init();
    
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendButtonOnClick(ActionEvent event) {
        if (tfChat.getText().isBlank() == false) {

        }else {
            System.out.println();
        }
    }
}
