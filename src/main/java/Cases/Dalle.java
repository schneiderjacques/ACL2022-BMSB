package main.java.Cases;

import main.java.Engine.DrawingPanel;
import main.java.Principale.Niveau;
import main.java.Principale.Tour;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Dalle extends Case {
    /**
     * Constructeur de la classe Case
     *
     * @param x
     * @param y
     */
    public Dalle(int x, int y) {
        super(false, x, y, Color.white);
    }

    @Override
    public void eventCollider(Tour t) {
        Niveau n = t.getCurrentLevel();
        openPorte(n.getCaseObject(this.getY() + 1, this.getX()));
        openPorte(n.getCaseObject(this.getY() - 1, this.getX()));
        openPorte(n.getCaseObject(this.getY(), this.getX() + 1));
        openPorte(n.getCaseObject(this.getY(), this.getX() - 1));

    }
    public void openPorte(Case c){
        if (c instanceof Passage){
            ((Passage) c).open();
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
