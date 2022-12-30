package notebook.appgradle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notebook.appgradle.commands.CommandHistory;

import java.io.IOException;


// TODO : store pages

// TODO : illustration pages tah LOREM ISPUM

// TODO : css styling

// TODO : remove page
// TODO : add tags to
// TODO : search by tags
// TODO : add search by tags
// TODO : add search by text
// TODO : add search by date

// TODO : order page by date

// TODO : input image into pages



public class HelloApplication extends Application {
    public static CommandHistory history = new CommandHistory();
    public static Notebook notebook = (new Notebook()).createDemoNotebook();
    public static Scene mainScene;
    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));

        fxmlLoader.setControllerFactory(iC -> new HomeController(notebook));

        mainScene = new Scene(fxmlLoader.load());
        mainStage = stage;
        mainStage.setTitle("test");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}