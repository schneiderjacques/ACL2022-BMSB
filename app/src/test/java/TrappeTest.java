package test.java;

import main.java.Main;
import main.java.Personnages.Heros;
import main.java.Principale.Jeu;
import main.java.Principale.Niveau;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TrappeTest {
    @Test
    public void eventTest() throws FileNotFoundException, InterruptedException {
        /** TODO : Test de l'event de trappe
        Jeu jeu = new Jeu();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        ClassLoader loader = Main.class.getClassLoader();
        list.add(new Niveau(loader.getResourceAsStream("test/resources/lab_test_5.txt"), jeu.getTour()));
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        h.moveX(11);
        assertEquals(true, h.canMove()); //le joueur peut se déplacer
        jeu.getTour().moveHeros('X', 1);
        assertEquals(false, h.canMove()); //le joueur ne peut plus se déplacer
        Thread.sleep(3000);
        assertEquals(true, h.canMove()); //le joueur peut à nouveau se déplacer
         **/
    }
}
