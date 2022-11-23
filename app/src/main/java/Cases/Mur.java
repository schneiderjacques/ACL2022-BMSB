package main.java.Cases;

import main.java.Principale.Tour;

import java.awt.*;

/**
* Class représentant les murs du labyrinthe
* @author Anthony Briot
 */
public class Mur extends Case {
    /**
    * Constructeur de la classe Mur
    * @param x : emplacement de la case en X
    * @param y : emplacement de la case en Y
     */
    public Mur(boolean collision, int x, int y) {
        //Initialisation des attributs
        super(collision, x, y, Color.black);
    }

    /**
     * Getter du type de case
     * @return type de case
     */
    public String getType() {
        return "Mur";
    }

    @Override
    public void eventCollider(Tour t) {
        //Ne fait rien
    }
}
