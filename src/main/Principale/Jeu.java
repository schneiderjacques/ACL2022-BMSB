package main.Principale;

import java.io.FileNotFoundException;

/*
* Class représentant le jeu en lui même
* @author ????
 */
public class Jeu {

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

}
