package main.Personnages;

import main.Principale.Niveau;

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
    public Monstre(int x, int y, boolean col, double pdv, double pda, Niveau n) {
        super(x, y, col, pdv, pda);
        this.niveau = n;
        //move or attack every 5 seconds
    }

    /*
     * Méthode qui déplace ou attaque le monstre
     */
    public void moveOrAttack() {
        if (!this.attaque(this.niveau.getTour().getHeros())) {
            this.moveRandom();
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
        if(((adv.getX() == getX()+1 && getLM() == "d") ||
                (adv.getX() == getX()-1 && getLM() == "g") ||
                (adv.getY() == getY()-1 && getLM() == "h") ||
                (adv.getY() == getY()+1 && getLM() == "b" )) &&
                !adv.beMonster()
        ){
            adv.recevoirDegats(getPDA());
            return true;
        }
        return false;
    }

    public boolean beMonster(){
        return true;
    }
}
