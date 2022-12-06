package test.java;

import main.java.Personnages.Heros;
import main.java.Principale.Jeu;
import main.java.Principale.Niveau;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PassageTest {

    @Test
    public void eventTest() throws FileNotFoundException {
        Jeu jeu = new Jeu();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        list.add(new Niveau(DegatTest.class.getResourceAsStream("/lab_test_7.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        jeu.demarreJeu();
        Heros h = jeu.getTour().getHeros();
        assertTrue(jeu.getTour().getCurrentLevel().getCaseObject(3,1).getCollision());
        jeu.getTour().moveHeros('X', 1);
        assertFalse(jeu.getTour().getCurrentLevel().getCaseObject(3,1).getCollision());
        jeu.getTour().moveHeros('X', 1);
        jeu.getTour().moveHeros('X', 1);
        assertTrue(jeu.getTour().getCurrentLevel().getCaseObject(3,1).getCollision());
    }
}
