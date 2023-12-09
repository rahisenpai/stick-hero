package game.stickhero;

import java.io.Serializable;

public class SavedGame implements Serializable {
    private static final long serialVersionUID = 20L;
    private String file,score,cherries;

    SavedGame(Integer score, Integer cherries){
        this.score = score+"";
        this.cherries = cherries+"";
        this.file = "src/main/data/SavedGame.txt";
    }

    public String getCherries() {
        return cherries;
    }
    public String getFile() {
        return file;
    }
    public String getScore() {
        return score;
    }
}
