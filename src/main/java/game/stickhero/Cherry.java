//here we have implemented singleton design pattern
package game.stickhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Cherry extends GameObject {
    private static Cherry cherry = null;
    private ImageView node;
    private Image img;

    public static Cherry getInstance(ImageView node){
        if (cherry == null){
            cherry = new Cherry(node);
        }
        return cherry;
    }

    private Cherry(ImageView node){
        this.node = node;
        this.img = node.getImage();
    }

    public ImageView getNode() {
        return node;
    }
}