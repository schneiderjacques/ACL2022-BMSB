package test.java;

import main.java.Personnages.Boo;
import main.java.Personnages.Goomba;
import main.java.Personnages.Heros;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Class de test du labyrinthe
 * @author Arthur Moitrier, Anthony Briot
 */
public class MonstreTest {

    /**
     * Test de déplacement du monstre
     */
    @Test
    public void testMonstre() {
        Goomba g = new Goomba(1, 1);

        //Test génération du monstre au coordonnées (1;1)
        assertEquals(1, g.getX());
        assertEquals(1, g.getY());

        // Test de la méthode moveX
        g.move('X', 1);
        assertEquals(2, g.getX());
        g.move('X', -1);
        assertEquals(1, g.getX());

        // Test de la méthode moveY
        g.move('Y', 1);
        assertEquals(2, g.getY());
        g.move('Y', -1);
        assertEquals(1, g.getY());
    }

    /**
     * Test de déplacement du monstre
     */
    @Test
    public void testAttackMonstre(){
        /** TODO
        Goomba g = new Goomba(1, 1);
        Goomba h = new Goomba(3, 1);
        Heros p = new Heros(2,3,10,1);

        g.move('X', 1);
        assertFalse(g.attaque(h));
        assertEquals(10, (int)h.getPDV());
        g.move('Y', 1);
        assertTrue(g.attaque(p));
        assertEquals(9, (int)p.getPDV());
        g.move('X', -1);
        g.move('X', 1);
        assertEquals(9, (int)p.getPDV());
         **/
    }

    @Test 
    public void testViewMonstre(){
        Goomba g = new Goomba(1, 1); 
        Boo b = new Boo(4,4);
        Heros h = new Heros(1,4,10,10); 
        assertEquals(true, g.inView(h));
        assertEquals(true, b.inView(h));
    }

    @Test
    public void testMoveDir(){
        Goomba g = new Goomba(1, 1); 
        Boo b = new Boo(4,4); 
        Heros h = new Heros(1,4,10,10);
        g.moveDir(h,false);
        b.moveDir(h,false);
        assertEquals(2, g.getY());
        assertEquals(3, b.getX());
    }

}