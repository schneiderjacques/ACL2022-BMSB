package test.java;

import main.Principale.Niveau;
import main.Principale.Tour;
import org.junit.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
}
