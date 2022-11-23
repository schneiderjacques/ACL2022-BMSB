package main.java.Cases;

import main.java.Principale.Tour;

import java.awt.*;

public class Passage extends Case {

    /**
     * Constructeur de la classe Case
     *
     * @param x : emplacement de la case en X
     * @param y : emplacement de la case en Y
     */
    public Passage(int x, int y) {
        super(true, x, y, Color.BLACK);
    }

    @Override
    public void eventCollider(Tour t) {
        this.setCollision(true);
        this.setColor(Color.BLACK);
    }

    @Override
    public String getType() {
        return "Porte";
    }

    public void open(){
        this.setCollision(false);
        this.setColor(Color.darkGray);
    }
}
