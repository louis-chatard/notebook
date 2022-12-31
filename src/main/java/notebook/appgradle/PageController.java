package notebook.appgradle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import notebook.appgradle.commands.Command;
import notebook.appgradle.commands.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PageController implements Observer, Initializable {
    private Notebook notebook;
    private Page page;
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
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        page.addObserver(this);
        update();
    }
    @Override
    public void update() {
        hideUselessButtons();
        this.titleLabel.setText(page.getTitle());
        this.descriptionLabel.setText(page.getDescription());
        this.textLabel.setText(page.getText());
        this.pageNumberLabel.setText("Page " + page.getPageNumber());
    }

    public void executeCommand(Command command) throws IOException {
        command.execute();
    }

    public void editPage() throws IOException {
        executeCommand(new EditPageCommand(notebook, page));
    }
    public void newPage() throws IOException {
        executeCommand(new GoNewPageCommand(notebook, null));
    }
    public void goToHome() throws IOException {
        executeCommand(new GoHomeCommand(notebook, page));
    }
    public void nextPage() {
        if (page.getPageNumber() < HelloApplication.notebook.getPages().size()) {
            page = HelloApplication.notebook.getPages().get(page.getPageNumber());
            update();
        }
    }
    public void previousPage() {
        if (page.getPageNumber() > 1) {
            page = HelloApplication.notebook.getPages().get(page.getPageNumber()-2);
            update();
        }
    }

    public void hideUselessButtons() {
        if (page.getPageNumber() == 1) {
            previousPageButton.setVisible(false);
        } else if (page.getPageNumber() == HelloApplication.notebook.getPages().size()) {
            nextPageButton.setVisible(false);
        } else {
            previousPageButton.setVisible(true);
            nextPageButton.setVisible(true);
        }
    }
}