package main.Personnages;

import main.Principale.Niveau;

import java.awt.*;

/**
 * Class représentant un personnage du jeu
 * @author Arthur Moitrier
 */
public class Goomba extends Monstre {

    /**
     * Constructeur du monstre Goomba
     * @param x : position en X
     * @param y : position en Y
     * @param n : niveau dans lequel se trouve le monstre
     */
    public Goomba(int x, int y, Niveau n) {super(x, y, true, 15, 2, n, Color.pink);}

    /**
     * Constructeur du monstre Goomba
     * @param x : position en X
     * @param y : position en Y
     */
    public Goomba(int x, int y) {super(x, y, true, 15, 2, Color.pink);}


    /**
     * Méthode qui retourne le nom du monstre
     * @return : nom du monstre
     */
    @Override
    public String getType() {
        return "Goomba";
    }
}
