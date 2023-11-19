package com.game.stickhero;

import javafx.scene.image.Image;


public class Hero {
    final private int height, width;
    private Stick stick;
    final private Image avatar;

    //method declarations
    //public void move();
    //public void fall();
    //public void flip();

    Hero(int h, int w, Image image){
        this.height = h;
        this.width = w;
        this.avatar = image;
        this.stick = new Stick(10);
    }
}