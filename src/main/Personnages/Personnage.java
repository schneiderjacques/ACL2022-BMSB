package main.Personnages;

import main.Engine.GamePainter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
* Class représentant un personnage du jeu
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

    //Taille d'une case en pixels
    private static final int TAILLE_CASE = 16*3;

    //Collision du personnage
    private final boolean collision;

    //Couleur du personnage
    private final Color color;

    /**
    * Constructeur du personnage
    * @param x : position en X
    * @param y : position en Y
    * @param col : collision du personnage
    * @param pdv : points de vie
    * @param pda : points d'attaque
     */
    public Personnage(int x, int y, boolean col, double pdv, double pda, Color color) {
        //Initialisation des attributs
        this.x = x;
        this.y = y;
        this.pdv = pdv;
        this.pda = pda;
        this.lm = "b";
        this.collision = col;
        this.color = color;
    }

    /**
     * Méthode de dessin du personnage dans le jeu
     * @param im : image sur laquelle dessiner
     */
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(this.color);
        crayon.fillRect(x*TAILLE_CASE,y*TAILLE_CASE,TAILLE_CASE,TAILLE_CASE);
    }

    /**
    * Méthode permettant de déplacer le personnage en X
    * @param dir : direction du déplacement
     */
    public void moveX(int dir){
        this.x = this.x + dir;
        if(dir == 1){
            lm = "d";
        }else{
            lm = "g";
        }
    }


    /**
    * Méthode permettant de déplacer le personnage en Y
    * @param dir : direction du déplacement
     */
    public void moveY(int dir){
        this.y = this.y + dir;
        if(dir == 1){
            lm = "b";
        }else{
            lm = "h";
        }
    }

    /**
    * Méthode permettant de déplacer le personnage
    * @param axe : axe de déplacement
    * @param dir : direction du déplacement
     */
    public void move(char axe, int dir){
        if (axe == 'X'){
            this.moveX(dir);
        }
        else if (axe == 'Y'){
            this.moveY(dir);
        }
    }

    /**
    * Méthode qui permet d'attaquer les personnages en face du personnage
     * @param adv : adversaire
     * @return : true s'il y a eu une attaque, false sinon
     */
    public boolean attaque(Personnage adv){
        if((adv.getX() == getX()+1 && adv.getY() == getY() && Objects.equals(getLM(), "d")) ||
        (adv.getX() == getX()-1 && adv.getY() == getY() && Objects.equals(getLM(), "g")) ||
        (adv.getY() == getY()-1 && adv.getX() == getX() && Objects.equals(getLM(), "h")) ||
        (adv.getY() == getY()+1 && adv.getX() == getX() && Objects.equals(getLM(), "b"))){
            adv.recevoirDegats(getPDA());
            System.out.println("Vous avez attaqué un adversaire");
            return true;
        }
        return false;
    }

    /**
     * Getter de la position X du personnage
     * @return : position X du personnage
     */
    public int getX(){
        return x; 
    }

    /**
     * Getter de la position Y du personnage
     * @return : position Y du personnage
     */
    public int getY(){
        return y; 
    }

    /**
     * getteur de la vie du personnage
     * @return : vie du personnage
     */
    public double getPDV(){
        return pdv; 
    }

    /**
     * getteur de la force du personnage
     * @return : force du personnage
     */
    public double getPDA(){
        return pda; 
    }

    /**
     * getteur du dernier mouvement du personnage
     * @return : dernier mouvement du personnage
     */
    public String getLM(){
        return lm; 
    }

    /**
     * Setter de la vie du personnage. 
     * @param pv : nouvelle vie du personnage
     */
    public void setPDV(double pv){
        this.pdv = pv; 
        if(getPDV() < 0){
            pdv = 0; 
        }
    }

    /**
     * Setter de la vie du personnage après une attaque
     * @param degats : dégats subis par le personnage
     */
    public void recevoirDegats(double degats){
        setPDV(getPDV() - degats);
    }

    /**
     * Methode qui indique si le personnage est mort
     * @return : true si le personnage est mort, false sinon
     */
    public boolean estVivant(){
        return getPDV() != 0; 
    }

    /**
     * Getter du type de personnage
     * @return type de personnage
     */
    public abstract String getType();

    /**
     * toString
     * @return String
     */
    public String toString(){
        return "personnage : \n"+
        "Vie : "+getPDV()+"\n"+
        "Attaque : "+getPDA()+"\n"+
        "Position : "+getX()+";"+getY()+"\n\n"; 
    }

    /**
     * Indique si le personnage est un monstre
     * @return true si le personnage est un monstre, false sinon
     */
    public boolean beMonster(){
        return false;
    }

    /**
     * Indique si le personnage entre en collision avec les murs ou non
     * @return true si le personnage entre en collision avec les murs, false sinon
     */
    public boolean isCollision() {
        return collision;
    }

    /**
     * Setter du l'orientation du personnage
     * @param orientation : nouvelle orientation du personnage
     */
    public void setLm(String orientation) {
        this.lm = orientation;
    }
}
