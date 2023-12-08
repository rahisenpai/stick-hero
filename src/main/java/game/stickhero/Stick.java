package game.stickhero;

import javafx.animation.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class Stick {
    private Hero hero;
    final private int width;
    private int waiting;
    private double length;
    private Rectangle rectangle;
    private Timeline timeline;

    Stick(int w, Hero hero){
        this.waiting = 0;
        this.width = w;
        this.length = 1;
        this.hero = hero;
        this.rectangle = new Rectangle(this.width,this.length);
    }

    public void extend(){
        if (this.waiting == 0){
            this.waiting = 1;
            timeline = new Timeline(new KeyFrame(Duration.millis(25), e -> {
                this.length += 5;
                this.rectangle.setY(rectangle.getY() - 5);
                this.rectangle.setHeight(this.length);
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            this.hero.getGameplay().addAnimation(timeline);
            timeline.setOnFinished(e -> this.hero.getGameplay().removeAnimation(timeline));
            timeline.play();
        }
    }

    public void rotate() {
        if (this.waiting == 1) {
            this.waiting = 2;
            timeline.stop();
            this.hero.getGameplay().removeAnimation(timeline);
            Pillar cp = this.hero.getGameplay().getController().getCP();
            Pillar np = this.hero.getGameplay().getController().getNP();
            double distance = np.getRectangle().getLayoutX() - (cp.getRectangle().getLayoutX()+cp.getRectangle().getWidth());
            if(this.length<distance || this.length>distance+np.getRectangle().getWidth()){
                this.hero.setWillFall(true);
            }
            Rotate rotate = new Rotate(0,this.rectangle.getX(),this.rectangle.getY()+this.rectangle.getHeight());
            this.rectangle.getTransforms().add(rotate);
            timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0,Interpolator.EASE_BOTH)),
                    new KeyFrame(Duration.millis(1000), new KeyValue(rotate.angleProperty(), 90,Interpolator.EASE_BOTH))
            );
            this.hero.getGameplay().addAnimation(timeline);
            timeline.play();
            timeline.setOnFinished( e -> {
                this.hero.getGameplay().removeAnimation(timeline);
                this.hero.move();
            });
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public double getLength() {
        return length;
    }
}