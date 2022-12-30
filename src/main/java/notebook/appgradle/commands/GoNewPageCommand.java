package notebook.appgradle.commands;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import notebook.appgradle.EditController;
import notebook.appgradle.HelloApplication;
import notebook.appgradle.Notebook;
import notebook.appgradle.Page;

import java.io.IOException;

public class GoNewPageCommand extends Command {

    public GoNewPageCommand(Notebook notebook, Page page) {
        super(notebook, page);
    }
    @Override
    public void execute() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editPage.fxml"));

        fxmlLoader.setControllerFactory(c -> new EditController(HelloApplication.notebook, new Page(HelloApplication.notebook)));
        Scene newScene = new Scene(fxmlLoader.load());

        HelloApplication.mainStage.setScene(newScene);
        HelloApplication.mainStage.setTitle("New Page");
        HelloApplication.mainStage.show();
    }
}
