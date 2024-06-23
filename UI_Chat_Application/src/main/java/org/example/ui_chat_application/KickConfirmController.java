package org.example.ui_chat_application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.example.client.IClient;
import org.example.model.Room;
import org.example.model.User;

import javafx.event.ActionEvent;

public class KickConfirmController {
    private IClient client;
    private User toBeKickMember;
    private Room room;
    private Parent root;
    private Stage stage;
	private Scene scene;

    @FXML
    private Text confirmationText;

    @FXML
    private Button btnKick;

    @FXML
    private Button btnCancel;

    @FXML
    private void handleKick(ActionEvent event) {
        boolean hasKicked = this.client.kickMember(toBeKickMember.getId(), this.room.getId());
        System.out.println(hasKicked);
        if(hasKicked){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                root = loader.load();
                HomeController homeController = loader.getController();
                homeController.setClient(client);
                homeController.init();
        
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            root = loader.load();
            HomeController homeController = loader.getController();
            homeController.setClient(client);
            homeController.init();
    
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void init(User toBeKickMember, Room room){
        this.toBeKickMember = toBeKickMember;
        this.room = room;
    }

    public void setClient(IClient client){
        this.client = client;
    }
}