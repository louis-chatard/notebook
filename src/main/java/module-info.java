module notebook.appgradle {
    requires javafx.controls;
    requires javafx.fxml;

    opens notebook.appgradle to javafx.fxml;
    exports notebook.appgradle;
}