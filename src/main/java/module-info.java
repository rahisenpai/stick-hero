module com.game.stickhero {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.game.stickhero to javafx.fxml;
    exports com.game.stickhero;
}