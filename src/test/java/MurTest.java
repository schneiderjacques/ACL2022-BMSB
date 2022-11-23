package test.java;

import org.junit.*;
import main.java.Cases.Mur;

import static org.junit.Assert.*;

/**
 * Class de test du mur
 * @author Anthony Briot
 */
public class MurTest {

    @Test
    public void createMur() {
        Mur m = new Mur(true, 1,1);
        Mur m2 = new Mur(false,1,1);
        assertFalse(m2.getCollision());
        assertTrue(m.getCollision());
    }
}