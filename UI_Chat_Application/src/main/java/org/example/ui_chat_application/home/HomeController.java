package org.example.ui_chat_application.home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class MainController {

    @FXML
    private ListView<String> listViewLeft; // Assuming first ListView is the left one

    @FXML
    private ListView<String> listViewRight; // Assuming second ListView is the right one

    @FXML
    private Button btnChat;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnAdd;

    @FXML
    public void initialize() {
        // Add items to the right list view
        listViewRight.getItems().addAll("Alice", "Bob", "Drake");

        // Set the cell factory to use the custom list cell for the right list view
        listViewRight.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                return new ListCell<String>() {
                    private Home home;

                    {
                        // Initialize the Home instance within the ListCell
                        home = new Home("");

                        // Ensure the HBox is set as the graphic for the ListCell
                        setGraphic(home.getHBox());
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Update the Home instance with the new item text
                            home = new Home(item);
                            setGraphic(home.getHBox());
                        }
                    }
                };
            }
        });

        // Initialize other components or handle events as necessary
    }
}
