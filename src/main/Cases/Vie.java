package main.Cases;

import main.Personnages.Heros;

/**
 * Représente une case de vie, lorsque le joueur marche dessus il récupère de la vie
 * @author Jacques Schneider
 */
public class Vie extends Case{
    private final double HEAL_AMOUNT = 4;
    /*
     * Constructeur de la case de Vie
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Vie(int x, int y) {
        //Initialisation des attributs
        super(false, x, y);
    }



    /**
     * Getter du type de case
     * @return type de case
     */
    @Override
    public String getType() {
        return "Vie";
    }

    @Override
    public void eventCollider(Heros h) {
        System.out.println("Votre héros a récupéré de la vie");
    }
}
