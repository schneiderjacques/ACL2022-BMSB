package main.java.Cases;

import main.java.Engine.GamePainter;
import main.java.Principale.Tour;

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

    private BufferedImage[] image = null;

    //le joueur entre en collision avec la case ou non
    private boolean collision;

    //Frame actuelle
    private int frame = 0;
    //Vitesse des frames
    private int FrameSpeed = 10;

    //Tick actuel
    private int tick = 0;



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
    public int getFrame() {
        return frame;
    }
    /**
     * Méthode de dessin de la case dans le jeu
     * @param image image sur laquelle dessiner
     */
    public abstract void draw(BufferedImage image);


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
    /**
     * Initialisation le buffer de la case
     * @param size : taille du buffer
     */
    public void initImage(int size){
        this.image = new BufferedImage[size];
    }

    /**
     * Getter de l'image de la case dans le buffer
     * @param frame : frame de l'image
     * @return image de la case
     */
    public Image getImage(int frame){
        return this.image[frame];
    }

    /**
     * Setter de l'image de la case dans le buffer
     * @param image : image a mettre en place
     * @param index :  index de l'image dans le buffer
     */
    public void setImage(BufferedImage image, int index){
        this.image[index] = image;
    }

    /**
     * Setter de la frame actuel
     * @param frame : frame actuel
     */
    public void setFrame(int frame){
        this.frame = frame;
    }

    /**
     * Getter de la vitesse des frames
     * @return int représentant la vitesse des frames
     */
    public int getFrameSpeed() {
        return FrameSpeed;
    }

    /**
     * Setter des ticks
     * @param tick : int représentant le tick actuel de la case
     */
    public void setTick(int tick){
        this.tick = tick;
    }
    /**
     * Getter des ticks
     * @return int représentant le tick actuel de la case
     */
    public int getTick(){
        return this.tick;
    }

    public Color getColor(){
        return this.color;
    }
}
