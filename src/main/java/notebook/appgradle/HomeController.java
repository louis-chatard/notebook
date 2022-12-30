package notebook.appgradle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Observer, Initializable {
    private Parent root;
    private Scene newScene;
    private Stage stage;
    private Notebook notebook;

    @FXML
    private Label tester;
    @FXML
    private Button newPage;
    @FXML
    public GridPane listPages;
    @FXML
    private Button editPage;

    public HomeController(Notebook notebook) {
        this.notebook = notebook;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notebook.addObserver(this);
        notebook.showNotebook();
        update();
    }

    @Override
    public void update() {
        int row = 0, col = 0;
        for (Page page : notebook.getPages()) {
            Button button = new Button(page.getTitle());
            button.setId("goPage"+page.getPageNumber()+"Button");
            button.setContentDisplay(ContentDisplay.CENTER);
            button.setMnemonicParsing(false);
            button.setOnAction(event -> {
                try {
                    goToPage(event, page.getPageNumber());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            button.setPrefHeight(76.0);
            button.setPrefWidth(81.0);
            button.setTextAlignment(TextAlignment.CENTER);
            button.setWrapText(true);

            listPages.add(button, col, row);
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }
    }

    public void newPageFromHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editPage.fxml"));

        Page newPage = new Page(this.notebook);
        notebook.addPage(newPage);
        newPage.setPageNumber(notebook.getPages().size());
        fxmlLoader.setControllerFactory(iC -> new EditController(newPage, this.notebook));
        newScene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    public void goToPage(ActionEvent event, int pageNumber) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("notebookPage.fxml"));

        System.out.println("Page number searched: "+pageNumber);
        System.out.println("Page number found: "+notebook.getPages().get(pageNumber-1).getPageNumber());
        Page page = notebook.getPages().get(pageNumber-1);
        fxmlLoader.setControllerFactory(iC -> new PageController(page));
        newScene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(page.getTitle());
        stage.setScene(newScene);
        stage.show();
        }


}
