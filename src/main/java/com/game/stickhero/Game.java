package com.game.stickhero;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;


public class Game extends javafx.application.Application {
    int score;
    private Hero hero;
    private Pillar pillar;
    private Pillar nextPillar;

    //method declarations
    //public void update();
    //public void checkStick();

    @Override
    public void start(Stage stage) throws IOException {
        this.hero = new Hero(75,65,new Image(Objects.requireNonNull(Main.class.getResourceAsStream("hero.png"))));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("hero.png"))));
        stage.setTitle("stick-hero");
        stage.setScene(scene);
        stage.show();
    }

    public static void begin(String[] args) {
        launch(args);
    }
}