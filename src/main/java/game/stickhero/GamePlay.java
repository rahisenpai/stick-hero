package game.stickhero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class GamePlay {
    private Stage stage;
    private GamePlayController controller;

    public void display() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        controller = fxmlLoader.getController();
        controller.setup(scene,this);
    }

    public GamePlay(Stage stage){
        this.stage = stage;
    }
}
