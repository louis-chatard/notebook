package notebook.appgradle.commands;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import notebook.appgradle.EditController;
import notebook.appgradle.HelloApplication;
import notebook.appgradle.Notebook;
import notebook.appgradle.Page;

import java.io.IOException;

public class EditPageCommand extends Command{

        public EditPageCommand(Notebook notebook, Page page) {
            super(notebook, page);
        }

        @Override
        public void execute() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("editPage.fxml"));

            fxmlLoader.setControllerFactory(c -> new EditController(HelloApplication.notebook, page));
            Scene newScene = new Scene(fxmlLoader.load());
            newScene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());

            HelloApplication.mainStage.setResizable(false);
            HelloApplication.mainStage.setScene(newScene);
            HelloApplication.mainStage.setTitle(page.getTitle() + " - Edit");
            HelloApplication.mainStage.show();
        }
}
