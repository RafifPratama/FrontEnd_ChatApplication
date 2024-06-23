package org.example.ui_chat_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import org.example.client.IClient;
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

    public void init() {

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

            textFlow.setStyle("-fx-color: rgb(255,255,255)" +
                    "-fx-background-color: (15,152,242)" +
                    "-fx-background-radius: 20px");
            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(Color.color(0.934, 0.945, 0.996));

            hBox.getChildren().add(textFlow);
            vboxMassage.getChildren().add(hBox);
        }else {
            System.out.println();
        }
    }

    public void addChatBubble(String textMassage) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(textMassage);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle(
                "-fx-background-color: (233,233,235)" +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));
        text.setFill(Color.color(0.934, 0.945, 0.996));

        hBox.getChildren().add(textFlow);
        vboxMassage.getChildren().add(hBox);
    }
}
