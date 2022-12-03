package main.java.Cases;

import main.java.Engine.DrawingPanel;
import main.java.Principale.Tools;
import main.java.Principale.Tour;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Représente une case de dégat, lorsque le joueur marche dessus il subit des dégats
 * @author Jacques Schneider
 */
public class Degat extends Case {

    private boolean walkedOn = false;

    /**
     * Constructeur de la case Degat
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Degat(int x, int y) {
        //Initialisation des attributs
        super(false, x, y);
        this.initImage(1);
        this.setImage(Tools.getImageByName("/images/game/objects/tile_degat0"),0);
    }

    /**
     * Getter du type de case
     * @return type de case
     */
    @Override
    public String getType() {
        return "Degat";
    }

    /**
     * Méthode qui permet de faire subir des dégats au joueur
     * @param t : Tour du jeu
     */
    @Override
    public void eventCollider(Tour t) {
        //Dégats de la case
        double DAMAGE_AMOUNT = 4;
        t.getHeros().retirerPDV(DAMAGE_AMOUNT);
        walkedOn = true;
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
