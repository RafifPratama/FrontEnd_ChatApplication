package org.example.ui_chat_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import org.example.client.IClient;
import org.example.model.Chat;
import org.example.model.Room;

public class ChatRoomController {
    IClient client;
    Room room;
    private Parent root;
    private Stage stage;
	private Scene scene;

    @FXML
    private TextField tfChat;
    @FXML
    private Button sendButton;
    @FXML
    private Button backButton;
    @FXML
    private VBox vboxMassage;
    @FXML
    private Label chatRoomName;

    public void init() {
        chatRoomName.setText(room.getName());
        ArrayList<Chat> alChatPerRoom = this.client.listAllChatsInTheRoom(this.room.getId());
        for (int i = 0; i < alChatPerRoom.size(); i++) {
            if(alChatPerRoom.get(i).getSenderId().equals(client.getClientId())){
                loadMyChat(alChatPerRoom.get(i).getChats());
            }
            else{
                loadOtherUserChat(alChatPerRoom.get(i).getChats(), alChatPerRoom.get(i).getSender_name());
            }
        }
    }

    public void setClientAndRoom(IClient client, Room room){
        this.client = client;
        this.room = room;
    }

    public void backButtonOnClick(ActionEvent event) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendButtonOnClick(ActionEvent event) {
        if (tfChat.getText().isBlank() == false) {
            this.client.sendMessage(tfChat.getText(), this.room.getName());
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5, 5, 5, 10));

            Text text = new Text(tfChat.getText());
            TextFlow textFlow = new TextFlow(text);

            textFlow.setStyle("-fx-color: rgb(255,255,255);" +
                    "-fx-background-color: rgb(15,152,242);" +
                    "-fx-background-radius: 20px;");
            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(Color.color(0.934, 0.945, 0.996));

            hBox.getChildren().add(textFlow);
            vboxMassage.getChildren().add(hBox);
            tfChat.clear();
        }else {
            System.out.println();
        }
    }

    public void loadMyChat(String message){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-color: rgb(255,255,255);" +
                "-fx-background-color: rgb(15,152,242);" +
                "-fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        text.setFill(Color.color(0.934, 0.945, 0.996));

        hBox.getChildren().add(textFlow);
        vboxMassage.getChildren().add(hBox);
    }

    public void loadOtherUserChat(String textMassage, String sender_name) {
        VBox vBox = new VBox();
        Text senderText = new Text(sender_name);
        TextFlow senderTextFlow = new TextFlow(senderText);
        senderText.setFill(Color.color(0.1, 0.1, 0.1));
        senderTextFlow.setPadding(new Insets(5,10,0,10));
        senderText.setFont(Font.font(11));
        vBox.getChildren().add(senderTextFlow);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(textMassage);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle(
                "-fx-background-color: rgb(233,233,235);" +
                "-fx-background-radius: 20px;");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        text.setFill(Color.color(0, 0, 0));

        hBox.getChildren().add(textFlow);
        vBox.getChildren().add(hBox);
        vboxMassage.getChildren().add(vBox);
    }
}
