package org.example.ui_chat_application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

public class NewRoomChatController  {

    @FXML
    private TextField chatRoomNameField;

    @FXML
    private Button createButton;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        // Initialize event handlers for the button
        createButton.setOnAction(event -> handleCreateButton());
        backButton.setOnAction(event -> handleBackButton(event));
    }

    private void handleCreateButton() {
        // Get the chat room name from the text field
        String chatRoomName = chatRoomNameField.getText();

        // Placeholder for creating a chat room (you can replace this with actual logic)
        if (chatRoomName != null && !chatRoomName.isEmpty()) {
            try {
                MainApplication.setRoot("home.fxml");
            }catch (IOException e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            System.out.println("Chat room name cannot be empty");
        }
    }

    private void handleBackButton(ActionEvent event) {
        try {
            MainApplication.setRoot("home.fxml");
        }catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
