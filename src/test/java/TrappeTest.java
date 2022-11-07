package test.java;

import main.Personnages.Heros;
import main.Principale.Jeu;
import main.Principale.Niveau;
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
        Jeu jeu = new Jeu();
        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        for (int i = 2; i <= 2; i++) {
            list.add(new Niveau("src/resources/level_" + i + ".txt", jeu.getTour()));
        }
        jeu.getTour().loadNiveaux(list);
        Heros h = jeu.getTour().getHeros();
        h.moveX(11);
        assertEquals(true, h.canMove()); //le joueur peut se déplacer
        jeu.getTour().moveHeros('X', 1);
        assertEquals(false, h.canMove()); //le joueur ne peut plus se déplacer
        Thread.sleep(3000);
        assertEquals(true, h.canMove()); //le joueur peut à nouveau se déplacer
    }
}
