package main.Engine;

import main.Principale.Jeu;

import java.awt.event.KeyListener;

/**
 * @author Horatiu Cirstea
 *
 * controleur qui envoie des commandes au jeu
 *
 */
public interface GameController extends KeyListener {

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 *
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand();

	public Jeu getJeu();

	public void setDrawingPanel(DrawingPanel dp);

}
