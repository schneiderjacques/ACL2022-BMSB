package main.Personnages;

import main.Principale.Niveau;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/*
 * Class représentant un monstre du jeu
 * @author Arthur Moitrier
 */
public abstract class Monstre extends Personnage {

    private Niveau niveau;

    /*
     * Constructeur du monstre
     * @param x : position en X
     * @param y : position en Y
     * @param pdv : points de vie
     * @param pda : points d'attaque
     * @param n : niveau dans lequel se trouve le monstre
     */
    public Monstre(int x, int y, boolean col, double pdv, double pda, Niveau n, Color color) {
        super(x, y, col, pdv, pda, color);
        this.niveau = n;
    }

    /*
     * Méthode qui déplace ou attaque le monstre
     */
    public void moveOrAttack() {
        if (!this.attaque(this.niveau.getTour().getHeros())) {
            this.moveRandom();
            this.attaque(this.niveau.getTour().getHeros());
        }
    }

    /*
     * Méthode permettant de déplacer le monstre
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
    };


    /*
     * Méthode permettant d'attaquer un personnage
     * @param p : personnage à attaquer
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
            System.out.println("Monstre:"+getX()+";"+getY());
            System.out.println("Hero:"+adv.getX()+";"+adv.getY());
            System.out.println("Le monstre attaque");
            return true;
        }
        return false;
    }

    public boolean beMonster(){
        return true;
    }
}
