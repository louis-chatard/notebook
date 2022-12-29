package notebook.appgradle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EditController implements Observer {

    @FXML
    private Label labeltest;

    public void update() {
        labeltest.setText("Welcome to JavaFX Application!");
    }

}
