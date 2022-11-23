package test.java;

import main.Personnages.Heros;
import main.Principale.Jeu;
import main.Principale.Niveau;
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
        for (int i = 2; i <= 2; i++) {
            list.add(new Niveau(PassageTest.class.getResourceAsStream("/resources/level_" + i + ".txt"), jeu.getTour()));
        }
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        h.moveX(2);
        //Ouverture du passage
        jeu.getTour().moveHeros('X', 1);
        assertFalse(jeu.getTour().getCurrentLevel().getCase(5, 1).getCollision());
        //Sur le passage
        jeu.getTour().moveHeros('X', 1);
        //Sors de passage, il se ferme
        jeu.getTour().moveHeros('X', 1);
        assertTrue(jeu.getTour().getCurrentLevel().getCase(5, 1).getCollision());
    }
}
