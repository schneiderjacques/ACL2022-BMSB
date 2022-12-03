package main.java.Cases;

import main.java.Engine.DrawingPanel;
import main.java.Principale.Tools;
import main.java.Principale.Tour;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Passage extends Case{

    /**
     * Constructeur de la classe Case
     *
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Passage(int x, int y) {
        super(true, x, y);
        this.initImage(1);
        this.setImage(Tools.getImageByName("/images/game/objects/passage"),0);
    }

    @Override
    public void eventCollider(Tour t) {
        // ne fait rien
    }

    /**
     * Méthode qui permet de fermer le passage
     */
    public void close(){
        this.setCollision(true);
    }

    @Override
    public String getType() {
        return "Porte";
    }

    /**
     * Méthode qui permet d'ouvrir le passage
     */
    public void open(){
        this.setCollision(false);
    }

    /**
     * Méthode qui permet de dessiner le passage
     * @param image image sur laquelle dessiner
     */
    @Override
    public void draw(BufferedImage image) {
        if (!this.getCollision()) {
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.drawImage(this.getImage(this.getFrame()), this.getY() * TAILLE_CASE, this.getX() * TAILLE_CASE + DrawingPanel.ECART, TAILLE_CASE, TAILLE_CASE, null);
        }
     }
}
