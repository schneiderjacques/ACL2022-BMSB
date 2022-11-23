package test.java;

import main.java.Cases.Exit;
import main.java.Personnages.Heros;
import main.java.Principale.Jeu;
import main.java.Principale.Niveau;
import org.junit.*;

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
        list.add(new Niveau(DegatTest.class.getResourceAsStream("/lab_test_2.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        jeu.getTour().getCurrentLevel().setKeyFound(true);
        /*
        * Implémentation  plus tard du test
        * */
        jeu.getTour().moveHeros('X', 1);
    }
}
