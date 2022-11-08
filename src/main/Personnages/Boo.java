package main.Personnages;

import main.Principale.Niveau;

import java.awt.*;

/**
 * Class représentant un personnage du jeu
 * @author Arthur Moitrier
 */
public class Boo extends Monstre{

    /**
     * Constructeur du monstre Boo
     * @param x : position en X
     * @param y : position en Y
     * @param n : niveau dans lequel se trouve le monstre
     */
    public Boo(int x, int y, Niveau n) {
        super(x, y, false, 10, 1, n, Color.MAGENTA);
    }

    /**
     * Constructeur du monstre Boo
     * @param x : position en X
     * @param y : position en Y
     */
    public Boo(int x, int y) {
        super(x, y, false, 10, 1, Color.MAGENTA);
    }

    /**
     * Méthode qui retourne le nom du monstre
     * @return : nom du monstre
     */
    @Override
    public String getType() {
        return "Boo";
    }
}
