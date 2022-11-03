package test.java;

import main.Cases.Exit;
import org.junit.*;
import main.Cases.Exit;

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
}
