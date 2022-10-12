package main.Controller;

import main.Principale.Jeu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Controlleur qui permet de gérer les mouvements du joueur
 * @author Anthony Briot
 */
public class ControllerMouvement implements KeyListener {

    //Le jeu courant
    private Jeu jeu;

    /*
     * Constructeur du controlleur
     */
    public ControllerMouvement(Jeu jeu) {
        //On initialise le jeu
        this.jeu = jeu;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.jeu.getTour().moveHeros('Y', -1);
                break;
            case KeyEvent.VK_DOWN:
                this.jeu.getTour().moveHeros('Y', 1);
                break;
            case KeyEvent.VK_LEFT:
                this.jeu.getTour().moveHeros('X', -1);
                break;
            case KeyEvent.VK_RIGHT:
                this.jeu.getTour().moveHeros('X', 1);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
