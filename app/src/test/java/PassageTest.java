package test.java;

import main.java.Main;
import main.java.Personnages.Heros;
import main.java.Principale.Jeu;
import main.java.Principale.Niveau;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PassageTest {

    @Test
    public void eventTest() throws FileNotFoundException {
        /** TODO : Test de l'event de passage de niveau
        Jeu jeu = new Jeu();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        ClassLoader loader = Main.class.getClassLoader();
        list.add(new Niveau(loader.getResourceAsStream("test/resources/lab_test_4.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        h.moveX(2);
        //Ouverture du passage
        jeu.getTour().moveHeros('X', 1);
        assertFalse(jeu.getTour().getCurrentLevel().getCase(5, 1).getCollision());
        //Sur le passage
        jeu.getTour().moveHeros('X', 1);
        //Sors de passage, il se ferme
        jeu.getTour().moveHeros('X', 1);
        assertTrue(jeu.getTour().getCurrentLevel().getCase(5, 1).getCollision());
         */
    }
}
