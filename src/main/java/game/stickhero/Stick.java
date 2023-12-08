package game.stickhero;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.event.ActionEvent;

public class Stick {
    private static Hero hero;
    final private int width;
    private boolean waiting;
    private double length;
    private Rectangle rectangle;
    private Timeline timeline;

    Stick(int w, Hero hero){
        this.waiting = true;
        this.width = w;
        this.length = 1;
        this.hero = hero;
        this.rectangle = new Rectangle(this.width,this.length);
    }

    public void extend(){
        if (this.waiting){
            this.waiting = false;
            timeline = new Timeline(new KeyFrame(Duration.millis(25), e -> {
                this.length += 5;
                this.rectangle.setLayoutY(rectangle.getLayoutY() - 5);
                this.rectangle.setHeight(this.length);
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    public void rotate() {
        if (!this.waiting) {
            timeline.stop();
            Rotate rotate = new Rotate(0,this.rectangle.getX(),this.rectangle.getY()+this.rectangle.getHeight());
            this.rectangle.getTransforms().add(rotate);
            timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), 90))
            );
            timeline.play();
            timeline.setOnFinished( new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stick.hero.move();
                        }
                }
            );
        }
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public double getLength() {
        return length;
    }
}