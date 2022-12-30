package notebook.appgradle.commands;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import notebook.appgradle.*;

import java.io.IOException;

public class GoToPageCommand extends Command{

        public GoToPageCommand(Notebook notebook, Page page) {
            super(notebook, page);
        }

        @Override
        public void execute() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("notebookPage.fxml"));

            fxmlLoader.setControllerFactory(c -> new PageController(page));
            Scene newScene = new Scene(fxmlLoader.load());

            HelloApplication.mainStage.setScene(newScene);
            HelloApplication.mainStage.setTitle(page.getTitle());
            HelloApplication.mainStage.show();

        }
}
