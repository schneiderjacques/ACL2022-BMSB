package main.Cases;

import main.Principale.Niveau;
import main.Principale.Tour;

import java.awt.*;
import java.util.Objects;

public class Dalle extends Case{
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
        if(Objects.equals(n.getCase(this.getY() + 1, this.getX()).getType(), "Porte")){
            ((Passage)n.getCase(this.getY() + 1, this.getX())).open();
        }
        if(Objects.equals(n.getCase(this.getY() - 1, this.getX()).getType(), "Porte")){
            ((Passage)n.getCase(this.getY() - 1, this.getX())).open();
        }
        if(Objects.equals(n.getCase(this.getY(), this.getX() + 1).getType(), "Porte")){
            ((Passage)n.getCase(this.getY(), this.getX() + 1)).open();
        }
        if(Objects.equals(n.getCase(this.getY(), this.getX() - 1).getType(), "Porte")){
            ((Passage)n.getCase(this.getY(), this.getX() - 1)).open();
        }
    }

    @Override
    public String getType() {
        return "Levier";
    }
}
