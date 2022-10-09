package src.test.java;

import org.junit.*;
import src.main.Cases.Mur;

import static org.junit.Assert.*;

public class MurTest {

    @Test
    public void createMur() {
        Mur m = new Mur(true);
        Mur m2 = new Mur(false);
        assertFalse(m2.getCollision());
        assertTrue(m.getCollision());
    }
}