package test.java;

import main.java.Main;
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
        /** TODO : Test de l'event de vie
        Jeu jeu = new Jeu();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        ClassLoader loader = Main.class.getClassLoader();
        list.add(new Niveau(loader.getResourceAsStream("test/resources/lab_test_6.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();

        jeu.getTour().moveHeros('X', 1);
        assertEquals(14.0, h.getPDV(),0.1);**/
    }
}
