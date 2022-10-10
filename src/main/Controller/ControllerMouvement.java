package main.Controller;

import main.Cases.Case;
import main.Personnages.Heros;
import main.Principale.Jeu;
import main.Principale.Labyrinthe;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Controlleur qui permet de gérer les mouvements du joueur
 * @author Anthony Briot
 */
public class ControllerMouvement implements KeyListener {

    //Le jeu courant
    private Jeu jeu;

    private Heros heros;

    /*
     * Constructeur du controlleur
     */
    public ControllerMouvement(Jeu jeu) {
        //On initialise le jeu
        this.jeu = jeu;
        //On initialise le heros
        this.heros = jeu.getHeros();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Case c = null;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                c = this.jeu.getLabyrinthe().getCase(this.heros.getX(), this.heros.getY() - 1);
                if (!c.getCollision()) {
                    heros.moveY(-1);
                    System.out.println("UP");
                } else {
                    System.out.println("Impossible d'avancer");
                }
                break;
            case KeyEvent.VK_DOWN:
                c = this.jeu.getLabyrinthe().getCase(this.heros.getX(), this.heros.getY() + 1);
                if (!c.getCollision()) {
                    heros.moveY(1);
                    System.out.println("DOWN");
                } else {
                    System.out.println("Impossible d'avancer");
                }
                break;
            case KeyEvent.VK_LEFT:
                c = this.jeu.getLabyrinthe().getCase(this.heros.getX() - 1, this.heros.getY());
                if (!c.getCollision()) {
                    heros.moveX(-1);
                    System.out.println("LEFT");
                } else {
                    System.out.println("Impossible d'avancer");
                }
                break;
            case KeyEvent.VK_RIGHT:
                c = this.jeu.getLabyrinthe().getCase(this.heros.getX() + 1, this.heros.getY());
                if (!c.getCollision()) {
                    heros.moveX(1);
                    System.out.println("RIGHT");
                } else {
                    System.out.println("Impossible d'avancer");
                }
                break;
        }

        //Temporaire
        System.out.println("Vos nouvelles coordonnées : " + this.heros.getX() + ";" + this.heros.getY());
        jeu.getLabyrinthe().printMap(this.heros);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
