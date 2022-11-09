package main.Cases;

import main.Principale.Tour;

import java.awt.*;

/**
 * Class représentant la sortie du labyrinthe
 * @author Anthony Briot
 */
public class Exit extends Case {

    /**
    * Constructeur de la case exit
    * @param x : emplacement de la case en X
    * @param y : emplacement de la case en Y
     */
    public Exit(int x, int y) {
        //Initialisation des attributs
        super(false, x, y, Color.orange);
    }

    /**
     * Getter du type de case
     * @return type de case
     */
    public String getType() {
        return "Exit";
    }

    /**
     * Méthode qui permet de savoir si le héros peut sortir du labyrinthe
     * @param t : Tour courante
     */
    @Override
    public void eventCollider(Tour t) {
        if(t.getCurrentLevel().isKeyFound()){
            if (t.getCurrentLevel().isLastLevel()) {
                t.setFini(true);
            } else {
                t.nextLevel();
            }
            System.out.println("Passage au prochain niveau");
        } else {
            System.out.println("Recuperer la cle pour reussir le niveau !");
        }
    }
}
