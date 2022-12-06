package test.java;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import main.java.Personnages.Goomba;
import main.java.Personnages.Heros;
import main.java.Personnages.Monstre;
import main.java.Principale.Niveau;
import org.junit.*;

import main.java.Controller.ControllerMouvement;

import main.java.Principale.Jeu;

import static org.junit.Assert.*;

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
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        list.add(new Niveau(DegatTest.class.getResourceAsStream("/lab_test_6.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        jeu.demarreJeu();
        Heros h = jeu.getTour().getHeros();
        h.moveY( 5);
        h.move('X', 1);
        h.move('X', 1);
        Monstre m = jeu.getTour().getCurrentLevel().getMonsterInFront(h);
        assertTrue(jeu.getTour().getCurrentLevel().getMonstres().contains(m));
        h.setLm("b");
        //pas de monstre devant lui
        assertEquals(jeu.getTour().getCurrentLevel().getMonsterInFront(h), null);
        h.setLm("d");
        assertTrue(jeu.getTour().getCurrentLevel().getMonstres().contains(m));
        h.attaque(m);
        assertEquals((int)m.getPDV(), (int)5);
        h.setCanAttack(true);
        h.attaque(m);
        assertEquals((int)m.getPDV(), (int)0);
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
        Jeu jeu = new Jeu();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        list.add(new Niveau(DegatTest.class.getResourceAsStream("/lab_test_6.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        jeu.demarreJeu();
        Heros h = jeu.getTour().getHeros();
        assertEquals(1, h.getX());
        jeu.getTour().moveHeros('X', -1);
        assertEquals(1, h.getX());
    }

}