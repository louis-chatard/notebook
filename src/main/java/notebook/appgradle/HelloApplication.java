package notebook.appgradle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import notebook.appgradle.commands.Command;
import notebook.appgradle.commands.CommandHistory;

import java.io.IOException;

// TODO : home page
// TODO : store pages

// TODO : change to Command pattern ce qu'on peut

// TODO : navigate between pages

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
    public Notebook notebook = (new Notebook()).createDemoNotebook();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));

        fxmlLoader.setControllerFactory(iC -> new HomeController(notebook));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Notebook");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}