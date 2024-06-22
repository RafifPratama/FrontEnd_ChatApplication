package org.example.ui_chat_application.home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import org.example.client.*;
import org.example.model.*;

public class HomeController {

    @FXML
    private ListView<String> contactList;

    @FXML
    private ListView<String> chatList;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnChat;

    @FXML
    public void initialize() {
        IClient client = new Client();
        ArrayList<Room> alRoom = client.listAllRooms();
        // Add all available rooms to contactList
        for (int i = 0; i < alRoom.size(); i++) {
            contactList.getItems().add(alRoom.get(i).getName());
        }
        // contactList.getItems().addAll("Contact 1", "Contact 2", "Contact 3");
        chatList.getItems().addAll("Member 1", "Member 2", "Member 3");

        // Add event handlers for buttons
        btnAdd.setOnAction(event -> handleAddContact());
        btnLogout.setOnAction(event -> handleLogout());
        btnChat.setOnAction(event -> handleChat());
    }

    private void handleAddContact() {
        // Placeholder for adding a contact (you can replace this with actual logic)
        contactList.getItems().add("New Contact");
    }

    private void handleLogout() {
        // Placeholder for logout logic (you can replace this with actual logic)
        System.out.println("Logged out");
    }

    private void handleChat() {
        // Placeholder for starting a chat (you can replace this with actual logic)
        String selectedContact = contactList.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            chatList.getItems().add("Chat with " + selectedContact);
        } else {
            System.out.println("No contact selected");
        }
    }
}
