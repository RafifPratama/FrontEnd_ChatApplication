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
    Room selectedRoom;
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
        ArrayList<Room> alRoom = client.listAllRooms();
    
        for (int i = 0; i < alRoom.size(); i++) {
            contactList.getItems().add(alRoom.get(i).getName());
        }
        contactList.setOnMouseClicked(event -> handleChatClick(event, client, alRoom));

        // Add event handlers for buttons
        btnAdd.setOnMouseClicked(event -> handleAddContact(event));
        btnLogout.setOnMouseClicked(event -> handleLogout(event));
        btnChat.setOnMouseClicked(event -> handleChat(event));
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
            this.selectedRoom = alRoom.get(selectedIdx);
            ArrayList<User> alUserPerRoom = client.listAllMembersInTheRoom(alRoom.get(selectedIdx).getId());
            // btnChat.setOnMouseClicked(event -> handleChat(event));
            for (int i = 0; i < alUserPerRoom.size(); i++) {
                chatList.getItems().add(alUserPerRoom.get(i).getName());
                Integer roomId = alRoom.get(selectedIdx).getId();
                swapChatJoin(this.client.isMemberInside(roomId));
            }
        }
    }

    private void swapChatJoin(boolean flag){
        if(flag){
            btnChat.setText("Chat");
            return;
        }
        btnChat.setText("Join");
    }

    private void handleLogout(MouseEvent event) {
        // Placeholder for logout logic (you can replace this with actual logic)
        try {
            // MainApplication.setRoot("login.fxml");
            this.client.logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            root = loader.load();
            // NewRoomChatController newRoomChatController = loader.getController();
            // newRoomChatController.setClient(client);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void handleChat(MouseEvent event) {
        if (btnChat.getText().equals("Join")) {
            joinButtonOnClick(event);
        }else if (btnChat.getText().equals("Chat")) {
            chatButtonOnClick(event);
        }
    }

    private void joinButtonOnClick(MouseEvent event) {
        this.client.joinRoom(this.selectedRoom.getName());
        try{
            ArrayList<User> alUserPerRoom = client.listAllMembersInTheRoom(selectedRoom.getId());
            chatList.getItems().clear();
            for (int i = 0; i < alUserPerRoom.size(); i++) {
                chatList.getItems().add(alUserPerRoom.get(i).getName());
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void chatButtonOnClick(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chat_room.fxml"));
            root = loader.load();
            ChatRoomController chatRoomController = loader.getController();
            chatRoomController.setClientAndRoom(client, this.selectedRoom);
    
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
