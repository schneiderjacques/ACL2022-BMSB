package test.java;

import main.java.Personnages.Heros;
import main.java.Principale.Jeu;
import main.java.Principale.Niveau;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TrappeTest {
    @Test
    public void eventTest() throws FileNotFoundException, InterruptedException {
        /** TODO
        Jeu jeu = new Jeu();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        list.add(new Niveau(DegatTest.class.getResourceAsStream("/lab_test_4.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        assertEquals(true, h.canMove()); //le joueur peut se déplacer
        jeu.getTour().moveHeros('X', 1);
        assertEquals(false, h.canMove()); //le joueur ne peut plus se déplacer
        Thread.sleep(3000);
        assertEquals(true, h.canMove()); //le joueur peut à nouveau se déplacer**/
    }
}
