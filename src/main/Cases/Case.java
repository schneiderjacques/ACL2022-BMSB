package main.Cases;

import main.Engine.GamePainter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
* Class représentant une case du labyrinthe
* @author Anthony Briot, Silvio Brancati
 */
public abstract class Case implements CaseInterface, GamePainter {

    //Position de la case en X
    private final int x;

    //Position de la case en Y
    private final int y;

    //Taille d'une case en pixels
    public static final int TAILLE_CASE = 16*3;

    //Couleur de la case
    private final Color color;

    //le joueur entre en collision avec la case ou non
    private final boolean collision;

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
        crayon.fillRect(y*TAILLE_CASE,x*TAILLE_CASE,TAILLE_CASE,TAILLE_CASE);
    }

    /**
    * Getter de collision
    * @return collision : le joueur entre en collision avec la case ou non
     */
    public boolean getCollision() {
        return this.collision;
    }

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
}
