package main.Controller;

import main.Engine.DrawingPanel;
import main.Principale.Jeu;
import main.Engine.Cmd;
import main.Engine.GameController;

import java.awt.event.KeyEvent;

/**
 * Controlleur qui permet de gérer les mouvements du joueur
 *
 * @author Anthony Briot
 */
public class ControllerMouvement implements GameController {

    //Le jeu courant
    private final Jeu jeu;

    /**
     * commande en cours
     */
    private Cmd commandeEnCours;


    /**
     * Permet l'interaction avec le drawing panel
     * */
    private DrawingPanel dp;

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
        if (this.jeu.getGameState() == 1) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> {
                    this.jeu.getTour().moveHeros('Y', -1);
                    this.commandeEnCours = Cmd.UP;
                }
                case KeyEvent.VK_DOWN -> {
                    this.jeu.getTour().moveHeros('Y', 1);
                    this.commandeEnCours = Cmd.DOWN;
                }
                case KeyEvent.VK_LEFT -> {
                    this.jeu.getTour().moveHeros('X', -1);
                    this.commandeEnCours = Cmd.LEFT;
                }
                case KeyEvent.VK_RIGHT -> {
                    this.jeu.getTour().moveHeros('X', 1);
                    this.commandeEnCours = Cmd.RIGHT;
                }
                case KeyEvent.VK_SPACE -> {
                    this.jeu.getTour().heroAttaque();
                    this.commandeEnCours = Cmd.SPACE;
                }
            }
        } else if (this.jeu.getGameState() == 0) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ENTER -> {
                    this.commandeEnCours = Cmd.ENTER;
                    if (dp.getMenuScreen().getCommandNum() == 0 ){
                        this.jeu.demarreJeu();
                    } else {
                        System.exit(0);
                    }
                }
                case KeyEvent.VK_UP -> {
                    this.commandeEnCours = Cmd.UP;
                    dp.getMenuScreen().setCommandNum(0);
                }
                case KeyEvent.VK_DOWN -> {
                    this.commandeEnCours = Cmd.DOWN;
                    dp.getMenuScreen().setCommandNum(1);
                }

            }
        }
    }

    @Override
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

    @Override
    public void setDrawingPanel(DrawingPanel dp) {
        this.dp = dp;
    }
}
