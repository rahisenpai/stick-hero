package game.stickhero;

import java.io.IOException;
import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PrimaryController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static GamePlay newGamePlay;

    public void resume(ActionEvent event) {
        newGamePlay.getController().getPane().getChildren().remove(((Node)event.getSource()).getParent());
        newGamePlay.getAnimations().forEach(Animation::play);
    }

    public void startGame(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        newGamePlay = new GamePlay(stage);
        newGamePlay.display();
    }

    public void loadHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exitGame(ActionEvent event){
        System.exit(0);
    }
}