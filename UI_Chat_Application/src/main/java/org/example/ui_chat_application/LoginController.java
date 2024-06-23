package org.example.ui_chat_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import org.example.ui_chat_application.MainApplication;
import org.example.client.*;

import com.google.gson.Gson;

import java.io.IOException;

public class LoginController {
    private Parent root;
    private Stage stage;
	private Scene scene;

    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnSubmitLogin;
    @FXML
    private Label loginMassage;

    public void loginButtonOnClick(ActionEvent event){
        boolean isAuth = false;
        IClient client = new Client();
        if (tfUsername.getText().isBlank() == false && tfPassword.getText().isBlank() == false) {
            String username = tfUsername.getText();
            String password = tfPassword.getText();
            isAuth = client.login(username, password) ? true : false;
        }else {
            loginMassage.setText("Invalid login!");
        }

        if(isAuth){
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
                
            }catch (IOException e) {
                e.printStackTrace();
                e.getCause();
            }
        }else{
            loginMassage.setText("Login gagal");
        }
    }

    @FXML
    public void registerButtonOnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
