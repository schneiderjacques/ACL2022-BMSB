package main.java.Cases;

import main.java.Engine.DrawingPanel;
import main.java.Principale.Tour;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class représentant une case sol du labyrinthe
 * author Anthony Briot
 */
public class Sol extends Case {
    /**
     * Constructeur de la case sol
     *
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Sol(int x, int y) {
        //Initialisation des attributs
        super(false, x, y, Color.white);
        this.initImage(1);
    }

    /**
     * Getter du type de case
     *
     * @return type de case
     */
    public String getType() {
        return "Sol";
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.drawImage(this.getImage(this.getFrame()), this.getY() * TAILLE_CASE, this.getX() * TAILLE_CASE + DrawingPanel.ECART, TAILLE_CASE, TAILLE_CASE, null);
    }

    @Override
    public void eventCollider(Tour t) {
        //Ne fait rien
    }
}
