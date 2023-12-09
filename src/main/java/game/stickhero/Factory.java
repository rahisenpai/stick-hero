//here we have implemented factory design pattern
package game.stickhero;

import javafx.scene.shape.Rectangle;


public class Factory {
    public static Pillar createPillarWithWidth(int width) {
        return new Pillar(width);
    }

    public static Pillar createPillarWithRectangle(Rectangle rectangle) {
        return new Pillar(rectangle);
    }

    public static Stick createStick(Hero hero) {
        return new Stick(hero);
    }
}
