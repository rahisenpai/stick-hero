package game.stickhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class GamePlayController {
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

    public void setup(Scene scene, GamePlay g){
        this.scene = scene;
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
    }

    public void pause(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("pause.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
