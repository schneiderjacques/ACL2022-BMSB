package test.java;

import org.junit.*;
import main.Cases.Case;

import static org.junit.Assert.*;

public class CaseTest {

    @Test
    public void createCase() {
        Case c = new Case(true);
        Case c2 = new Case(false);
        assertTrue(c.getCollision());
        assertFalse(c2.getCollision());
    }
}