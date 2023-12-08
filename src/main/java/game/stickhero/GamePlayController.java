package game.stickhero;

import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;


public class GamePlayController {
    private Stage stage;
    private Scene scene;
    private GamePlay gameplay;
    private  Hero hero;
    private Pillar cp,np;
    @FXML
    private Label cherries;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView avatar;
    @FXML
    private Rectangle r1, r2;

    public void setup(Scene scene, Stage stage, GamePlay g){
        this.scene = scene;
        this.stage = stage;
        this.gameplay = g;
        this.hero = new Hero(75,65,avatar, this.gameplay);
        Stick stick = this.hero.getStick();
        pane.getChildren().add(stick.getRectangle());
        Pillar p1 = new Pillar(r1);
        Pillar p2 = new Pillar(r2);
        stick.getRectangle().setLayoutX(127);
        stick.getRectangle().setLayoutY(470);
        this.cp = new Pillar(r1);
        this.np = new Pillar(r2);

        scene.setOnMousePressed(event -> {
            this.hero.getStick().extend();
        });
        scene.setOnMouseReleased(event -> {
            this.hero.getStick().rotate();
        });
        scene.setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            if (code.equals("SPACE")){
                this.hero.getStick().extend();
            }
            else if (code.equals("ESCAPE")){
                this.gameplay.getAnimations().forEach(Animation::pause);
                pause();
            }
        });
        scene.setOnKeyReleased(event -> {
            String code = event.getCode().toString();
            if (code.equals("SPACE")){
                this.hero.getStick().rotate();
            }
        });
    }

    public void pause() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("pause.fxml"));
            this.pane.getChildren().add(root);
            root.setLayoutX(200);
            root.setLayoutY(150);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void gameOver() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("gameOver.fxml"));
            this.pane.getChildren().add(root);
            root.setLayoutX(200);
            root.setLayoutY(150);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Hero getHero() {
        return hero;
    }
    public Pillar getCP() {
        return this.cp;
    }
    public Pillar getNP() {
        return this.np;
    }
    public void setNp(Pillar np) {
        this.np = np;
    }
    public void setCp(Pillar cp) {
        this.cp = cp;
    }
    public AnchorPane getPane() {
        return pane;
    }
}
