package test.java;

import org.junit.*;
import main.java.Cases.Sol;

import static org.junit.Assert.*;

/**
 * Class de test du sol
 * @author Anthony Briot
 */
public class SolTest {

    @Test
    public void createSol() {
        Sol s = new Sol(1,1);
        assertFalse(s.getCollision());
    }
}