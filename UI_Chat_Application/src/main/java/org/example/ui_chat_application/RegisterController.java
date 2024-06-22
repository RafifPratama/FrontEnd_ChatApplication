package org.example.ui_chat_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import org.example.client.Client;
import org.example.client.IClient;
public class RegisterController {
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
                    MainApplication.setRoot("login.fxml");
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
            MainApplication.setRoot("login.fxml");
//            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
//            Stage registerStage = new Stage();
//            registerStage.setScene(new Scene(root, 600, 400));
//            registerStage.show();
        }catch (IOException e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
