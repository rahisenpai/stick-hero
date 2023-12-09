package game.stickhero;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class Hero extends GameObject {
    private boolean willFall=false, isMoving=false, isInverted=false;
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
        TranslateTransition translate = new TranslateTransition(Duration.seconds((this.stick.getLength()+25)/250),this.node);
        translate.setByX(this.stick.getLength()+25);
        this.gameplay.addAnimation(translate);
        this.isMoving = true;
        translate.play();
        translate.setOnFinished( e -> {
            this.gameplay.removeAnimation(translate);
            this.isMoving = false;
            if (this.willFall || this.isInverted){
                this.fall();
            }
            else {
                this.gameplay.screenTransition();
            }
        });
    }

    public void invert(){
        if (this.isMoving && !this.isInverted){
            this.node.setScaleY(-1);
            this.node.setLayoutY(this.node.getLayoutY()+this.height);
            this.isInverted = true;
        }
    }

    public void deInvert(){
        if (this.isMoving && this.isInverted){
            this.node.setScaleY(1);
            this.node.setLayoutY(this.node.getLayoutY()-this.height);
            this.isInverted = false;
        }
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
        rotate.setOnFinished(e -> {
            this.gameplay.removeAnimation(translate);
            this.gameplay.removeAnimation(rotate);
            if (this.gameplay.getCherries()<5) {
                this.gameplay.getController().gameOver();
                Main.getGame().serialize();
            } else {
                revive();
            }
        });
    }

    public void revive(){
        TranslateTransition translate = new TranslateTransition(Duration.millis(500),this.node);
        translate.setByY(-250);
        Text revive = new Text("REVIVED!!!");
        revive.setWrappingWidth(300);
        revive.setLayoutX(270);
        revive.setLayoutY(320);
        revive.setTabSize(36);
        revive.setFont(Font.font(64));
        this.gameplay.getController().getPane().getChildren().add(revive);
        this.gameplay.addAnimation(translate);
        translate.play();
        translate.setOnFinished(e -> {
            this.gameplay.removeAnimation(translate);
            int c = this.gameplay.getCherries()-5;
            this.gameplay.setCherries(c);
            this.gameplay.getController().getCherries().setText(c+"");
            int s = this.gameplay.getScore()-5;
            this.gameplay.setScore(s);
            this.gameplay.getController().getScore().setText(s+"");
            this.gameplay.getController().getPane().getChildren().remove(revive);
            this.gameplay.screenTransition();
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
    public boolean getInverted() {
        return this.isInverted;
    }
}