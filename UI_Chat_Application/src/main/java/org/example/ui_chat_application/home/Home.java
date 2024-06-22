package org.example.ui_chat_application.home;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Home {
    private HBox hbox;
    private Label label;
    private Button button;

    public Home(String item) {
        hbox = new HBox(10); // 10 is the spacing between label and button
        label = new Label(item);
        button = new Button("✓"); // Change the button label to "✓"

        hbox.getChildren().addAll(label, button);

        button.setOnAction(event -> {
            // Define the action for the check button
            System.out.println("Checked: " + item);
        });
    }

    public HBox getHBox() {
        return hbox;
    }
}
