package notebook.appgradle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import notebook.appgradle.commands.Command;

import java.io.IOException;

public class PageController implements Observer {

    private Stage stage;
    private Scene newScene;
    private Parent root;
    @FXML
    private Label welcomeText;

    private void executeCommand(Command command) {
        command.execute();
        HelloApplication.history.push(command);
    }

    public void editPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editPage.fxml"));
        root = loader.load();

        EditController editController = loader.getController();
        editController.editPage(new Page());
        editController.update();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newScene = new Scene(root);
        stage.setScene(newScene);
        stage.show();

    }

    public void newPage(ActionEvent event) throws IOException {
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
    public void goToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        root = loader.load();

        HomeController homeController = loader.getController();

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