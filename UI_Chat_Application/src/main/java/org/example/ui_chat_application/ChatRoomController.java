package org.example.ui_chat_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChatRoomController {
    @FXML
    private TextField tfChat;
    @FXML
    private Button sendButton;
    @FXML
    private Button backButton;

    public void initialize() {

    }
    public void backButtonOnClick(ActionEvent event) {
        try {
            MainApplication.setRoot("home.fxml");
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
