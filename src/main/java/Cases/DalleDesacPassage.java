package main.java.Cases;

import main.java.Principale.Niveau;
import main.java.Principale.Tour;

import java.awt.image.BufferedImage;

public class DalleDesacPassage extends Case {
    /**
     * Constructeur de la classe Case
     *
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public DalleDesacPassage(int x, int y) {
        super(false, x, y);
    }

    @Override
    public void eventCollider(Tour t) {
        Niveau n = t.getCurrentLevel();
        closePorte(n.getCaseObject(this.getY() + 1, this.getX()));
        closePorte(n.getCaseObject(this.getY() - 1, this.getX()));
        closePorte(n.getCaseObject(this.getY(), this.getX() + 1));
        closePorte(n.getCaseObject(this.getY(), this.getX() - 1));

    }
    public void closePorte(Case c){
        if (c instanceof Passage){
            ((Passage) c).close();
        }
    }

    @Override
    public String getType() {
        return "Levier";
    }

    @Override
    public void draw(BufferedImage image) {
        return;
    }
}
