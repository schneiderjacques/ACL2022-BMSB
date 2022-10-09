package src.test.java;

import src.main.Principale.Labyrinthe;
import org.junit.*;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class LabyrintheTest {

    @Test
    public void createLabyrinthe() throws FileNotFoundException {
        Labyrinthe l = new Labyrinthe("src/test/resources/lab_test_1.txt");
        assertEquals(10, l.getLongueur());
        assertEquals(10, l.getLargeur());
    }
}
