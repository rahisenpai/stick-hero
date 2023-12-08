package game.stickhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class GamePlayController {
    private Scene scene;
    private GamePlay gameplay;
    private  Hero hero;
    private Pillar p1,p2,p3;
    @FXML
    private AnchorPane pane;
    @FXML
    private ImageView avatar;
    @FXML
    private Rectangle r1, r2;
    public void setup(Scene scene, GamePlay g){
        this.scene = scene;
        this.gameplay = g;
        this.hero = new Hero(75,65,avatar);
        Stick stick = this.hero.getStick();
        pane.getChildren().add(stick.getRectangle());
        stick.getRectangle().setLayoutX(127);
        stick.getRectangle().setLayoutY(470);
        this.p1 = new Pillar(r1);
        this.p2 = new Pillar(r2);
        scene.setOnMousePressed(event -> {
            this.hero.getStick().extend();
        });
        scene.setOnMouseReleased(event -> {
            this.hero.getStick().rotate();
        });
    }

    public void pause(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pause.fxml")));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Hero getHero() {
        return hero;
    }
}
