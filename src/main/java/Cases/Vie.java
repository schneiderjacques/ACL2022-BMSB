package main.java.Cases;

import main.java.Engine.DrawingPanel;
import main.java.Principale.Tools;
import main.java.Principale.Tour;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Représente une case de vie, lorsque le joueur marche dessus il récupère de la vie
 *
 * @author Jacques Schneider
 */
public class Vie extends Case {


    /**
     * Constructeur de la case de Vie
     *
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Vie(int x, int y) {
        //Initialisation des attributs
        super(false, x, y, Color.green);
        this.initImage(6);
        try {
            this.setImage(Tools.getImageByName("/images/game/objects/vie/Red 1"), 0);
            this.setImage(Tools.getImageByName("/images/game/objects/vie/Red 2"), 1);
            this.setImage(Tools.getImageByName("/images/game/objects/vie/Red 3"), 2);
            this.setImage(Tools.getImageByName("/images/game/objects/vie/Red 4"), 3);
            this.setImage(Tools.getImageByName("/images/game/objects/vie/Red 5"), 4);
            this.setImage(Tools.getImageByName("/images/game/objects/vie/Red 6"), 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Getter du type de case
     *
     * @return type de case
     */
    @Override
    public String getType() {
        return "Vie";
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        this.setTick(this.getTick() + 1);
        if (this.getTick() >= this.getFrameSpeed()) {
            this.setTick(0);
            this.setFrame(this.getFrame() + 1);
            if (this.getFrame() >= 6) {
                this.setFrame(0);
            }
        }
        g.drawImage(this.getImage(this.getFrame()), this.getY() * TAILLE_CASE, this.getX() * TAILLE_CASE + DrawingPanel.ECART, TAILLE_CASE, TAILLE_CASE, null);

    }

    /**
     * Méthode qui permet de récupérer des points de vie
     *
     * @param t : Tour courante
     */
    @Override
    public void eventCollider(Tour t) {
        //Nombre de points de vie récupérés
        double HEAL_AMOUNT = 4;
        t.getHeros().ajouterPDV(HEAL_AMOUNT);
        t.getCurrentLevel().setCaseToSol(this.getX(), this.getY());
    }
}
