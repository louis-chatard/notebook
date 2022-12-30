package notebook.appgradle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditController implements Observer, Initializable {
    private Scene newScene;
    private Stage stage;
    private Notebook notebook;
    private Page page;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextArea textField;
    @FXML
    private DatePicker beginDate;
    @FXML
    private DatePicker endDate;


    public EditController(Page page, Notebook notebook) {
        this.notebook = notebook;
        if (page == null) {
            this.page = new Page(this.notebook);
        } else {
            this.page = page;
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        page.addObserver(this);
        notebook.showNotebook();
        update();
    }
    @Override
    public void update() {
        textField.setText(page.getText());
        descriptionField.setText(page.getDescription());
        titleField.setText(page.getTitle());
    }

    public void save() {
        page.setTitle(titleField.getText());
        String beginString = beginDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String endString = endDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String date = "Trip from the " + beginString + " to the " + endString;
        page.setDescription(date);
        page.setText(textField.getText());
        notebook.updatePage(page.getPageNumber(), page);
        notebook.showNotebook();
        update();
    }
    public void goBackHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));

        fxmlLoader.setControllerFactory(iC -> new HomeController(notebook));
        newScene = new Scene(fxmlLoader.load());

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }


}
