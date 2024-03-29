package main.java.Engine;

import main.java.Principale.Niveau;

import javax.swing.JFrame;
import java.awt.*;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * interface graphique avec son controller et son afficheur
 *
 */
public class GraphicalInterface  {

	/**
	 * le Panel pour l'afficheur
	 */
	private DrawingPanel panel;

	/**
	 * le controller
	 */
	private GameController controller;

	/**
	 * le frame
	 */
	private JFrame frame;

	/**
	 * construit une interface graphique
	 */
	private GamePainter gamePainter;

	private GraphicsDevice gDevice;

	/**
	 * la construction de l'interface graphique: JFrame avec panel pour le game
	 *
	 * @param gamePainter l'afficheur a utiliser dans le moteur
	 * @param gameController l'afficheur a utiliser dans le moteur
	 *
	 */
	public GraphicalInterface(GamePainter gamePainter, GameController gameController){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gDevice = ge.getDefaultScreenDevice();

		this.controller = gameController;
		this.gamePainter = gamePainter;
		this.frame = new JFrame();
		this.panel=new DrawingPanel(this.gamePainter, controller.getJeu());
		buildFrame();
	}

	/**
	 * construit le frame
	 */
	private void buildFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// attacher le panel contenant l'afficheur du game
		frame.setContentPane(this.panel);

		// attacher controller au panel du game
		this.panel.addKeyListener(controller);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getContentPane().setFocusable(true);
		frame.getContentPane().requestFocus();
		this.frame.setResizable(false);
	}

	/**
	 * mise a jour du dessin
	 */
	public void paint() {
		this.panel.drawGame();
	}

	/**
	 * Méthode permettant de passer à l'affichage du niveau suivant
	 * @param currentLevel
	 *  		le niveau courant
	 */
    public void nextLevel(Niveau currentLevel) {
		this.panel.nextLevel(currentLevel);
	}

	/**
	 * Getter du panel
	 * @return le panel
	 */
	public DrawingPanel getPanel() {
		return this.panel;
	}
}
