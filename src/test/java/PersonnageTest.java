import org.junit.*;

import main.Personnages.Personnage;

import static org.junit.Assert.assertEquals;

public class PersonnageTest {

    public static void testPXY(int x, int y, Personnage p){
        System.out.println("Avant : "+p.getX()+" ; "+p.getY());
        p.move(x, y);
        System.out.println("Avant : "+p.getX()+" ; "+p.getY());
    }

    @Test
    public void testPersonnage(){
        Personnage p = new Personnage(0, 0, 0, 0); 
        testPXY(1, 0, p);
        testPXY(0, 1, p);
        testPXY(0, -1, p);
        testPXY(-1, 0, p);
        assertEquals(p, p);
    }
    
}
