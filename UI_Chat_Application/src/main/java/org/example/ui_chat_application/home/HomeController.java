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
    private ListView<String> chatList;
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
        // Initialize the chat list with some dummy data
        chatList.getItems().addAll("Chat with Alice", "Chat with Bob", "Chat with Charlie", "Chat with David");

        // Add event listener for the chat list
        chatList.setOnMouseClicked(this::handleChatClick);
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

    private void handleChatClick(MouseEvent event) {
        String selectedChat = chatList.getSelectionModel().getSelectedItem();
        if (selectedChat != null) {
            System.out.println("Selected chat: " + selectedChat);
            // Logic to open chat with the selected contact
        }
    }
}