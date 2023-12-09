package game.stickhero;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class Main{
    private static Game game;

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Tests.class);
        System.out.println("----- T E S T S -----");
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        System.out.println("---------------------");
        File gameData = new File("src/main/data/StickHero.txt");
        if (gameData.exists()){
            game = deserializeGame();
        } else {
            game = new Game();
        }
        int i = game.getHighscore();
        game.init(i);
    }

    public static Game deserializeGame() {
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new FileInputStream("src/main/data/StickHero.txt"));
            return (Game) in.readObject();
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

    public static Game getGame() {
        return game;
    }
}