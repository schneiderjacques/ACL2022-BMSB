package main.Controller;

import main.Principale.Jeu;
import main.Engine.Cmd;
import main.Engine.GameController;

import java.awt.event.KeyEvent;

/*
 * Controlleur qui permet de gérer les mouvements du joueur
 * @author Anthony Briot
 */
public class ControllerMouvement implements GameController {

    //Le jeu courant
    private Jeu jeu;

    /**
     * commande en cours
     */
    private Cmd commandeEnCours;

    /**
     * construction du controleur par defaut le controleur n'a pas de commande
     */
    public ControllerMouvement(Jeu jeu) {
        //On initialise le jeu
        this.jeu = jeu;
        this.commandeEnCours = Cmd.IDLE;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.jeu.getTour().moveHeros('Y', -1);
                this.commandeEnCours = Cmd.UP;
                break;
            case KeyEvent.VK_DOWN:
                this.jeu.getTour().moveHeros('Y', 1);
                this.commandeEnCours = Cmd.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                this.jeu.getTour().moveHeros('X', -1);
                this.commandeEnCours = Cmd.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                this.jeu.getTour().moveHeros('X', 1);
                this.commandeEnCours = Cmd.RIGHT;
                break;
        }
    }

    @Override
    /**
     * met a jour les commandes quand le joueur relache une touche
     */
    public void keyReleased(KeyEvent e) {
        this.commandeEnCours = Cmd.IDLE;
    }
    /**
     * quand on demande les commandes, le controleur retourne la commande en
     * cours
     *
     * @return commande faite par le joueur
     */
    public Cmd getCommand() {
        return this.commandeEnCours;
    }

    @Override
    public Jeu getJeu() {
        return this.jeu;
    }
}
