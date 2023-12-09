package game.stickhero;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;


public class GamePlay {
    private Integer cherries=0, score=0;
    private ArrayList<Animation> animations;
    private Stage stage;
    private GamePlayController controller;
    private Cherry cherry;
    private boolean cherryPresent=false, collectedCherry=false;

    public void display() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        controller = fxmlLoader.getController();
        controller.setup(scene,this.stage,this);
        this.cherry = Cherry.getInstance(new ImageView(new Image(Main.class.getResourceAsStream("cherry.png"))));
        this.cherry.getNode().setFitHeight(44);
        this.cherry.getNode().setFitWidth(33);
        this.cherry.getNode().setLayoutY(480);
        this.controller.getPane().getChildren().add(cherry.getNode());
        this.cherry.getNode().setVisible(false);
        this.cherryPresent = false;
        this.animations = new ArrayList<Animation>();
        this.controller.getCherries().setText(cherries+"");
        this.controller.getScore().setText(score+"");
        stage.setScene(scene);
        new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!controller.getGameplay().getCollectedCherry()) {
                    if (checkCollision(controller.getHero().getNode(), cherry.getNode())) {
                        Sounds.cherry();
                        controller.getGameplay().setCollectedCherry(true);
                        int score = controller.getGameplay().getScore()+1;
                        controller.getGameplay().setScore(score);
                        controller.getScore().setText(score+"");
                        int cherry = controller.getGameplay().getCherries()+1;
                        controller.getGameplay().setCherries(cherry);
                        controller.getCherries().setText(cherry+"");
                    }
                }
            }
        }.start();
    }

    public void screenTransition(){
        this.collectedCherry = false;
        Hero hero = this.controller.getHero();
        Pillar cp = this.controller.getCP();
        Pillar np = this.controller.getNP();
        Stick stick = hero.getStick();
        double distance = (cp.getRectangle().getLayoutX()+cp.getRectangle().getWidth()) - (np.getRectangle().getLayoutX()+np.getRectangle().getWidth());
        if (this.cherryPresent){
            this.cherryPresent = false;
            this.cherry.getNode().setVisible(false);
        }

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
            this.animations.remove(timeline);
            this.score++;
            if (!this.controller.getHero().getWillFall()) {
                Sounds.score();
            }
            Main.getGame().updateScore(this.score);
            this.controller.getScore().setText(this.score.toString());
            Stick newStick = Factory.createStick(hero);
            hero.setStick(newStick);
            newStick.getRectangle().setLayoutX(127);
            newStick.getRectangle().setLayoutY(470);
            Pillar newP = Factory.createPillarWithWidth(75 + (int)(Math.random() * ((150-75) + 1)));
            newP.getRectangle().setLayoutX(200 + (int)(Math.random() * ((550-200) + 1)));
            newP.getRectangle().setLayoutY(470);
            if (Math.random()*4 < 1){
                this.cherry.getNode().setLayoutX(200 + (int)(Math.random() * ((newP.getRectangle().getLayoutX()-250) + 1)));
                this.cherry.getNode().setVisible(true);
                this.cherryPresent = true;
            }
            this.controller.getPane().getChildren().addAll(newStick.getRectangle(), newP.getRectangle());
            this.controller.setCp(np);
            this.controller.setNp(newP);
        });
        this.animations.add(timeline);
        timeline.play();
    }

    private boolean checkCollision(ImageView image1, ImageView image2) {
        return image1.getBoundsInParent().intersects(image2.getBoundsInParent());
    }

    public ArrayList<Animation> getAnimations() {
        return animations;
    }
    public void addAnimation(Animation a){
        this.animations.add(a);
    }
    public void removeAnimation(Animation a){
        this.animations.remove(a);
    }
    public GamePlay(Stage stage){
        this.stage = stage;
    }
    public GamePlay(Stage stage,SavedGame sg){
        this.stage = stage;
        this.cherries = Integer.valueOf(sg.getCherries());
        this.score = Integer.valueOf(sg.getScore());
    }
    public GamePlayController getController() {
        return controller;
    }
    public Integer getScore() {
        return score;
    }
    public Integer getCherries() {
        return cherries;
    }
    public void setCollectedCherry(boolean collectedCherry) {
        this.collectedCherry = collectedCherry;
    }
    public boolean getCollectedCherry(){
        return this.collectedCherry;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
    public void setCherries(Integer cherries) {
        this.cherries = cherries;
    }
}
