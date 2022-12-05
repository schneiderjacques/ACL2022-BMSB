package test.java;

import main.java.Personnages.Heros;
import main.java.Principale.Jeu;
import main.java.Principale.Niveau;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class VieTest {

    @Test
    public void eventTest() throws FileNotFoundException {
        Jeu jeu = new Jeu();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        list.add(new Niveau(DegatTest.class.getResourceAsStream("/lab_test_5.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        h.setPDV(6);
        jeu.getTour().moveHeros('X', 1);
        assertEquals(10.0, h.getPDV(),0.1);
    }

    /**
     * Test si le héros ne peut pas dépasser sa vie maximale
     * @throws FileNotFoundException
     */
    @Test
    public void limitedLife() throws FileNotFoundException {
        Jeu jeu = new Jeu();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        list.add(new Niveau(DegatTest.class.getResourceAsStream("/lab_test_5.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        jeu.getTour().moveHeros('X', 1);
        assertEquals(10.0, h.getPDV(),0.1);
    }
}
