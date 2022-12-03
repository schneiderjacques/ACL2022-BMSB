package main.java.Cases;

import main.java.Engine.DrawingPanel;
import main.java.Personnages.Heros;
import main.java.Principale.Tools;
import main.java.Principale.Tour;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Représente une case piège, lorsque le joueur marche dessus il est immobilisé 3 secondes
 * @author Jacques Schneider
 */
public class Trappe extends Case{
    //Temps d'immobilisation du joueur
    private final double BLOCKED_TIME = 3;

    private boolean walkedOn = false;

    //Executer pour bloquer le personnage
    private ScheduledExecutorService executor;

    /**
     * Constructeur de la case Piege
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Trappe(int x, int y) {
        //Initialisation des attributs
        super(false, x, y);
        this.initImage(1);
        this.setImage(Tools.getImageByName("/images/game/objects/jail"),0);
    }



    /**
     * Getter du type de case
     * @return type de case
     */
    @Override
    public String getType() {
        return "Trappe";
    }

    /**
     * Méthode qui bloque le joueur pendant 3 secondes
     * @param t : tour du jeu
     */
    @Override
    public void eventCollider(Tour t) {
        Heros h = t.getHeros();
        h.setCanMove(false);
        executor = Executors.newSingleThreadScheduledExecutor();
        Runnable stopPlayer = () -> {
            h.setCanMove(true);
            stopExecutor();

        };
        this.executor.scheduleAtFixedRate(stopPlayer, 3000, 100, TimeUnit.MILLISECONDS);
        walkedOn = true;
    }

    /**
     * Méthode qui arrête l'executor
     */
    public void stopExecutor(){
        this.executor.shutdown();
    }
    @Override
    public void draw(BufferedImage image) {
        if (walkedOn) {
            Graphics2D g = (Graphics2D) image.getGraphics();
            this.setTick(this.getTick() + 1);
            if (this.getTick() >= this.getFrameSpeed()) {
                this.setTick(0);
                this.setFrame(this.getFrame() + 1);
                if (this.getFrame() >= 1) {
                    this.setFrame(0);
                }
            }
            g.drawImage(this.getImage(this.getFrame()), this.getY() * TAILLE_CASE, this.getX() * TAILLE_CASE + DrawingPanel.ECART, TAILLE_CASE, TAILLE_CASE, null);
        }
    }
}
