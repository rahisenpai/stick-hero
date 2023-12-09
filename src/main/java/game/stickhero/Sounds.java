package game.stickhero;

import javafx.scene.media.*;
import java.io.File;


public class Sounds {
    private static final String score = "src/main/resources/game/stickhero/score.mp3";
    private static final String extend = "src/main/resources/game/stickhero/extend.mp3";
    private static final String fall = "src/main/resources/game/stickhero/score.mp3";
    private static final String revive = "src/main/resources/game/stickhero/revive.mp3";
    private static final String cherry = "src/main/resources/game/stickhero/cherries.mp3";
    private static final String gameOver = "src/main/resources/game/stickhero/gameover.mp3";
    private static MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File(score).toURI().toString()));

    private static void play(String s){
        Media sound = new Media(new File(s).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public static void score(){
        play(score);
    }
    public static void extend(){
        play(extend);
    }
    public static void fall(){
        play(fall);
    }
    public static void cherry(){
        play(cherry);
    }
    public static void gameOver(){
        play(gameOver);
    }
    public static void revive(){
        play(revive);
    }
    public static void stop(){
        mediaPlayer.stop();
    }
}
