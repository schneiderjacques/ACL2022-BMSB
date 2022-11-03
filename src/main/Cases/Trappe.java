package main.Cases;

import main.Personnages.Heros;
import main.Principale.Tour;

import java.awt.*;

/**
 * Représente une case piège, lorsque le joueur marche dessus il est immobilisé 3 secondes
 * @author Jacques Schneider
 */
public class Trappe extends Case{
    private final double BLOCKED_TIME = 3;
    /*
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

    @Override
    public void eventCollider(Tour t) {
        System.out.println("Votre héros vient d'être piégé, il ne peut plus bouger pendant 3 secondes");
    }
}
