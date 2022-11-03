package main.Cases;

import main.Personnages.Heros;
import main.Principale.Tour;

/**
 * Représente la clé récupérée par le joueur permettant d'ouvrire la case sortie
 * @author Jacques Schneider
 */
public class Key extends Case{

    /*
     * Constructeur de la case Key
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Key(int x, int y) {
        //Initialisation des attributs
        super(false, x, y);
    }



    /**
     * Getter du type de case
     * @return type de case
     */
    @Override
    public String getType() {
        return "Key";
    }

    @Override
    public void eventCollider(Tour t) {
        System.out.println("Votre héros vient de récupéré la clé de sortie");
    }
}
