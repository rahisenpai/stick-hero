module game.stickhero {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens game.stickhero to javafx.fxml;
    exports game.stickhero;
}