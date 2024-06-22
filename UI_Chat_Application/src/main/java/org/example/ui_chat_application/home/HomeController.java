package org.example.ui_chat_application.home;

import java.io.IOException;

import org.example.ui_chat_application.MainApplication;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

import org.example.client.*;
import org.example.model.*;

public class HomeController {
    IClient client;

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

    public void init() {
        IClient client = new Client();
        ArrayList<Room> alRoom = client.listAllRooms();
    
        for (int i = 0; i < alRoom.size(); i++) {
            contactList.getItems().add(alRoom.get(i).getName());
        }
        contactList.setOnMouseClicked(event -> handleChatClick(event, client, alRoom));

        // Add event handlers for buttons
        btnAdd.setOnAction(event -> handleAddContact());
        btnLogout.setOnAction(event -> handleLogout());
        btnChat.setOnAction(event -> handleChat());
    }

    public void setClient(IClient client){
        this.client = client;
        init();
    }

    private void handleAddContact() {
        // Placeholder for adding a contact (you can replace this with actual logic)
        try {
            MainApplication.setRoot("new_room_chat.fxml");
        }catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void handleChatClick(MouseEvent event, IClient client, ArrayList<Room> alRoom) {
        String selectedChat = contactList.getSelectionModel().getSelectedItem();
        if (selectedChat != null) {
            chatList.getItems().clear();
            int selectedIdx = contactList.getSelectionModel().getSelectedIndex();
            ArrayList<User> alUserPerRoom = client.listAllMembersInTheRoom(alRoom.get(selectedIdx).getId());
            for (int i = 0; i < alUserPerRoom.size(); i++) {
                // System.out.println(alUserPerRoom.get(i).getName());
                chatList.getItems().add(alUserPerRoom.get(i).getName());
            }
            // Logic to open chat with the selected contact
        }
    }

    private void handleLogout() {
        // Placeholder for logout logic (you can replace this with actual logic)
        try {
            MainApplication.setRoot("login.fxml");
        }catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void handleChat() {
        // Placeholder for starting a chat (you can replace this with actual logic)
        if (btnChat.getText().equals("Join")) {
            joinButtonOnClick();
        }else if (btnChat.getText().equals("Chat")) {
            chatButtonOnClick();
        }
    }

    private void joinButtonOnClick() {

    }

    private void chatButtonOnClick() {
        try {
            MainApplication.setRoot("chat_room.fxml");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
