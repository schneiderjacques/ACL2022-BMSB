package main.java.Engine;

import main.java.Principale.Jeu;

import java.io.FileNotFoundException;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	/**
	 * le game a executer
	 */
	private Jeu game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	/**
	 * construit un moteur
	 *
	 * @param jeu
	 *            jeu a lancer
	 * @param gameController
	 *            controlleur a utiliser
	 *
	 */
	public GameEngineGraphical(Jeu jeu, GameController gameController) {
		// creation du jeu
		this.game = jeu;
		this.gamePainter = jeu.getTour().getCurrentLevel();
		this.gameController = gameController;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException, FileNotFoundException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);
		this.gameController.setDrawingPanel(gui.getPanel());
		double drawInterval = 1000000000.0 / 60.0;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		// boucle de game
		while (!this.game.isFinished()) {
			if (this.game.getTour().levelChanged()) {
				this.game.getTour().setLevelChanged(false);
				this.gui.nextLevel(this.game.getTour().getCurrentLevel());
			}

			if (this.game.isWon() && this.game.getGameState() != 2) {
				this.game.addScore();
				this.game.setGameState(2);
				this.game.getTour().clearLevel();
			} else if (this.game.isLost() && this.game.getGameState() != 3) {
				this.game.addScore();
				this.game.setGameState(3);
				this.game.getTour().clearLevel();
			}

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if(delta >= 1 ) {

				// demande controle utilisateur
				Cmd c = this.gameController.getCommand();
				// fait evoluer le game
				this.game.evolve(c);
				// affiche le game
				this.gui.paint();
				// met en attente
				delta--;
				drawCount++;
			}
			if(timer >= 1000000000) {
				//System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}

		}

	}

}
