package src.test.java;

import org.junit.*;

import src.main.Personnages.Personnage;

import static org.junit.Assert.assertEquals;

public class PersonnageTest {

    public static void testPXY(int x, int y, Personnage p){
        System.out.println("Avant : "+p.toString());
        p.move(x, y);
        System.out.println("Avant : "+p.toString());
    }

    @Test
    public void testPersonnage(){
        Personnage p = new Personnage(0, 0, 0, 0); 
        testPXY(1, 0, p);
        testPXY(0, 1, p);
        testPXY(0, -1, p);
        testPXY(-1, 0, p);

    }
    
}
