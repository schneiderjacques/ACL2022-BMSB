package main.Principale;

import main.Engine.Cmd;
import main.Engine.Game;

import java.io.FileNotFoundException;

/*
* Class représentant le jeu en lui même
* @author Jacques Schneider
 */
public class Jeu implements Game {

    //Labyritnhe du jeu en cours
    private Tour tour;

    /*
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
        /*System.out.println("Execute "+commande);*/
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
