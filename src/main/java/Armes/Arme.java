package main.java.Armes;

import main.java.Engine.DrawingPanel;
import main.java.Engine.GamePainter;
import main.java.Cases.Case;
import main.java.Personnages.Monstre;
import main.java.Principale.Jeu;
import main.java.Principale.Niveau;
import main.java.Principale.Tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Class représentant une arme du jeu
 *
 * @author Arthur Moitrier
 */
public abstract class Arme implements GamePainter {

    //Position en X
    private int x;

    //Position en Y
    private int y;

    //Degat de l'arme
    private final double pda;

    //Direction de l'arme
    private String direction;

    private int ttl = 20;

    private boolean canAttack = true;

    /**
     * Constructeur du personnage
     *
     * @param x   : position en X
     * @param y   : position en Y
     * @param pda : points d'attaque
     * @param dir : direction
     */
    public Arme(int x, int y, double pda, String dir) {
        //Initialisation des attributs
        this.x = x*Case.TAILLE_CASE;
        this.y = y*Case.TAILLE_CASE;
        this.pda = pda;
        this.direction = dir;
    }

    /**
     * Méthode de dessin du personnage dans le jeu
     *
     * @param image image sur laquelle dessiner
     */
    public abstract void draw(BufferedImage image);

    /**
     * Méthode permettant de déplacer l'arme
     */
    public void move() {
        switch (this.direction){
            case "h" -> {
                this.y -= 12;
            }
            case "b" -> {
                this.y += 12;
            }
            case "g" -> {
                this.x -= 12;
            }
            case "d" -> {
                this.x += 12;
            }
        }
        this.ttl--;
    }

    public void attaque(Monstre adv) {
        if(canAttack){
            adv.recevoirDegats(getPDA());
            this.ttl = 0;
            canAttack = false;
            Jeu.sound.setFile(0);
            Jeu.sound.play();
        }
    }

    /**
     * Getter de la position X du personnage
     *
     * @return : position X du personnage
     */
    public int getX() {
        return x;
    }

    /**
     * Getter de la position Y du personnage
     *
     * @return : position Y du personnage
     */
    public int getY() {
        return y;
    }

    /**
     * getteur de la force du personnage
     *
     * @return : force du personnage
     */
    public double getPDA() {
        return pda;
    }

    /**
     * getteur de la direction de l'arme
     *
     * @return : direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * getteur de la ttl de l'arme
     *
     * @return : ttl
     */
    public int getTTL() {
        return ttl;
    }

    /**
     * Setter de la position X du personnage
     * @param x
     *     : nouvelle position X du personnage
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter de la position Y du personnage
     * @param y
     *    : nouvelle position Y du personnage
     */
    public void setY(int y) {
        this.y = y;
    }
}
