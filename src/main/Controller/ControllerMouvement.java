package src.main.Controller;

import src.main.Personnages.Heros;
import src.main.Principale.Jeu;

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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                heros.moveY(-1);
                System.out.println("UP");
                break;
            case KeyEvent.VK_DOWN:
                heros.moveY(1);
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                heros.moveX(-1);
                System.out.println("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                heros.moveX(1);
                System.out.println("RIGHT");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
