package game.stickhero;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;


public class Game extends javafx.application.Application implements Serializable {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("hero.png"))));
        stage.setTitle("stick-hero");
        stage.setScene(scene);
        stage.show();
    }

    public static void init(String[] args) {
        launch(args);
    }
}