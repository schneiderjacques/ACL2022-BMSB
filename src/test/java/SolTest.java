package src.test.java;

import org.junit.*;
import src.main.Cases.Sol;

import static org.junit.Assert.*;

public class SolTest {

    @Test
    public void createSol() {
        Sol s = new Sol();
        assertFalse(s.getCollision());
    }
}