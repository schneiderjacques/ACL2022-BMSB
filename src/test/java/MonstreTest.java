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
 * @author Arthur Moitrier, Anthony Briot
 */
public class MonstreTest {

    /**
     * Test de déplacement du monstre
     */
    @Test
    public void testMonstre(){
        /*
        Goomba g = new Goomba(1, 1);

        //Test génération du monstre au coordonnées (1;1)
        assertEquals(1, g.getX());
        assertEquals(1, g.getY());

        // Test de la méthode moveX
        g.move('X', 1);
        assertEquals(2, g.getX());
        g.move('X', -1);
        assertEquals(1, g.getX());

        // Test de la méthode moveY
        g.move('Y', 1);
        assertEquals(2, g.getY());
        g.move('Y', -1);
        assertEquals(1, g.getY());
         */
    }

    /**
     * Test de déplacement du monstre
     */
    @Test
    public void testAttackMonstre(){
        /*
        Goomba g = new Goomba(1, 1);
        Goomba h = new Goomba(3, 1);
        Heros p = new Heros(2,3,10,1);

        g.move('X', 1);
        assertEquals(false, g.attaque(h));
        assertEquals(10, (int)h.getPDV());
        g.move('Y', 1);
        assertEquals(true, g.attaque(p));
        assertEquals(9, (int)p.getPDV());
        g.move('X', -1);
        g.move('X', 1);
        assertEquals(9, (int)p.getPDV());

         */
    }

    /**
     * Test de déplacement aléatoire du monstre
     */
    @Test
    public void testMoovMonstreAlea(){
        //TODO
    }

}