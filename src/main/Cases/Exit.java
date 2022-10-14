package main.Cases;

import main.Personnages.Heros;
import main.Principale.Tour;

/*
 * Class représentant la sortie du labyrinthe
 * @author Anthony Briot
 */
public class Exit extends Case {
    /*
    * Constructeur de la case exit
    * @param x : emplacement de la case en X
    * @param y : emplacement de la case en Y
     */
    public Exit(int x, int y) {
        //Initialisation des attributs
        super(false, x, y);
    }

    /**
     * Getter du type de case
     * @return type de case
     */
    public String getType() {
        return "Exit";
    }

    @Override
    public void eventCollider(Tour t) {
        System.out.println("Passage au prochain niveau !");
    }
}
