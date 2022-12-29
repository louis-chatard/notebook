module notebook.appgradle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens notebook.appgradle to javafx.fxml;
    exports notebook.appgradle;
}