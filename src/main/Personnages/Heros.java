package main.Personnages;

import java.awt.*;

/**
 * Class représentant le héros du jeu que l'on contrôle
 * @author Arthur Moitrier
 */
public class Heros extends Personnage {

    // Le personnage peut se deplacer
    // Par défaut à true car tout les personnages peuvent se déplacer
    private boolean canMove = true;

    /**
     * Constructeur du héros
     * Le héros commence toujours aux coordonnées (1,1) avec 10 points de vie et 0.5 points d'attaque
     */
    public Heros() {
        super(1, 1, true, 10, 0.5, Color.blue);
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
     * Getter du type de personnage
     * @return type de personnage
     */
    public String getType() {
        return "Heros";
    }

    /**
     * canMove
     * @return canMove
     * */
    public boolean canMove(){
        return canMove;
    }

    /*
     * setCanMove
     * @param canMove
     * */
    public void setCanMove(boolean canMove){
        this.canMove = canMove;
    }
}
