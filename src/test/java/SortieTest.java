package test.java;

import org.junit.*;
import main.Cases.Sortie;

import static org.junit.Assert.*;

/**
 * Class de test de la sortie
 * @author Anthony Briot
 */
public class SortieTest {

    @Test
    public void createSortie() {
        Sortie s = new Sortie(1,1);
        assertFalse(s.getCollision());
    }
}