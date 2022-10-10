package test.java;

import org.junit.Assert;
import org.junit.Test;
import main.Controller.ControllerMouvement;
import main.Personnages.Heros;
import main.Personnages.Personnage;
import main.Principale.Jeu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

public class CollisionTest {



    @Test
    public void movePlayerInWall() throws FileNotFoundException {
        Jeu jeu = new Jeu();
        jeu.getHeros().move(1,1);
        ControllerMouvement cm = new ControllerMouvement(jeu);
        Button a = new Button("click");
        KeyEvent key = new KeyEvent(a, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        cm.keyPressed(key);
        Assert.assertEquals(jeu.getHeros().getY(), 1); //Le héros doit rester en position 1 - 1 car au dessus de lui se trouve un mur
        Assert.assertNotEquals(jeu.getHeros().getY(), 0);
    }


}
