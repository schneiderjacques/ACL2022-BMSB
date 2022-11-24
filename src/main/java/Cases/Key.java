package main.java.Cases;

import main.java.Engine.DrawingPanel;
import main.java.Engine.Sound;
import main.java.Principale.Tools;
import main.java.Principale.Tour;

import java.awt.*;
import java.awt.image.BufferedImage;

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
        this.initImage(1);
        this.setImage(Tools.getImageByName("/images/game/objects/key_found"),0);
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
    @Override
    public void draw(BufferedImage image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        if (image != null) {
            g.drawImage(this.getImage(this.getFrame()), this.getY()*TAILLE_CASE, this.getX()*TAILLE_CASE + DrawingPanel.ECART, TAILLE_CASE, TAILLE_CASE, null);
        }
    }
}
