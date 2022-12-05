package test.java;

import main.java.Personnages.Heros;
import main.java.Principale.Jeu;
import main.java.Principale.Niveau;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class KeyTest {
    @Test
    public void eventTest() throws FileNotFoundException {
        Jeu jeu = new Jeu();
        jeu.mute();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        list.add(new Niveau(DegatTest.class.getResourceAsStream("/lab_test_3.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        assertEquals(false, jeu.getTour().getCurrentLevel().isKeyFound());
        jeu.getTour().moveHeros('X', 1);
        assertEquals(true, jeu.getTour().getCurrentLevel().isKeyFound());
    }
}
