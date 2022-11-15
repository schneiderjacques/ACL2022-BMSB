package main.Personnages;

import main.Principale.Niveau;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.lang.Math.*;

/**
 * Class représentant un monstre du jeu
 * @author Arthur Moitrier, Anthony Briot
 */
public abstract class Monstre extends Personnage {

    //Niveau du monstre
    private Niveau niveau;
    private double view;

    /**
     * Constructeur du monstre
     * @param x : position en X
     * @param y : position en Y
     * @param col : collision du monstre
     * @param pdv : points de vie
     * @param pda : points d'attaque
     * @param n : niveau dans lequel se trouve le monstre
     * @param color : couleur du monstre
     * @param view : vue du monstre.
     */
    public Monstre(int x, int y, boolean col, double pdv, double pda, Niveau n, Color color, int view) {
        super(x, y, col, pdv, pda, color);
        this.niveau = n;
        this.view = view; 
    }

    /**
     * Déplacement du monstre
     * @param x : position en X
     * @param y : position en Y
     * @param col : collision du monstre
     * @param pdv : points de vie
     * @param pda : points d'attaques
     * @param color : couleur du monstre
     */
    public Monstre(int x, int y, boolean col, double pdv, double pda, Color color, int view) {
        super(x, y, col, pdv, pda, color);
        this.view = view; 
    }

    /**
     * Méthode qui déplace ou attaque le monstre
     */
    public void moveOrAttack() {
        if (!this.attaque(this.niveau.getTour().getHeros())) {
            if(inView(this.niveau.getTour().getHeros())){
                moveDir(this.niveau.getTour().getHeros(),true);
            }else{
                moveRandom();
            }
            this.attaque(this.niveau.getTour().getHeros());
        }
    }

    /**
     * Méthode permettant de déplacer le monstre aléatoirement
     */
    public void moveRandom(){
        int dir = (int) (Math.random() * 4);
            switch (dir) {
                case 0:
                    if (this.niveau.canMove(this, 'X', 1)) this.moveX(1);
                    break;
                case 1:
                    if (this.niveau.canMove(this, 'X', -1)) this.moveX(-1);
                    break;
                case 2:
                    if (this.niveau.canMove(this, 'Y', 1)) this.moveY(1);
                    break;
                case 3:
                    if (this.niveau.canMove(this, 'Y', -1)) this.moveY(-1);
                    break;
            }
    }

    /**
     * Methode permettant de déterminer si l'adversaire est dans le champ de vision du monstre 
     * @param adv : Adversaire 
     * @return : Boolean 
     */
    public boolean inView(Personnage adv){
        double dist = 0; 
        double x = this.getX() - adv.getX(); 
        double y = this.getY() - adv.getY(); 
        x = x*x; 
        y = y*y; 
        dist = x + y; 
        dist = Math.sqrt(dist); 
        return dist <= view; 
    }

    /**
     * Methode permettant de déplacer le monstre en direction de son adversaire
     * @param adv
     */
    public void moveDir(Personnage adv, boolean niv){
        char dir; 
        int depl = 0; 
        double diffX = adv.getX() - this.getX(); 
        double diffY = adv.getY() - this.getY();
        if(diffX == 0){
            dir = 'Y'; 
        }else{
            if(diffY == 0){
                dir = 'X'; 
            }else{
                if(Math.abs(diffX)<Math.abs(diffY)){
                    dir = 'X'; 
                }else{
                    dir = 'Y'; 
                }
            }
        }

        if(dir == 'X'){
            if(diffX < 0){
                depl = -1;
            }else{
                depl = 1; 
            }
        }
        if(dir == 'Y'){
            if(diffY<0){
                depl = -1; 
            }else{
                depl = 1;
            }
        }

        if(!niv){
            move(dir, depl);
        }else{
            if(this.niveau.canMove(this, dir, depl)){
                move(dir, depl);
            }else{
                moveRandom();
            }
        }
    }


    /**
     * Méthode permettant d'attaquer un personnage
     * @param adv : personnage à attaquer
     * @return boolean
     */
    public boolean attaque(Personnage adv){
        if(((adv.getX() == getX()+1 && adv.getY() == getY()) ||
                (adv.getX() == getX()-1 && adv.getY() == getY()) ||
                (adv.getY() == getY()-1 && adv.getX() == getX()) ||
                (adv.getY() == getY()+1 && adv.getX() == getX())) &&
                !adv.beMonster()
        ){
            adv.recevoirDegats(getPDA());
            System.out.println("Le monstre attaque le héros" + " : " + adv.getPDV());
            return true;
        }
        return false;
    }

    /**
    * Methode permettant de savoir si le personnage est un monstre
    * @return boolean
    */
    public boolean beMonster(){
        return true;
    }

    @Override
    public void recevoirDegats(double degats) {
        super.recevoirDegats(degats);
        this.niveau.resetMonstre(this);
    }

}
