package main.Cases;

import main.Engine.DrawingPanel;
import main.Engine.GamePainter;
import main.Principale.Tour;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
* Class représentant une case du labyrinthe
* @author Anthony Briot, Silvio Brancati
 */
public abstract class Case implements GamePainter {

    //Position de la case en X
    private final int x;

    //Position de la case en Y
    private final int y;

    //Taille d'une case en pixels
    public static final int TAILLE_CASE = 16*3;

    //Couleur de la case
    private Color color;

    //le joueur entre en collision avec la case ou non
    private boolean collision;

    /**
    * Constructeur de la classe Case
    * @param collision : le joueur entre en collision avec la case ou non
     */
    public Case(boolean collision, int x, int y, Color color){
        //Initialisation des attributs
        this.x = x;
        this.y = y;
        this.collision = collision;
        this.color = color;
    }

    /**
     * Méthode de dessin de la case dans le jeu
     * @param im image sur laquelle dessiner
     */
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(this.color);
        crayon.fillRect(y*TAILLE_CASE,x*TAILLE_CASE + DrawingPanel.ECART,TAILLE_CASE,TAILLE_CASE);
    }

    /**
    * Getter de collision
    * @return collision : le joueur entre en collision avec la case ou non
     */
    public boolean getCollision() {
        return this.collision;
    }

    /**
     * Méthode qui est appelée lorsque le héros se déplace sur la case
     * @param t : Tour du jeu
     */
    public abstract void eventCollider(Tour t);

    /**
     * Getter du type de case
     * @return type de case
     */
    public abstract String getType();
    /**
     * Getter du x de la case
     * @return int du x de la case
     */
    public int getX() {
        return x;
    }
    /**
     * Getter du y de la case
     * @return int du y de la case
     */
    public int getY() {
        return y;
    }

    public void setColor(Color c){
        this.color = c;
    }

    public void setCollision(Boolean c){
        this.collision = c;
    }
}
