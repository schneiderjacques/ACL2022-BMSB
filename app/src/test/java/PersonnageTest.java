package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import javax.swing.GroupLayout;

import org.junit.*;

import main.java.Controller.ControllerMouvement;

import main.java.Personnages.*;
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
    public void testAttaque(){
        /** TODO : Test de l'attaque du héros
        Heros p = new Heros(2,2,10,1);
        Goomba h = new Goomba(2, 0);
        Goomba d = new Goomba(4, 2);
        Goomba b = new Goomba(2, 4);
        Goomba g = new Goomba(0, 2);

        p.move('X', 1);
        p.attaque(d);
        assertEquals("Le héro s'est deplacé à droite.", 3, p.getX());
        assertEquals("Le monstre a perdu de points de vie.", 14, (int)d.getPDV());
        p.move('X', -1);
        p.move('X', -1);
        p.attaque(g);
        assertEquals("Le monstre a perdu de points de vie.", 14, (int)g.getPDV());
        p.move('X', 1);
        p.move('Y', -1);
        p.attaque(h);
        p.attaque(b);
        assertEquals("Le monstre a perdu de points de vie.", 14, (int)h.getPDV());
        assertEquals("Le monstre n'a pas perdu de points de vie.", 15, (int)b.getPDV());
        p.move('Y', 1);
        p.move('Y', 1);
        p.attaque(b);
        assertEquals("Le monstre a perdu de points de vie.", 14, (int)b.getPDV());
        p.move('X', 1);
        p.move('Y', -1);
        p.attaque(d);
        assertEquals("Le monstre n'a perdu de points de vie.", 14, (int)d.getPDV());
         **/
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
        /** TODO : Test de déplacement du personnage avec le clavier (avec le controlleur) et collisions
        Jeu jeu = new Jeu();
        ControllerMouvement cm = new ControllerMouvement(jeu);
        Button a = new Button("click");
        KeyEvent key = new KeyEvent(a, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        cm.keyPressed(key);
        Assert.assertEquals(jeu.getTour().getHeros().getY(), 1); //Le héros doit rester en position 1 - 1 car au dessus de lui se trouve un mur
        Assert.assertNotEquals(jeu.getTour().getHeros().getY(), 0);
         **/
    }

}