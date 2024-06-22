package org.example.ui_chat_application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

import org.example.client.*;
import org.example.model.*;

public class HomeController {
    IClient client;
    private Parent root;
    private Stage stage;
	private Scene scene;

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
        btnAdd.setOnMouseClicked(event -> handleAddContact(event));
        btnLogout.setOnAction(event -> handleLogout());
        btnChat.setOnAction(event -> handleChat());
    }

    public void setClient(IClient client){
        this.client = client;
    }

    private void handleAddContact(MouseEvent event) {
        // Placeholder for adding a contact (you can replace this with actual logic)
        try {
            // MainApplication.setRoot("new_room_chat.fxml");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("new_room_chat.fxml"));
            root = loader.load();
            NewRoomChatController newRoomChatController = loader.getController();
            newRoomChatController.setClient(client);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
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
