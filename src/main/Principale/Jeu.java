package main.Principale;

import main.Personnages.Heros;

import java.io.FileNotFoundException;

/*
* Class représentant le jeu en lui même
* @author ????
 */
public class Jeu {

    //Labyritnhe du jeu en cours
    private Labyrinthe labyrinthe;

    //Personnage du jeu en cours
    private Heros heros;

    /*
    * Constructeur du jeu
     */
    public Jeu() throws FileNotFoundException {

        //Création du labyrinthe
        this.labyrinthe = new Labyrinthe("src/resources/lab_1.txt");
        //Création du Heros
        this.heros = new Heros(0, 0, 100, 10);
    }

}
