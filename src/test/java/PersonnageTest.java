package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import main.java.Personnages.Goomba;
import main.java.Personnages.Heros;
import main.java.Principale.Niveau;
import org.junit.*;

import main.java.Controller.ControllerMouvement;

import main.java.Principale.Jeu;

/**
 * Class de test du labyrinthe
 * @author Martin Gurtner, Arthur Moitrier
 */
public class PersonnageTest {


    /**
     *
     */
    @Test
    public void testAttaque() throws FileNotFoundException {
        Jeu jeu = new Jeu();
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        list.add(new Niveau(DegatTest.class.getResourceAsStream("/lab_test_1.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        //h.move('X', 1);
        //h.move('X', 1);
        //jeu.getTour().heroAttaque();
    }
    /**
     * Test de déplacement du personnage
     */
    @Test
    public void testPersonnage(){
        Heros h = new Heros();
        //Test génération du personnage au coordonnées (1;1)
        assertEquals(1, h.getX());
        assertEquals(1, h.getY());

        // Test de la méthode moveX
        h.move('X', 1);
        assertEquals(2, h.getX());
        h.move('X', -1);
        assertEquals(1, h.getX());

        // Test de la méthode moveY
        h.move('Y', 1);
        assertEquals(2, h.getY());
        h.move('Y', -1);
        assertEquals(1, h.getY());
    }

    /**
     * Test de déplacement du personnage avec le clavier (avec le controlleur) et collisions
     */
    @Test
    public void movePlayerInWall() throws FileNotFoundException {
        /** TODO
        Jeu jeu = new Jeu();
        ControllerMouvement cm = new ControllerMouvement(jeu);
        Button a = new Button("click");
        KeyEvent key = new KeyEvent(a, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        cm.keyPressed(key);
        Assert.assertEquals(jeu.getTour().getHeros().getY(), 1); //Le héros doit rester en position 1 - 1 car au dessus de lui se trouve un mur
        Assert.assertNotEquals(jeu.getTour().getHeros().getY(), 0);**/
    }

}