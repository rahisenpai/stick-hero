package game.stickhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class Hero {
    final private int height, width;
    private Stick stick;
    private ImageView node;
    private Image avatar;

    Hero(int h, int w, ImageView node){
        this.height = h;
        this.width = w;
        this.node = node;
        this.avatar = node.getImage();
        this.stick = new Stick(10,this);
    }

    public void move(){
        TranslateTransition translate = new TranslateTransition(Duration.seconds(2),this.node);
        translate.setByX(this.stick.getLength()+25);
        translate.play();
        this.stick.setWaiting(true);
    }

    public Stick getStick() {
        return stick;
    }
}