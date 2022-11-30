package main.java.Personnages;

import main.java.Cases.Case;
import main.java.Engine.DrawingPanel;
import main.java.Principale.Niveau;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class représentant un personnage du jeu
 * @author Arthur Moitrier
 */
public class Boo extends Monstre{

    /**
     * Constructeur du monstre Boo
     * @param x : position en X
     * @param y : position en Y
     * @param n : niveau dans lequel se trouve le monstre
     */
    public Boo(int x, int y, Niveau n) {
        super(x, y, false, 10, 1, n, Color.MAGENTA, 5);
    }

    /**
     * Constructeur du monstre Boo
     * @param x : position en X
     * @param y : position en Y
     */
    public Boo(int x, int y) {
        super(x, y, false, 10, 1, Color.MAGENTA, 5);
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D crayon = (Graphics2D) image.getGraphics();
        crayon.setColor(this.getColor());
        crayon.fillRect(getX()* Case.TAILLE_CASE,getY()*Case.TAILLE_CASE + DrawingPanel.ECART, Case.TAILLE_CASE,Case.TAILLE_CASE);
    }
}
