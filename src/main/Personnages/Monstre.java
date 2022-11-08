package main.Personnages;

import main.Principale.Niveau;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Class représentant un monstre du jeu
 * @author Arthur Moitrier, Anthony Briot
 */
public abstract class Monstre extends Personnage {

    //Niveau du monstre
    private Niveau niveau;

    /**
     * Constructeur du monstre
     * @param x : position en X
     * @param y : position en Y
     * @param col : collision du monstre
     * @param pdv : points de vie
     * @param pda : points d'attaque
     * @param n : niveau dans lequel se trouve le monstre
     * @param color : couleur du monstre
     */
    public Monstre(int x, int y, boolean col, double pdv, double pda, Niveau n, Color color) {
        super(x, y, col, pdv, pda, color);
        this.niveau = n;
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
    public Monstre(int x, int y, boolean col, double pdv, double pda, Color color) {
        super(x, y, col, pdv, pda, color);
    }

    /**
     * Méthode qui déplace ou attaque le monstre
     */
    public void moveOrAttack() {
        if (!this.attaque(this.niveau.getTour().getHeros())) {
            this.moveRandom();
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
}
