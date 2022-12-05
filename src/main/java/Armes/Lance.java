package main.java.Armes;

import main.java.Cases.Case;
import main.java.Engine.DrawingPanel;
import main.java.Principale.Niveau;
import main.java.Principale.Tools;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lance extends Arme{

    private BufferedImage image;

    /**
     * Constructeur d'une lance
     *
     * @param x   : position en X
     * @param y   : position en Y
     * @param pda : points d'attaque
     * @param dir : direction
     */
    public Lance(int x, int y, double pda, String dir, Niveau n) {
        super(x, y, pda, dir, n);
        image = Tools.getImageByName("/images/game/weapon/spear-"+dir);

    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.drawImage(this.image, this.getX(), this.getY() + DrawingPanel.ECART, Case.TAILLE_CASE, Case.TAILLE_CASE, null);
    }

}
