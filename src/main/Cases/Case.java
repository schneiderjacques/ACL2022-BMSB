package main.Cases;

import main.Engine.GamePainter;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
* Class représentant une case du labyrinthe
* @author Anthony Briot
 */
public abstract class Case implements CaseInterface, GamePainter {

    //Position de la case en X
    private int x;

    //Position de la case en Y
    private int y;

    private static final int TAILLE_CASE = 16*3;

    private Color color;

    //le joueur entre en collision avec la case ou non
    private boolean collision;

    /*
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

    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(this.color);
        crayon.fillRect(y*TAILLE_CASE,x*TAILLE_CASE,TAILLE_CASE,TAILLE_CASE);
    }

    /*
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
}
