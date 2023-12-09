package game.stickhero;

import java.io.*;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PrimaryController implements Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static GamePlay newGamePlay;

    public void resume(ActionEvent event) {
        newGamePlay.getController().getPane().getChildren().remove(((Node)event.getSource()).getParent());
        newGamePlay.getController().setPaused(false);
        newGamePlay.getAnimations().forEach(Animation::play);
    }

    public void save(ActionEvent event){
        Button b = ((Button)(Node)event.getSource());
        SavedGame savesGame = new SavedGame(newGamePlay.getScore(), newGamePlay.getCherries());
        if (!Main.getGame().getSavedGames().isEmpty()) {
            Main.getGame().getSavedGames().remove(0);
        }
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("src/main/data/SavedGame.txt"));
            out.writeObject(savesGame);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("error in serializing");
        } finally {
            try { out.close(); }
            catch (IOException ignored) {}
        }
        Main.getGame().addSavedGame(savesGame);
        Main.getGame().serialize();
        b.setText("SAVED");
    }

    public void loadSavedGame(Stage stage){
        SavedGame savedGame = deserializeSavedGame();
        newGamePlay = new GamePlay(stage,savedGame);
        try {
            newGamePlay.display();
        }
        catch(IOException ignored){}
    }

    public static SavedGame deserializeSavedGame() {
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream("src/main/data/SavedGame.txt"));
            return (SavedGame) in.readObject();
        }
        catch (Exception e){
            System.out.println("error in deserializing game");
        }
        finally {
            try{ in.close(); }
            catch (IOException ignored){}
        }
        return null;
    }

    public void startGame(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        newGamePlay = new GamePlay(stage);
        newGamePlay.display();
    }

    public void help(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("help.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
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
        load.setOnAction(e -> loadSavedGame(stage));

        if (!Main.getGame().getSavedGames().isEmpty()){
            pane.getChildren().add(load);
        }
        pane.getChildren().add(highscore);
        stage.setScene(scene);
        stage.show();
    }

    public void exitGame(ActionEvent event){
        Main.getGame().serialize();
        System.exit(0);
    }
}