package test.java;

import main.Cases.Exit;
import main.Personnages.Heros;
import main.Principale.Jeu;
import main.Principale.Niveau;
import org.junit.*;
import main.Cases.Exit;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Class de test de la sortie
 * @author Anthony Briot
 */
public class ExitTest {

    @Test
    public void createSortie() {
        Exit s = new Exit(1,1);
        assertFalse(s.getCollision());
    }
    @Test
    public void eventTest() throws FileNotFoundException {
        Jeu jeu = new Jeu();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        for (int i = 2; i <= 2; i++) {
            list.add(new Niveau(ExitTest.class.getResourceAsStream("/resources/level_" + i + ".txt"), jeu.getTour()));
        }
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        h.moveY(3); h.moveX(13);
        jeu.getTour().getCurrentLevel().setKeyFound(true);
        /*
        * Implémentation  plus tard du test
        * */
        jeu.getTour().moveHeros('X', 1);
    }
}
