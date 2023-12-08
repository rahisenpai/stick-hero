package game.stickhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class Hero {
    private int height, width;
    private Stick stick;
    private ImageView node;
    private Image avatar;
    private GamePlay gameplay;

    Hero(int h, int w, ImageView node, GamePlay g){
        this.height = h;
        this.width = w;
        this.node = node;
        this.avatar = node.getImage();
        this.stick = new Stick(10,this);
        this.gameplay = g;
    }

    public void move(){
        TranslateTransition translate = new TranslateTransition(Duration.seconds(2),this.node);
        translate.setByX(this.stick.getLength()+25);
        translate.play();
        translate.setOnFinished( e -> this.gameplay.screenTransition());
    }

    public Stick getStick() {
        return this.stick;
    }
    public void setStick(Stick stick) {
        this.stick = stick;
    }
    public ImageView getNode() {
        return this.node;
    }
}