package test.java;

import org.junit.*;
import main.Cases.Mur;

import static org.junit.Assert.*;

/**
 * Class de test du mur
 * @author Anthony Briot
 */
public class MurTest {

    @Test
    public void createMur() {
        Mur m = new Mur(true);
        Mur m2 = new Mur(false);
        assertFalse(m2.getCollision());
        assertTrue(m.getCollision());
    }
}