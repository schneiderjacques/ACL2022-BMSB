package main.java.Personnages;

import main.java.Armes.Arme;
import main.java.Armes.Lance;
import main.java.Engine.DrawingPanel;
import main.java.Engine.GamePainter;
import main.java.Cases.Case;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class représentant un personnage du jeu
 *
 * @author Martin Gurtner
 */
public abstract class Personnage implements GamePainter {

    //Position du personnage en X
    private int x;

    //Position du personnage en Y
    private int y;

    //Vie du personnage
    private double pdv;

    //Force du personnage
    private final double pda;

    //Dernier mouvement du héros 
    private String lm;

    //Collision du personnage
    private final boolean collision;

    private BufferedImage[] image = null;
    //Frame actuelle
    private int frame = 0;
    //Vitesse des frames
    private int FrameSpeed = 10;

    //Tick actuel
    private int tick = 0;

    // nombre de frames de l'animation courante
    private int nbFrame = 9;

    // animation courante est : le personnage est attaqué
    private boolean animationAttacked = false;

    // animation courante est : le personnage attaque
    private boolean animationAttack = false;

    /**
     * Constructeur du personnage
     *
     * @param x   : position en X
     * @param y   : position en Y
     * @param col : collision du personnage
     * @param pdv : points de vie
     * @param pda : points d'attaque
     */
    public Personnage(int x, int y, boolean col, double pdv, double pda) {
        //Initialisation des attributs
        this.x = x;
        this.y = y;
        this.pdv = pdv;
        this.pda = pda;
        this.lm = "b";
        this.collision = col;
    }

    /**
     * Méthode permettant de récupérer la frame actuelle
     * @return frame
     */
    public int getFrame() {
        return frame;
    }

    /**
     * Méthode permettant de récupérer le nombre de frames de l'animation courante
     * @return nbFrame
     */
    public int getNbFrame() {
        return nbFrame;
    }

    /**
     * Méthode de dessin du personnage dans le jeu
     *
     * @param image image sur laquelle dessiner
     */
    public void draw(BufferedImage image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        this.setTick(this.getTick() + 1);
        if (this.getTick() >= this.getFrameSpeed()) {
            this.setTick(0);
            this.setFrame(this.getFrame() + 1);
            if (this.getFrame() >= getNbFrame()) {
                this.setFrame(0);
                if (this.getAnimationAttacked()) {
                    this.setAnimationAttacked(false);
                    changeFrame();
                }
                if (this.getAnimationAttack()) {
                    this.setAnimationAttack(false);
                    changeFrame();
                }
            }
        }
        g.drawImage(this.getImage(this.getFrame()), this.getX() * Case.TAILLE_CASE, this.getY() * Case.TAILLE_CASE + DrawingPanel.ECART, Case.TAILLE_CASE, Case.TAILLE_CASE, null);
    }

    /**
     * Initialisation le buffer de la case
     *
     * @param size : taille du buffer
     */
    public void initImage(int size) {
        this.image = new BufferedImage[size];
        this.nbFrame = size;
    }

    /**
     * Getter de l'image de la case dans le buffer
     *
     * @param frame : frame de l'image
     * @return image de la case
     */
    public Image getImage(int frame) {
        return this.image[frame];
    }

    /**
     * Setter de l'image de la case dans le buffer
     *
     * @param image : image à mettre en place
     * @param index : index de l'image dans le buffer
     */
    public void setImage(BufferedImage image, int index) {
        this.image[index] = image;
    }

    /**
     * Setter de la frame actuel
     *
     * @param frame : frame actuel
     */
    public void setFrame(int frame) {
        this.frame = frame;
    }

    /**
     * Getter de la vitesse des frames
     *
     * @return int représentant la vitesse des frames
     */
    public int getFrameSpeed() {
        return FrameSpeed;
    }

    /**
     * Setter des ticks
     *
     * @param tick : int représentant le tick actuel de la case
     */
    public void setTick(int tick) {
        this.tick = tick;
    }

    /**
     * Getter des ticks
     *
     * @return int représentant le tick actuel de la case
     */
    public int getTick() {
        return this.tick;
    }

    /**
     * Méthode permettant de déplacer le personnage en X
     *
     * @param dir : direction du déplacement
     */
    public void moveX(int dir) {
        this.x = this.x + dir;
        if (dir == 1) {
            lm = "d";
            changeFrame();
        } else {
            lm = "g";
            changeFrame();
        }
    }

    /**
     * Méthode permettant de modifier les frames du personnage
     */
    public abstract void changeFrame();


    /**
     * Méthode permettant de déplacer le personnage en Y
     *
     * @param dir : direction du déplacement
     */
    public void moveY(int dir) {
        this.y = this.y + dir;
        if (dir == 1) {
            lm = "b";
            changeFrame();
        } else {
            lm = "h";
            changeFrame();
        }
    }

    /**
     * Méthode permettant de modifier le dernier mouvement du personnage
     * @param lm
     *        : dernier mouvement du personnage
     */
    public void setLastMove(String lm) {
        this.lm = lm;
        changeFrame();
    }

    /**
     * Méthode permettant de déplacer le personnage
     *
     * @param axe : axe de déplacement
     * @param dir : direction du déplacement
     */
    public void move(char axe, int dir) {
        if (axe == 'X') {
            this.moveX(dir);
        } else if (axe == 'Y') {
            this.moveY(dir);
        }
    }

    /**
     * Méthode qui permet d'attaquer les personnages en face du personnage
     *
     * @param adv : adversaire
     * @return : true s'il y a eu une attaque, false sinon
     */
    public boolean attaque(Personnage adv) {
        if ((adv.getX() == getX() + 1 && adv.getY() == getY() && Objects.equals(getLM(), "d")) ||
                (adv.getX() == getX() - 1 && adv.getY() == getY() && Objects.equals(getLM(), "g")) ||
                (adv.getY() == getY() - 1 && adv.getX() == getX() && Objects.equals(getLM(), "h")) ||
                (adv.getY() == getY() + 1 && adv.getX() == getX() && Objects.equals(getLM(), "b"))) {
            this.setAnimationAttack(true);
            adv.recevoirDegats(getPDA());
            return true;
        }
        return false;
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
     * getteur de la vie du personnage
     *
     * @return : vie du personnage
     */
    public double getPDV() {
        return pdv;
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
     * getteur du dernier mouvement du personnage
     *
     * @return : dernier mouvement du personnage
     */
    public String getLM() {
        return lm;
    }

    /**
     * Setter de la vie du personnage.
     *
     * @param pv : nouvelle vie du personnage
     */
    public void setPDV(double pv) {
        this.pdv = pv;
    }

    /**
     * Ajouter des points de vie au personnage
     *
     * @param pv
     */
    public abstract void ajouterPDV(double pv);

    /**
     * Retirer des points de vie au personnage
     *
     * @param pv
     */
    public abstract void retirerPDV(double pv);

    /**
     * Setter de la vie du personnage après une attaque
     *
     * @param degats : dégats subis par le personnage
     */
    public void recevoirDegats(double degats) {
        this.setAnimationAttacked(true);
        retirerPDV(degats);
    }

    /**
     * Methode qui indique si le personnage est mort
     *
     * @return : true si le personnage est mort, false sinon
     */
    public boolean estVivant() {
        return getPDV() > 0;
    }

    /**
     * toString
     *
     * @return String
     */
    public String toString() {
        return "personnage : \n" +
                "Vie : " + getPDV() + "\n" +
                "Attaque : " + getPDA() + "\n" +
                "Position : " + getX() + ";" + getY() + "\n\n";
    }

    /**
     * Indique si le personnage est un monstre
     *
     * @return true si le personnage est un monstre, false sinon
     */
    public boolean beMonster() {
        return false;
    }

    /**
     * Getter permettant de savoir si le personnage est en train d'attaquer
     * @return : true si le personnage est en train d'attaquer, false sinon
     */
    public boolean getAnimationAttack() {
        return animationAttack;
    }

    /**
     * Setter permettant de savoir si le personnage est en train d'attaquer
     * @param animationAttack
     *       : true si le personnage est en train d'attaquer, false sinon
     */
    public void setAnimationAttack(boolean animationAttack) {
        this.animationAttack = animationAttack;
    }

    /**
     * Getter permettant de savoir si le personnage est en train d'être attaqué
     * @return : true si le personnage est en train d'être attaqué, false sinon
     */
    public boolean getAnimationAttacked() {
        return animationAttacked;
    }

    /**
     * Setter permettant de savoir si le personnage est en train d'être attaqué
     * @param animationAttacked
     *       : true si le personnage est en train d'être attaqué, false sinon
     */
    public void setAnimationAttacked(boolean animationAttacked) {
        this.animationAttacked = animationAttacked;
    }

    /**
     * Indique si le personnage entre en collision avec les murs ou non
     *
     * @return true si le personnage entre en collision avec les murs, false sinon
     */
    public boolean isCollision() {
        return collision;
    }

    /**
     * Setter de l'orientation du personnage
     *
     * @param orientation : nouvelle orientation du personnage
     */
    public void setLm(String orientation) {
        this.lm = orientation;
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
