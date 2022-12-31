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
import notebook.appgradle.commands.Command;
import notebook.appgradle.commands.GoNewPageCommand;
import notebook.appgradle.commands.GoToPageCommand;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Observer, Initializable {
    private Notebook notebook;
    @FXML
    private Button newPage;
    @FXML
    public GridPane listPages;

    public HomeController(Notebook notebook) {
        this.notebook = notebook;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                    goToPage(page.getPageNumber());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            button.getStyleClass().add("pageButton");
            button.setPrefHeight(76.0);
            button.setMinHeight(50.0);
            button.setPrefWidth(81.0);
            button.setMinWidth(50.0);
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

    public void executeCommand(Command command) throws IOException {
        command.execute();
    }
    public void newPageFromHome() throws IOException {
        executeCommand(new GoNewPageCommand(notebook, null));
    }
    public void goToPage(int pageNumber) throws IOException {
        Page objective = notebook.getPage(pageNumber);
        executeCommand(new GoToPageCommand(notebook, objective));
        }
};
