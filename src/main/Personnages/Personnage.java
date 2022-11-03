package main.Personnages;

import main.Engine.GamePainter;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
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
    private double pda;
    //Dernier mouvement du héros 
    private String lm;
    //Taille d'une case en pixels
    private static final int TAILLE_CASE = 16*3;
    //Collision du personnage
    private boolean collision;

    /*
    * Constructeur du personnage
    * @param x : position en X
    * @param y : position en Y
    * @param col : collision du personnage
    * @param pdv : points de vie
    * @param pda : points d'attaque
     */
    public Personnage(int x, int y, boolean col, double pdv, double pda){
        //Initialisation des attributs
        this.x = x;
        this.y = y;
        this.pdv = pdv;
        this.pda = pda;
        this.lm = "b";
        this.collision = col;
    }

    /**
     * Méthode de dessin du personnage dans le jeu
     * @param im
     *            image sur laquelle dessiner
     */
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.blue);
        crayon.fillRect(x*TAILLE_CASE,y*TAILLE_CASE,TAILLE_CASE,TAILLE_CASE);
    }
    /*
    * Méthode permettant de déplacer le personnage en X
    * @param x
     */
    public void moveX(int dir){
        //throw new Error("Not implemented yet");
        this.x = this.x + dir;
        //System.out.println("Le personnage se déplace en X de " + dir + " cases");
    }


    /*
    * Méthode permettant de déplacer le personnage en Y
    * @param y
     */
    public void moveY(int dir){
        //throw new Error("Not implemented yet");
        this.y = this.y + dir;
        //System.out.println("Le personnage se déplace en Y de " + dir + " cases");
    }

    /*
    * Méthode permettant de déplacer le personnage
    * @param axe
    * @param dir
     */
    public void move(char axe, int dir){
        //throw new Error("Not implemented yet");
        if (axe == 'X'){
            this.moveX(dir);
        }
        else if (axe == 'Y'){
            this.moveY(dir);
        }
        if(axe == 'X' && dir == 1){
            lm = "d"; 
        }
        if(axe == 'X' && dir == -1){
            lm = "g"; 
        }
        if(axe == 'Y' && dir == -1){
            lm = "h";
        }
        if(axe == 'Y' && dir == 1){
            lm = "b";
        }
    }

    /*
    * Méthode qui permet d'attaquer les personnages en face du personnage
     */
    public boolean attaque(Personnage adv){
        if((adv.getX() == getX()+1 && getLM() == "d") ||
        (adv.getX() == getX()-1 && getLM() == "g") ||
        (adv.getY() == getY()-1 && getLM() == "h") ||
        (adv.getY() == getY()+1 && getLM() == "b")){
            adv.recevoirDegats(getPDA());
            return true;
        }
        return false;
    }

    /*
     * Getter de la position X du personnage
     * @return x
     */
    public int getX(){
        return x; 
    }

    /*
     * Getter de la position Y du personnage
     * @return y 
     */
    public int getY(){
        return y; 
    }

    /**
     * getteur de la vie du personnage
     * @return pdv
     */
    public double getPDV(){
        return pdv; 
    }

    /**
     * getteur de la force du personnage
     * @return pda
     */
    public double getPDA(){
        return pda; 
    }

    public String getLM(){
        return lm; 
    }

    /**
     * Setter de la vie du personnage. 
     * @param pv
     */
    public void setPDV(double pv){
        this.pdv = pv; 
        if(getPDV() < 0){
            pdv = 0; 
        }
    }

    public void recevoirDegats(double degats){
        setPDV(getPDV() - degats);
    }

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

    public boolean beMonster(){
        return false;
    }

    public boolean isCollision() {
        return collision;
    }

}
