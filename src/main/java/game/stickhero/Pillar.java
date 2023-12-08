package game.stickhero;

import javafx.scene.shape.Rectangle;


public class Pillar {
    private int x, y, distance, width;
    private Rectangle rectangle;

    Pillar(int w){
        this.width = w;
    }
    Pillar(Rectangle r){
        this.rectangle = r;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }
}