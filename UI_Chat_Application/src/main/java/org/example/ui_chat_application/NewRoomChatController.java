package org.example.ui_chat_application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

import org.example.client.IClient;

public class NewRoomChatController  {
    IClient client;
    private Parent root;
    private Stage stage;
	private Scene scene;

    @FXML
    private TextField chatRoomNameField;

    @FXML
    private Button createButton;

    @FXML
    private Button backButton;

    public void init() {
        // Initialize event handlers for the button
        createButton.setOnMouseClicked(event -> handleCreateButton(event));
        backButton.setOnAction(event -> handleBackButton(event));
    }

    public void setClient(IClient client){
        this.client = client;
        init();
    }

    private void handleCreateButton(MouseEvent event) {
        // Get the chat room name from the text field
        String chatRoomName = chatRoomNameField.getText();

        boolean isRoomCreated = this.client.createRoom(chatRoomName);

        // Placeholder for creating a chat room (you can replace this with actual logic)
        if (chatRoomName != null && !chatRoomName.isEmpty() && isRoomCreated) {
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
            }catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            System.out.println("Chat room name cannot be empty");
        }
    }

    private void handleBackButton(ActionEvent event) {
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
        }catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
