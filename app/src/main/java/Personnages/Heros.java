package main.java.Personnages;

import main.java.Engine.Sound;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class représentant le héros du jeu que l'on contrôle
 * @author Arthur Moitrier
 */
public class Heros extends Personnage {

    // Le personnage peut se deplacer
    // Par défaut à true car le heros peut se déplacer
    private boolean canMove = true;

    // Le personnage peut attaquer
    // Par défaut à true car le heros peut attaquer
    private boolean canAttack = true;
    private Sound sound = new Sound();

    //Point de vie maximal du héros
    private final static double PDV_MAX = 10;

    /**
     * Constructeur du héros
     * Le héros commence toujours aux coordonnées (1,1) avec 10 points de vie et 0.5 points d'attaque
     */
    public Heros() {
        super(1, 1, true, 10, 5, Color.blue);
    }

    /**
     * Constructeur du héros
     * @param x : position en x
     * @param y : position en y
     * @param pdv : points de vie
     * @param pda : points d'attaque
     */
    public Heros(int x, int y, double pdv, double pda) {
        super(x,y,true, pdv,pda, Color.blue);
    }

    /**
     * canMove
     * @return canMove
     * */
    public boolean canMove(){
        return canMove;
    }

    /**
     * canAttack
     * @return canAttack
     * */
    public boolean canAttack(){return canAttack;}

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
            sound.setFile(0);
            sound.play();
            adv.recevoirDegats(getPDA());
            this.setCanAttack(false);
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            Runnable delayAttaque = () -> {this.setCanAttack(true);executor.shutdown();};
            executor.scheduleAtFixedRate(delayAttaque, 1000, 100, TimeUnit.MILLISECONDS);
            System.out.println("Le héros a attaqué le monstre" + " : " + adv.getPDV());
            return true;
        }
        return false;
    }

    /**
     * setCanMove
     * @param canMove
     * */
    public void setCanMove(boolean canMove){
        this.canMove = canMove;
    }

    /**
     * setCanAttack
     * @param canAttack
     * */
    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void reset(){
        this.setCanMove(true);
        this.setCanAttack(true);
        this.setX(1);
        this.setY(1);
    }

    @Override
    public void ajouterPDV(double pv) {
        this.setPDV(Math.min(this.getPDV() + pv, PDV_MAX));
    }

    @Override
    public void retirerPDV(double pv) {
        this.setPDV(Math.max(this.getPDV() - pv, 0));
    }
}
