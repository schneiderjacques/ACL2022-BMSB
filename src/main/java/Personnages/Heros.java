package main.java.Personnages;

import main.java.Engine.Sound;
import main.java.Principale.Tools;
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
        super(1, 1, true, 10, 5);

        /**
         * initialisation des images du héros
         */
        this.initImage(9);
        for (int i = 0; i < 9; i++) {
            this.setImage(Tools.getImageByName("/images/game/hero/tiles/idle/down/sprite_idle_down" + i), i);
        }
    }

    /**
     * Constructeur du héros
     * @param x : position en x
     * @param y : position en y
     * @param pdv : points de vie
     * @param pda : points d'attaque
     */
    public Heros(int x, int y, double pdv, double pda) {
        super(x,y,true, pdv,pda);
    }

    /**
     * Méthode permettant de changer les frames du monstre
     */
    @Override
    public void changeFrame() {
        this.setFrame(0);
        switch (this.getLM()) {
            case "d":
                if (this.getAnimationAttack()) {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/attack/right/sprite_attack_right" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/idle/right/sprite_idle_right" + i), i);
                    }
                } else {
                    this.initImage(4);
                    for (int i = 0; i <= 3; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/attacked/right/sprite_attacked_right" + i), i);
                    }
                }
                break;
            case "g":
                if (this.getAnimationAttack()) {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/attack/left/sprite_attack_left" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/idle/left/sprite_idle_left" + i), i);
                    }
                } else {
                    this.initImage(4);
                    for (int i = 0; i <= 3; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/attacked/left/sprite_attacked_left" + i), i);
                    }
                }

                break;
            case "h":
                if (this.getAnimationAttack()) {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/attack/up/sprite_attack_up" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/idle/up/sprite_idle_up" + i), i);
                    }    
                } else {
                    this.initImage(4);
                    for (int i = 0; i <= 3; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/attacked/up/sprite_attacked_up" + i), i);
                    }
                }
                
                break;
            case "b":
                if (this.getAnimationAttack()) {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/attack/down/sprite_attack_down" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/idle/down/sprite_idle_down" + i), i);
                    }    
                } else {
                    this.initImage(4);
                    for (int i = 0; i <= 3; i++) {
                        this.setImage(Tools.getImageByName("/images/game/hero/tiles/attacked/down/sprite_attacked_down" + i), i);
                    }
                }
                
                break;
        }
        this.setAnimationAttacked(false);
        this.setAnimationAttack(false);
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
            this.setAnimationAttack(true);
            changeFrame();
            sound.setFile(0);
            sound.play();
            adv.recevoirDegats(getPDA());
            this.setCanAttack(false);
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            Runnable delayAttaque = () -> {this.setCanAttack(true);executor.shutdown();};
            executor.scheduleAtFixedRate(delayAttaque, 1000, 100, TimeUnit.MILLISECONDS);
            return true;
        }
        return false;
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

    /**
     * Méthode permettant de réinitialiser le héros
     */
    public void reset(){
        this.setCanMove(true);
        this.setCanAttack(true);
        this.setX(1);
        this.setY(1);
    }

    /**
     * Méthode permettant d'ajouter des points de vie au héros
     * @param pv
     * nb de points de vie à ajouter
     */
    @Override
    public void ajouterPDV(double pv) {
        this.setPDV(Math.min(this.getPDV() + pv, PDV_MAX));
    }

    /**
     * Méthode permettant de retirer des points de vie au héros
     * @param pv
     * nb de points de vie à retirer
     */
    @Override
    public void retirerPDV(double pv) {
        this.setPDV(Math.max(this.getPDV() - pv, 0));
        this.setAnimationAttacked(true);
        changeFrame();
    }
}
