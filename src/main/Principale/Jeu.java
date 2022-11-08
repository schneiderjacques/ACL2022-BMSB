package main.Principale;

import main.Engine.Cmd;
import main.Engine.Game;

import java.io.FileNotFoundException;

/**
* Class représentant le jeu en lui même
* @author Jacques Schneider
 */
public class Jeu implements Game {

    //Labyritnhe du jeu en cours
    private final Tour tour;

    /**
     * Constructeur du jeu
     */
    public Jeu() throws FileNotFoundException {
        //Création du labyrinthe
        this.tour = new Tour();
    }

    /**
     * Getter de la tour
     * @return Tour
     */
    public Tour getTour() { return this.tour; }

    @Override
    public void evolve(Cmd commande) {
        //Evolution du jeu
    }

    /**
     * Méthode permettant de savoir si le jeu est fini
     * @return boolean
     */
    @Override
    public boolean isFinished() {
        return false;
    }
}
