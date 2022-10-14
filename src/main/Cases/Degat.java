package main.Cases;

import main.Personnages.Heros;

/**
 * Représente une case de dégat, lorsque le joueur marche dessus il subit des dégats
 * @author Jacques Schneider
 */
public class Degat extends Case{
    private final double DAMAGE_AMOUNT = 4;
    /*
     * Constructeur de la case Degat
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Degat(int x, int y) {
        //Initialisation des attributs
        super(false, x, y);
    }



    /**
     * Getter du type de case
     * @return type de case
     */
    @Override
    public String getType() {
        return "Degat";
    }

    @Override
    public void eventCollider(Heros h) {
        System.out.println("Votre héros a perdu de la vie !_!");
    }
}
