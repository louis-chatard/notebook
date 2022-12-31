package notebook.appgradle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import notebook.appgradle.commands.Command;
import notebook.appgradle.commands.GoNewPageCommand;
import notebook.appgradle.commands.GoToPageCommand;
import notebook.appgradle.commands.exitCommand;

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
    public void quit() throws IOException {
        executeCommand(new exitCommand());
    }

    public void createTemplate() {
        listPages.getChildren().clear();
        HelloApplication.notebook = new Notebook();
        String loremIpsum = "\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit.\n" +
                "Ut velit mauris, egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna.\n" +
                "Aliquam convallis sollicitudin purus. Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet.";
        for ( int i = 1; i < 11 ; i++ ) {
            Page page = new Page("Trip" + i, "Enter trip dates", "Lorem Ipsum: " + i + loremIpsum, "");
            page.setPageNumber(i);
            notebook.addPage(page);
            notebook.savePage(page);
        }
        update();
    }
};
