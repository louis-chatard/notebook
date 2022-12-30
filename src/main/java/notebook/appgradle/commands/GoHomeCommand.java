package notebook.appgradle.commands;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import notebook.appgradle.HelloApplication;
import notebook.appgradle.HomeController;
import notebook.appgradle.Notebook;
import notebook.appgradle.Page;


import java.io.IOException;

public class GoHomeCommand extends Command {


    public GoHomeCommand(Notebook notebook, Page page) {
        super(notebook, page);
    }

    @Override
    public void execute() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));

        fxmlLoader.setControllerFactory(c -> new HomeController(HelloApplication.notebook));
        Scene newScene = new Scene(fxmlLoader.load());

        HelloApplication.mainStage.setScene(newScene);
        HelloApplication.mainStage.setTitle("Home");
        HelloApplication.mainStage.show();
    }
}
