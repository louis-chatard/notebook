package notebook.appgradle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    private Parent root;
    private Scene newScene;
    private Stage stage;

    @FXML
    private Button navigationButton;
    @FXML
    private Button newPage;

    public void newPageFromHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editPage.fxml"));
        root = loader.load();

        EditController editController = loader.getController();
        editController.createPage();
        editController.update();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();
    }

    public void goToPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("notebookPage.fxml"));
        root = loader.load();

        PageController pageController = loader.getController();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newScene = new Scene(root);
            stage.setScene(newScene);
            stage.show();
        }
}
