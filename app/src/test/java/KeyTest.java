package test.java;

import main.java.Main;
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
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        ClassLoader loader = Main.class.getClassLoader();
        list.add(new Niveau(loader.getResourceAsStream("test/resources/lab_test_3.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        assertEquals(false, jeu.getTour().getCurrentLevel().isKeyFound());
        jeu.getTour().moveHeros('X', 1);
        assertEquals(true, jeu.getTour().getCurrentLevel().isKeyFound());
    }
}
