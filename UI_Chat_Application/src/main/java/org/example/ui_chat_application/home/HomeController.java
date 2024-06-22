package org.example.ui_chat_application.home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HomeController {
    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> contactList;
    @FXML
    private Button profileButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button newChatButton;
    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() {
        // Initialize the contact list with some dummy data
        contactList = new ListView<>();
        contactList.getItems().addAll("Alice", "Bob", "Charlie", "David");

        // Add event listener for the contact list
        contactList.setOnMouseClicked(event -> handleContactClick(event));
    }

    @FXML
    private void handleProfile() {
        System.out.println("Profile button clicked!");
        // Logic to handle profile viewing
    }

    @FXML
    private void handleSettings() {
        System.out.println("Settings button clicked!");
        // Logic to handle settings
    }

    @FXML
    private void handleNewChat() {
        System.out.println("New Chat button clicked!");
        // Logic to start a new chat
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logout button clicked!");
        // Logic to handle logout
    }

    private void handleContactClick(MouseEvent event) {
        String selectedContact = contactList.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            System.out.println("Selected contact: " + selectedContact);
            // Logic to open chat with the selected contact
        }
    }
}

