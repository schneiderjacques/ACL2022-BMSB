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
        super(true, x, y, Color.BLACK);
        this.initImage(2);
        this.setImage(Tools.getImageByName("/images/game/objects/passage"),0);
    }

    @Override
    public void eventCollider(Tour t) {
        this.setCollision(true);
    }

    @Override
    public String getType() {
        return "Porte";
    }

    public void open(){
        this.setCollision(false);
    }
    @Override
    public void draw(BufferedImage image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(this.getColor());
        if (!this.getCollision()) {
            g.drawImage(this.getImage(this.getFrame()), this.getY() * TAILLE_CASE, this.getX() * TAILLE_CASE + DrawingPanel.ECART, TAILLE_CASE, TAILLE_CASE, null);
        }
     }
}
