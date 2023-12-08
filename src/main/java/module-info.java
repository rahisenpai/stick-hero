module game.stickhero {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.stickhero to javafx.fxml;
    exports game.stickhero;
}