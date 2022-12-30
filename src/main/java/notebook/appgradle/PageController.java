package notebook.appgradle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class PageController implements Observer, Initializable {
    private Notebook notebook;
    private Stage stage;
    private Scene newScene;
    private Parent root;
    private Page page;
    private FileChooser fileChooser = new FileChooser();
    @FXML
    private Label textLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label pageNumberLabel;
    @FXML
    private Button previousPageButton;
    @FXML
    private Button nextPageButton;


    public PageController(Page page) {
        this.page = page;
        this.notebook = page.getNotebook();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        page.addObserver(this);
        notebook.showNotebook();
        page.showDates();
        update();
    }
    @Override
    public void update() {
        hideUselessButtons();
        this.titleLabel.setText(page.getTitle());
        this.descriptionLabel.setText(page.getDescription());
        this.textLabel.setText(page.getText());
        this.pageNumberLabel.setText("Page " + (notebook.getPages().indexOf(page) + 1));
    }


    public void editPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editPage.fxml"));

        fxmlLoader.setControllerFactory(iC -> new EditController(page, this.notebook));
        newScene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(page.getTitle()+" - Edit");
        stage.setScene(newScene);
        stage.show();
    }
    public void newPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editPage.fxml"));

        Page newPage = new Page(this.notebook);
        notebook.addPage(newPage);
        newPage.setPageNumber(notebook.getPages().size());
        fxmlLoader.setControllerFactory(iC -> new EditController(newPage, this.notebook));
        newScene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("New Page");
        stage.setScene(newScene);
        stage.show();
    }
    public void goToHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));

        fxmlLoader.setControllerFactory(iC -> new HomeController(notebook));
        newScene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Notebook");
        stage.setScene(newScene);
        stage.show();

    }
    public void nextPage(ActionEvent event) {
        if (page.getPageNumber() < notebook.getPages().size()) {
            page = notebook.getPages().get(page.getPageNumber());
            update();
        }
    }
    public void previousPage(ActionEvent event) {
        if (page.getPageNumber() > 1) {
            page = notebook.getPages().get(page.getPageNumber()-2);
            update();
        }
    }

    public void hideUselessButtons() {
        if (page.getPageNumber() == 1) {
            previousPageButton.setVisible(false);
        } else if (page.getPageNumber() == notebook.getPages().size()) {
            nextPageButton.setVisible(false);
        } else {
            previousPageButton.setVisible(true);
            nextPageButton.setVisible(true);
        }
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
                textLabel.setText(content);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}