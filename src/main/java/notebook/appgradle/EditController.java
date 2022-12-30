package notebook.appgradle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import notebook.appgradle.commands.Command;
import notebook.appgradle.commands.GoHomeCommand;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditController implements Observer, Initializable {
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


    public EditController(Notebook notebook, Page page) {
        this.notebook = notebook;
        this.page = page;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        page.addObserver(this);
        update();
    }
    @Override
    public void update() {
        titleField.setText(page.getTitle());
        descriptionField.setText(page.getDescription());
        textField.setText(page.getText());
        beginDate.setValue(page.getBeginDate());
        endDate.setValue(page.getEndDate());
        HelloApplication.mainStage.setTitle(page.getTitle());
    }

    public void save() {
        page.setTitle(titleField.getText());
        // check if a date is entered
        if (beginDate.getValue() != null && endDate.getValue() != null) {
            page.setBeginDate(beginDate.getValue());
            page.setEndDate(endDate.getValue());
            String beginString = page.getBeginDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String endString = page.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String date = "Trip from the " + beginString + " to the " + endString;
            page.setDescription(date);
        } else {
            page.setText(textField.getText());
            if ( !page.isEmpty() ) {
                if (page.getPageNumber() == -1) {
                    page.setPageNumber(notebook.getPages().size() + 1);
                    notebook.addPage(page);
                }
                notebook.updatePage(page.getPageNumber(), page);
            }
        }
        update();
    }

    public void executeCommand(Command command) throws IOException {
        command.execute();
    }
    public void goBackHome() throws IOException {
        executeCommand(new GoHomeCommand(notebook, page));
    }

}
