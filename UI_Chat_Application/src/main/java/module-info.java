module org.example.ui_chat_application {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ui_chat_application to javafx.fxml;
    exports org.example.ui_chat_application;
    exports org.example.ui_chat_application.login;
    exports org.example.ui_chat_application.home;
    opens org.example.ui_chat_application.home to javafx.fxml;
    opens org.example.ui_chat_application.login to javafx.fxml;
}