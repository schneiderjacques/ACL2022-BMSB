package main.Cases;

import main.Personnages.Heros;
import main.Principale.Tour;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Représente une case piège, lorsque le joueur marche dessus il est immobilisé 3 secondes
 * @author Jacques Schneider
 */
public class Trappe extends Case{
    //Temps d'immobilisation du joueur
    private final double BLOCKED_TIME = 3;

    //Executer pour bloquer le personnage
    private ScheduledExecutorService executor;

    /**
     * Constructeur de la case Piege
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Trappe(int x, int y) {
        //Initialisation des attributs
        super(false, x, y, Color.darkGray);
    }



    /**
     * Getter du type de case
     * @return type de case
     */
    @Override
    public String getType() {
        return "Trappe";
    }

    /**
     * Méthode qui bloque le joueur pendant 3 secondes
     * @param t : tour du jeu
     */
    @Override
    public void eventCollider(Tour t) {
        Heros h = t.getHeros();
        h.setCanMove(false);
        executor = Executors.newSingleThreadScheduledExecutor();
        System.out.println("Vous venez d'entrer dans une trappe, vous êtes bloqué pendant 3 secondes");
        Runnable stopPlayer = () -> {
            h.setCanMove(true);
            stopExecutor();

        };
        this.executor.scheduleAtFixedRate(stopPlayer, 3000, 100, TimeUnit.MILLISECONDS);
    }

    /**
     * Méthode qui arrête l'executor
     */
    public void stopExecutor(){
        this.executor.shutdown();
    }
}
