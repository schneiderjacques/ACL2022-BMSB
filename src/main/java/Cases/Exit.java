package main.java.Cases;

import main.java.Engine.DrawingPanel;
import main.java.Principale.Tools;
import main.java.Principale.Tour;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class représentant la sortie du labyrinthe
 * @author Anthony Briot
 */
public class Exit extends Case {

    /**
    * Constructeur de la case exit
    * @param x : emplacement de la case en X
    * @param y : emplacement de la case en Y
     */
    public Exit(int x, int y) {
        //Initialisation des attributs
        super(true, x, y);
        this.initImage(1);
        this.setImage(Tools.getImageByName("/images/game/objects/door_closed"),0);
    }

    /**
     * Getter du type de case
     * @return type de case
     */
    public String getType() {
        return "Exit";
    }

    public void open() {
        this.setCollision(false);
        this.setImage(Tools.getImageByName("/images/game/objects/door_open"),0);
    }


    /**
     * Méthode qui permet de savoir si le héros peut sortir du labyrinthe
     * @param t : Tour courante
     */
    @Override
    public void eventCollider(Tour t) {
        //System.out.println(t.getCurrentLevel().isKeyFound());
        if (t.getCurrentLevel().isLastLevel() && t.getCurrentLevel().isKeyFound()) {
            t.setFini(true);
        } else if (t.getCurrentLevel().isKeyFound()){
            //System.out.println("Niveau suivant");
            t.nextLevel();
        }
    }

    @Override
    public void draw(BufferedImage image) {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.drawImage(this.getImage(this.getFrame()), this.getY()*TAILLE_CASE, this.getX()*TAILLE_CASE + DrawingPanel.ECART, TAILLE_CASE, TAILLE_CASE, null);
    }
}
