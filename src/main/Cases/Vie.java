package main.Cases;

import main.Personnages.Heros;
import main.Principale.Tour;

import java.awt.*;

/**
 * Représente une case de vie, lorsque le joueur marche dessus il récupère de la vie
 * @author Jacques Schneider
 */
public class Vie extends Case{

    /**
     * Constructeur de la case de Vie
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Vie(int x, int y) {
        //Initialisation des attributs
        super(false, x, y, Color.green);
    }



    /**
     * Getter du type de case
     * @return type de case
     */
    @Override
    public String getType() {
        return "Vie";
    }

    /**
     * Méthode qui permet de récupérer des points de vie
     * @param t : Tour courante
     */
    @Override
    public void eventCollider(Tour t) {
        System.out.println("Votre héros vient de récupéré de la vie");
        //Nombre de points de vie récupérés
        double HEAL_AMOUNT = 4;
        t.getHeros().setPDV(t.getHeros().getPDV() + HEAL_AMOUNT);
        t.getCurrentLevel().setCaseToSol(this.getX(), this.getY());
    }
}
