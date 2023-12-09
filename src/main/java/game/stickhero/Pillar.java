package game.stickhero;

import javafx.scene.shape.Rectangle;


public class Pillar extends GameObject {
    private int x, y, distance, width;
    private Rectangle rectangle;

    Pillar(int w){
        this.width = w;
        this.rectangle = new Rectangle(w,130);
    }
    Pillar(Rectangle r){
        this.rectangle = r;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }
}