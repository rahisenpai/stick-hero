package game.stickhero;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Game extends javafx.application.Application implements Serializable {
    private static final long serialVersionUID = 10L;
    private int highscore;
    private ArrayList<SavedGame> savedGames;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        PrimaryController controller = fxmlLoader.getController();
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("hero.png")));

        Text highscore = new Text("HIGH SCORE: "+Main.getGame().getHighscore());
        highscore.setWrappingWidth(300);
        highscore.setLayoutX(257);
        highscore.setLayoutY(84);
        highscore.setTabSize(32);
        highscore.setFont(Font.font(36));
        AnchorPane pane = (AnchorPane) scene.getRoot().getChildrenUnmodifiable().get(1);
        Button load = new Button("LOAD");
        load.setFont(Font.font(24));
        load.setLayoutX(237);
        load.setLayoutY(279);
        load.setPrefSize(95,50);
        load.setOnAction(e -> controller.loadSavedGame(stage));

        if (!Main.getGame().getSavedGames().isEmpty()){
            pane.getChildren().add(load);
        }
        pane.getChildren().add(highscore);
        stage.setTitle("stick-hero");
        stage.setScene(scene);
        stage.show();
    }

    public void serialize(){
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("src/main/data/StickHero.txt"));
            out.writeObject(this);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("error in serializing");
        } finally {
            try { out.close(); }
            catch (IOException ignored) {}
        }
    }

    public Game(){
        this.highscore = 0;
        this.savedGames = new ArrayList<SavedGame>();
    }
    public void updateScore(Integer i){
        this.highscore = Math.max(i, this.highscore);
    }
    public void init(int i) {
        this.highscore = i;
        launch();
    }
    public void addSavedGame(SavedGame s){
        this.savedGames.add(s);
    }
    public ArrayList<SavedGame> getSavedGames() {
        return savedGames;
    }
    public int getHighscore() {
        return highscore;
    }
}