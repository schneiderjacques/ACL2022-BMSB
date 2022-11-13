package main.Principale;

import main.Engine.Cmd;
import main.Engine.Game;

import java.io.FileNotFoundException;

/**
* Class représentant le jeu en lui même
* @author Jacques Schneider
 */


public class Jeu implements Game {

    /**
     * Etat du jeu, 0 = Menu démarrer, 1 = Affichage du jeu
     */
    private int gameState;

    //Labyritnhe du jeu en cours
    private final Tour tour;

    /**
     * Constructeur du jeu
     */
    public Jeu() throws FileNotFoundException {
        // Mise en place de l'état du Jeu
        this.gameState = 0;
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
        return isWon() || isLost();
    }

    /**
     * Méthode permettant de savoir si le jeu est gagné
     * @return boolean
     */
    public boolean isWon() {
        return tour.isWon();
    }

    /**
     * Méthode permettant de savoir si le jeu est perdu
     * @return boolean
     */
    public boolean isLost() {
        return tour.isLost();
    }

    /**
     * Démarre le jeu après le menu
     */
    public void demarreJeu() {
        this.gameState = 1;
        tour.demarreTour();
    }

    /**
     * Getter de GameState
     * @return gameState int 0 = menu, 1 = jeu
     * */
    public int getGameState(){
        return this.gameState;
    }

    /**
     * Setter de GameState
     * @param gameState entier représentant l'état du jeu
     * Nouvelle valeur de gameState
     * */
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
}
