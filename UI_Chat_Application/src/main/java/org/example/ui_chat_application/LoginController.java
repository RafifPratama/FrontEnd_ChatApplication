package org.example.ui_chat_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.ui_chat_application.MainApplication;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnSubmitLogin;
    @FXML
    private Label loginMassage;

    public void loginButtonOnClick(ActionEvent event){
        if (tfUsername.getText().isBlank() == false && tfPassword.getText().isBlank() == false){
            loginMassage.setText("");
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            System.out.println("username: "+username);
            System.out.println("passwrod: "+ password);
            try {
                MainApplication.setRoot("home.fxml");
            }catch (IOException e) {
                e.printStackTrace();
                e.getCause();
            }
        }else {
            loginMassage.setText("Invalid login, please try again");
        }
    }

    @FXML
    public void registerButtonOnClick(ActionEvent event) {
        try {
            MainApplication.setRoot("register.fxml");
//            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
//            Stage registerStage = new Stage();
//            registerStage.setScene(new Scene(root, 600, 400));
//            registerStage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
