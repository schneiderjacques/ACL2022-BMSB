package main.engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 */
import main.Principale.Jeu;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * la clase chargee de Dessiner
	 */
	private GamePainter painter;

	/**
	 * image suivante est l'image cachee sur laquelle dessiner
	 */
	private BufferedImage nextImage;

	/**
	 * image en cours est l'image entrain d'etre affichee
	 */
	private BufferedImage currentImage;

	public int originalTileSize = 16; // 16x16 tile
	public int scale = 3; //3x plus grand

	public final int tileSize = originalTileSize * scale; //48x48 tile
	private final int maxScreenCol = 16; //Nombre de colonne maximum visible sur l'écran
	private final int maxScreenRow = 12; //Nombre de ligne maximum visible sur l'écran
	private final int screenWidth = tileSize * maxScreenCol;
	private final int screenHeight = tileSize * maxScreenRow;


	//LEVEL SETTINGS

	private int maxLevelCol; //Nombre de colonne maximum dans le niveau
	private int maxLevelRow; //Nombre de ligne maximum dans le niveau
	private int worldWidth; //Largeur du monde en pixel
	private int worldHeight; //Hauteur du monde en pixel
	private Jeu jeu;



	/**
	 * la taille des images
	 */
	private int width, height;

	/**
	 * constructeur Il construit les images pour doublebuffering ainsi que le
	 * Panel associe. Les images stockent le painter et on demande au panel la
	 * mise a jour quand le painter est fini
	 */
	public DrawingPanel(GamePainter painter) {
		super();
		this.width = this.screenWidth;
		this.height = this.screenHeight;

		// Récupère le jeu
		jeu = painter.getJeu();
		// Initialise les max
		this.maxLevelCol = jeu.getTour().getCurrentLevel().getLargeur();
		this.maxLevelRow = jeu.getTour().getCurrentLevel().getLongueur();
		this.worldWidth = tileSize * maxLevelCol;
		this.worldHeight = tileSize * maxLevelRow;


		this.setPreferredSize(new Dimension(this.maxScreenCol * tileSize, this.maxScreenRow * tileSize));
		this.painter=painter;

		// cree l'image buffer et son graphics
		this.nextImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.currentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


	}

	/**
	 * demande de mettre a jour le rendu de l'image sur le Panel. Creer une
	 * nouvelle image vide sur laquelle dessiner
	 */
	public void drawGame() {
		// generer la nouvelle image
		this.painter.draw(this.nextImage);

		// inverses les images doublebuffereing
		BufferedImage temp = this.currentImage;
		// l'image a dessiner est celle qu'on a construite
		this.currentImage = this.nextImage;
		// l'ancienne image est videe
		this.nextImage = temp;
		this.nextImage.getGraphics().fillRect(0, 0, this.width, this.height);
		// met a jour l'image a afficher sur le panel
		this.repaint();
	}

	/**
	 * redefinit la methode paint consiste a dessiner l'image en cours
	 *
	 * @param g
	 *            graphics pour dessiner
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(this.currentImage, 0, 0, getWidth(), getHeight(), 0, 0, getWidth(), getHeight(), null);

	}

}
