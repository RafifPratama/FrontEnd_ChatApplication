package org.example.ui_chat_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.ui_chat_application.MainApplication;
import org.example.client.*;

import com.google.gson.Gson;

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
//        boolean isAuth = false;
//        IClient client = new Client();
//        if (tfUsername.getText().isBlank() == false && tfPassword.getText().isBlank() == false) {
//            String username = tfUsername.getText();
//            String password = tfPassword.getText();
//            isAuth = client.login(username, password) ? true : false;
//        }else {
//            loginMassage.setText("Invalid login!");
//        }
////        loginMassage.setText("");
////        String username = tfUsername.getText();
////        String password = tfPassword.getText();
////        IClient client = new Client();
////        boolean isAuth = client.login(username, password) ? true : false;
//
//        if(isAuth){
//            try {
//                MainApplication.setRoot("home.fxml");
//            }catch (IOException e) {
//                e.printStackTrace();
//                e.getCause();
//            }
//        }else{
//            loginMassage.setText("Login gagal");
//        }
        try {
            MainApplication.setRoot("home.fxml");
        }catch (IOException e){
            e.printStackTrace();
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
