package org.example.ui_chat_application.home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

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
        // Initialize the contact list and chat list with some dummy data
        contactList.getItems().addAll("Contact 1", "Contact 2", "Contact 3");
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
