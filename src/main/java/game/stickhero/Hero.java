package game.stickhero;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class Hero {
    private boolean willFall = false;
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
        this.gameplay.addAnimation(translate);
        translate.play();
        translate.setOnFinished( e -> {
            this.gameplay.removeAnimation(translate);
            if (this.willFall){
                this.fall();
            }
            else {
                this.gameplay.screenTransition();
            }
        });
    }

    public void fall(){
        TranslateTransition translate = new TranslateTransition(Duration.millis(750),this.node);
        RotateTransition rotate = new RotateTransition(Duration.millis(500),this.node);
        translate.setByY(250);
        rotate.setByAngle(360);
        rotate.setCycleCount(2);
        this.gameplay.addAnimation(translate);
        this.gameplay.addAnimation(rotate);
        translate.play(); rotate.play();
        translate.setOnFinished(e -> {
            this.gameplay.removeAnimation(translate);
            this.gameplay.removeAnimation(rotate);
            this.gameplay.getController().gameOver();
            Main.getGame().updateScore(this.gameplay.getScore());
            Main.getGame().serialize();
        });
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
    public void setWillFall(boolean willFall) {
        this.willFall = willFall;
    }
    public GamePlay getGameplay() {
        return gameplay;
    }
}