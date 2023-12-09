package game.stickhero;

import javafx.scene.image.ImageView;
import org.junit.Test;
import static org.junit.Assert.*;


public class Tests {
    @Test
    public void testHeroInversion(){
        Hero hero = new Hero(1,1,new ImageView(),null);
        assertFalse(hero.getInverted());
    }

    @Test
    public void testGetCollectedCherry(){
        GamePlay game = new GamePlay(null);
        assertFalse(game.getCollectedCherry());
    }

    @Test
    public void testCherries(){
        GamePlay game = new GamePlay(null);
        int i = game.getCherries();
        assertEquals(0,i);
    }

    @Test
    public void testCherries1(){
        GamePlay game = new GamePlay(null);
        int i = game.getCherries()+4;
        game.setCherries(i);
        int j = game.getCherries();
        assertEquals(4,j);
    }

    @Test
    public void testScore(){
        GamePlay game = new GamePlay(null);
        int i = game.getScore();
        assertEquals(0,i);
    }

    @Test
    public void testScore1(){
        GamePlay game = new GamePlay(null);
        int i = game.getScore()+4;
        game.setScore(i);
        int j = game.getScore();
        assertEquals(4,j);
    }

    @Test
    public void testWillFall1(){
        Stick stick = new Stick(10,null);
        boolean b = stick.willFall(60,70,80);
        assertTrue(b);
    }

    @Test
    public void testWillFall2(){
        Stick stick = new Stick(10,null);
        boolean b = stick.willFall(60,100,80);
        assertFalse(b);
    }
}
