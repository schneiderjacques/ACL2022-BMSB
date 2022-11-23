package test.java;

import main.java.Principale.Niveau;
import main.java.Principale.Tour;
import org.junit.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Class de test du labyrinthe
 * @author Silvio Brancati
 */
public class TourTest {

    @Test
    public void createTour() throws FileNotFoundException {
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        Tour t = new Tour();
        list.add(new Niveau(TourTest.class.getResourceAsStream("/lab_test_1.txt"), t));

        t.loadNiveaux(list);
        assertEquals(10, t.getCurrentLevel().getLongueur());
        assertEquals(10, t.getCurrentLevel().getLargeur());
    }

    @Test
    public void nextLevel() throws FileNotFoundException {
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        Tour t = new Tour();
        for (int i = 1; i <= 3; i++) {
            list.add(new Niveau(TourTest.class.getResourceAsStream("/lab_test_" + i + ".txt"), t));
        }
        t.loadNiveaux(list);

        for (int i = 1; i <= 2; i++) {
            assertEquals(10, t.getCurrentLevel().getLongueur());
            assertEquals(10, t.getCurrentLevel().getLargeur());
            assertFalse(t.getCurrentLevel().isLastLevel());
            t.nextLevel();
        }

        // le dernier niveau a l'attribut lastLevel à true
        t.nextLevel();
        assertTrue(t.getCurrentLevel().isLastLevel());
    }
}
