package main.java.Cases;

import main.java.Principale.Tour;

import java.awt.*;

/**
* Class représentant une case sol du labyrinthe
* author Anthony Briot
 */
public class Sol extends Case {
    /**
    * Constructeur de la case sol
    * @param x : emplacement de la case en X
    * @param y : emplacement de la case en Y
     */
    public Sol(int x, int y) {
        //Initialisation des attributs
        super(false, x, y, Color.white);
    }

    /**
     * Getter du type de case
     * @return type de case
     */
    public String getType() {
        return "Sol";
    }

    @Override
    public void eventCollider(Tour t) {
        //Ne fait rien
    }
}
