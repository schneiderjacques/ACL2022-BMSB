package main.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Principale.Jeu;
import main.engine.GamePainter;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 *
 */
public class JeuPainter implements GamePainter {

	/**
	 * la taille des cases
	 */
	protected static final int TAILLE_CASE = 16*3;

	/**
	 * appelle constructeur parent
	 *
	 * @param game
	 *            le jeutest a afficher
	 */
	private Jeu jeu;
	public JeuPainter(Jeu jeu) {
		this.jeu = jeu;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.blue);
		crayon.fillRect(jeu.getTour().getHeros().getX()*TAILLE_CASE,jeu.getTour().getHeros().getY()*TAILLE_CASE,TAILLE_CASE,TAILLE_CASE);
	}

	@Override
	public int getWidth() {
		return this.TAILLE_CASE;
	}

	@Override
	public int getHeight() {
		return this.TAILLE_CASE;
	}
	@Override
	public Jeu getJeu() {
		return this.jeu;
	}

}
