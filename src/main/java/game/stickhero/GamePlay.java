package game.stickhero;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class GamePlay {
    private int cherries;
    private Stage stage;
    private GamePlayController controller;

    public void display() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        controller = fxmlLoader.getController();
        controller.setup(scene,this);
    }

    public void screenTransition(){
        Hero hero = this.controller.getHero();
        Pillar cp = this.controller.getCP();
        Pillar np = this.controller.getNP();
        Stick stick = hero.getStick();
        double distance = (cp.getRectangle().getLayoutX()+cp.getRectangle().getWidth()) - (np.getRectangle().getLayoutX()+np.getRectangle().getWidth());

        KeyFrame k1 = new KeyFrame(Duration.ZERO, new KeyValue(hero.getNode().layoutXProperty(), hero.getNode().getLayoutX()),
                new KeyValue(cp.getRectangle().layoutXProperty(), cp.getRectangle().getLayoutX()),
                new KeyValue(np.getRectangle().layoutXProperty(), np.getRectangle().getLayoutX()),
                new KeyValue(stick.getRectangle().layoutXProperty(), stick.getRectangle().getLayoutX()));
        KeyFrame k2 = new KeyFrame(Duration.seconds(1), new KeyValue(hero.getNode().layoutXProperty(), hero.getNode().getLayoutX()-stick.getLength()-25),
                new KeyValue(cp.getRectangle().layoutXProperty(), cp.getRectangle().getLayoutX()+distance),
                new KeyValue(np.getRectangle().layoutXProperty(), np.getRectangle().getLayoutX()+distance),
                new KeyValue(stick.getRectangle().layoutXProperty(), stick.getRectangle().getLayoutX()+distance));
        Timeline timeline = new Timeline(k1,k2);

        timeline.setOnFinished(e -> {
            Stick newStick = new Stick(10, hero);
            hero.setStick(newStick);
            newStick.getRectangle().setLayoutX(127);
            newStick.getRectangle().setLayoutY(470);
            Pillar newP = new Pillar(50);
            newP.getRectangle().setLayoutX(470);
            newP.getRectangle().setLayoutY(470);
            this.controller.getPane().getChildren().addAll(newStick.getRectangle(), newP.getRectangle());
            this.controller.setCp(np);
            this.controller.setNp(newP);
        });
        timeline.play();
    }

    public GamePlay(Stage stage){
        this.stage = stage;
    }
}
