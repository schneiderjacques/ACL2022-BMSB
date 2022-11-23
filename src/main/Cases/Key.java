package main.Cases;

import main.Engine.Sound;
import main.Personnages.Heros;
import main.Principale.Tour;

import java.awt.*;

/**
 * Représente la clé récupérée par le joueur permettant d'ouvrire la case sortie
 * @author Jacques Schneider
 */
public class Key extends Case{

    /**
     * Constructeur de la case Key
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Key(int x, int y) {
        //Initialisation des attributs
        super(false, x, y, Color.yellow);
    }



    /**
     * Getter du type de case
     * @return type de case
     */
    @Override
    public String getType() {
        return "Key";
    }

    /**
     * Méthode permettant de récupérer la clé
     * @param t : tour courante
     */
    @Override
    public void eventCollider(Tour t) {
        t.getCurrentLevel().setKeyFound(true);
        t.getCurrentLevel().setCaseToSol(this.getX(), this.getY());
        Sound sound = new Sound();
        sound.setFile(2);
        sound.play();
    }
}
