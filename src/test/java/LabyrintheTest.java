package test.java;

import main.Principale.Labyrinthe;
import org.junit.*;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Class de test du labyrinthe
 * @author Silvio Brancati
 */
public class LabyrintheTest {

    @Test
    public void createLabyrinthe() throws FileNotFoundException {
        Labyrinthe l = new Labyrinthe("src/test/resources/lab_test_1.txt");
        assertEquals(10, l.getLongueur());
        assertEquals(10, l.getLargeur());
    }
}
