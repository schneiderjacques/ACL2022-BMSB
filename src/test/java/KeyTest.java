package test.java;

import main.Personnages.Heros;
import main.Principale.Jeu;
import main.Principale.Niveau;
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
        for (int i = 2; i <= 2; i++) {
            list.add(new Niveau("src/resources/level_" + i + ".txt", jeu.getTour()));
        }
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        h.moveY(3); h.moveX(4);
        assertEquals(false, jeu.getTour().getCurrentLevel().isKeyFound());
        jeu.getTour().moveHeros('X', 1);
        assertEquals(true, jeu.getTour().getCurrentLevel().isKeyFound());
    }
}
