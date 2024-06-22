package org.example.ui_chat_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import org.example.client.Client;
import org.example.client.IClient;
public class RegisterController {
    private Parent root;
    private Stage stage;
	private Scene scene;

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnSubmitRegister;
    @FXML
    private Label registerMassage;

    public void registerButtonOnClick(ActionEvent event) {
        IClient client = new Client();
        if (tfName.getText().isBlank()==false && tfUsername.getText().isBlank() == false && tfPassword.getText().isBlank() == false){
            String name = tfName.getText();
            String username = tfUsername.getText();
            String password = tfPassword.getText();

            boolean isRegistered = client.register(username,password,name) ? true : false;
            if (isRegistered) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                    root = loader.load();
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }catch (IOException e) {
                    e.printStackTrace();
                    e.getCause();
                }
            }else {
                registerMassage.setText("register gagal");
            }

        }else {
            registerMassage.setText("invalid register");
        }
    }

    @FXML
    public void loginButtonOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
