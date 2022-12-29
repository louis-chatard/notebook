package notebook.appgradle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditController implements Observer {
    private Page page;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextArea textField;
    @FXML
    private Label labeltest;


    public void editPage(Page page) {
        labeltest.setText("Edition mode");
        if (page == null) {
            this.page = new Page();
        } else {
            this.page = page;
        }
        titleField.setText(page.getTitle());
        descriptionField.setText(page.getDescription());
        textField.setText(page.getText());
    }

    public void createPage() {
        this.page = new Page();
        editPage(this.page);
        labeltest.setText("Creation mode");

    }

    public void save() {}
    @Override
    public void update() {
        textField.setText(page.getText());
        descriptionField.setText(page.getDescription());
        titleField.setText(page.getTitle());
        labeltest.setUnderline(true);
    }


}
