package game.stickhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Cherries extends GameObject {
    private int x, y;
    private ImageView node;
    private Image img;

    Cherries(ImageView node){
        this.node = node;
        this.img = node.getImage();
    }
}