package notebook.appgradle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notebook.appgradle.commands.CommandHistory;

import java.io.IOException;


// TODO : store pages
// TODO : jar !!

// TODO : illustration pages tah LOREM ISPUM

// TODO : fix remove page

// TODO : add tags to page
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
        mainScene.getStylesheets().add(HelloApplication.class.getResource("style.css").toExternalForm());
        mainStage = stage;
        mainStage.setTitle("Notebook");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}