package notebook.appgradle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller implements Observer {

    private Stage stage;
    private Scene newScene;
    private Parent root;
    @FXML
    private Label welcomeText;

    public void switchToEditPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editPage.fxml"));
        root = loader.load();

        EditController editController = loader.getController();
        editController.update();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();

    }
    @Override
    public void update() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}