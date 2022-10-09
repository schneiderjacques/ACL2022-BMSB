package src.test.java;

import org.junit.*;
import src.main.Cases.Sortie;

import static org.junit.Assert.*;

public class SortieTest {

    @Test
    public void createSortie() {
        Sortie s = new Sortie();
        assertFalse(s.getCollision());
    }
}