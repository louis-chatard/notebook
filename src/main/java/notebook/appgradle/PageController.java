package notebook.appgradle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import notebook.appgradle.commands.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class PageController implements Observer {
    private Stage stage;
    private Scene newScene;
    private Parent root;
    private Page page;
    private FileChooser fileChooser = new FileChooser();
    @FXML
    private Label text = new Label();
    @FXML
    private Label title = new Label();
    @FXML
    private Label description = new Label();

    public PageController(Page page) {
        this.page = page;
        title.setText(page.getTitle());
        description.setText(page.getDescription());
        text.setText(page.getText());
        update();
        /*
        text.setText(page.getText());
        title.setText(page.getTitle());
        description.setText(page.getDescription());
        */
    }

    private void executeCommand(Command command) {
        command.execute();
        HelloApplication.history.push(command);
    }

    public void editPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editPage.fxml"));
        root = loader.load();

        EditController editController = loader.getController();
        editController.editPage(page);
        editController.update();

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        newScene = new Scene(root);
        stage.setTitle(page.getTitle() + " - Edit");
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));

        Notebook notebook = (new Notebook()).createDemoNotebook();
        fxmlLoader.setControllerFactory(iC -> new HomeController(notebook));
        newScene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Notebook");
        stage.setScene(newScene);
        stage.show();

    }
    @Override
    public void update() {
        title.setText("woohoo un titre");
        description.setText(page.getDescription());
        text.setText(page.getText());
        // to complete
    }


    public void readFile(int pageNumber) {
        String fileName = "pages/page" + pageNumber + ".txt";
        File file = fileChooser.showOpenDialog(new Stage());
        String content = "";
        if (file != null) {
            try {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    content += scanner.nextLine() + "\n";
                }
                text.setText(content);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}