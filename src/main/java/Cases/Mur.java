package main.java.Cases;

import main.java.Engine.DrawingPanel;
import main.java.Principale.Tour;

import java.awt.*;
import java.awt.image.BufferedImage;

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
        super(collision, x, y);
        this.initImage(1);
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
    @Override
    public void draw(BufferedImage image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        if (image != null) {
            g.drawImage(this.getImage(this.getFrame()), this.getY()*TAILLE_CASE, this.getX()*TAILLE_CASE + DrawingPanel.ECART, TAILLE_CASE, TAILLE_CASE, null);
        }
    }
}
