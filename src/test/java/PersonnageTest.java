package test.java;

import static org.junit.Assert.assertEquals;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import javax.swing.GroupLayout;

import org.junit.*; 

import main.Controller.ControllerMouvement;

import main.Personnages.*;
import main.Principale.Jeu;

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
        Heros p = new Heros(2,2,10,1);
        Goomba h = new Goomba(2, 0, 10, 0); 
        Goomba d = new Goomba(4, 2, 10, 0); 
        Goomba b = new Goomba(2, 4, 10, 0); 
        Goomba g = new Goomba(0, 2, 10, 0);

        System.out.println(p.getX());
        p.move(1, 0);
        p.attaque(d);
        System.out.println(p.getX());
        System.out.println(d.getPDV());
        p.move(-2,0);
        p.attaque(g);
        System.out.println(g.getPDV());
        p.move(1, 0);
        p.move(0, -1);
        p.attaque(h);
        p.attaque(b);
        System.out.println(h.getPDV());
        System.out.println(b.getPDV());
        p.move(0, 2);
        p.attaque(b);
        System.out.println(b.getPDV());
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
     * @throws FileNotFoundException
     */
    @Test
    public void movePlayerInWall() throws FileNotFoundException {
        Jeu jeu = new Jeu();
        ControllerMouvement cm = new ControllerMouvement(jeu);
        Button a = new Button("click");
        KeyEvent key = new KeyEvent(a, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');
        cm.keyPressed(key);
        Assert.assertEquals(jeu.getTour().getHeros().getY(), 1); //Le héros doit rester en position 1 - 1 car au dessus de lui se trouve un mur
        Assert.assertNotEquals(jeu.getTour().getHeros().getY(), 0);
    }

}