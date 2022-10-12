package test.java;

import main.Principale.Niveau;
import main.Principale.Tour;
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
        ArrayList list = new ArrayList<Niveau>();
        list.add(new Niveau("src/test/resources/lab_test_1.txt"));
        Tour t = new Tour(list);
        assertEquals(10, t.getCurrentLevel().getLongueur());
        assertEquals(10, t.getCurrentLevel().getLargeur());
    }

    @Test
    public void nextLevel() throws FileNotFoundException {
        ArrayList list = new ArrayList<Niveau>();
        for (int i = 1; i <= 3; i++) {
            list.add(new Niveau("src/test/resources/lab_test_" + i + ".txt"));
        }
        Tour t = new Tour(list);

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
