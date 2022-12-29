package notebook.appgradle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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


public class HomeController implements Observer {
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

    // TODO : enter button which call update
    public void enterNotebook() {
        notebook.addObserver(this);
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

/*
            for (int i = 0; i <row ; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.println("i = " + i + " j = " + j);
                    Button button = new Button(page.getTitle());
                    listPages.add(button, j, i);
                }
            }   */
/*
            if (i == row) {

                i = 0;
            }
            if (j == col) {
                break;
            }
            while (i < row) {
                while (j < col) {
                    Button button = new Button(page.getTitle());
                    listPages.add(button, j, i);
                    j++;
                }
                i++;
            }
     */

    }

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

    public void goToPage(ActionEvent event, int pageNumber) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("notebookPage.fxml"));

        Page page = notebook.getPages().get(pageNumber);
        fxmlLoader.setControllerFactory(iC -> new PageController(page));
        newScene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(page.getTitle());
        stage.setScene(newScene);
        stage.show();
        }
}
