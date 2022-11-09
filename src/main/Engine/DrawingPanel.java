package main.Engine;

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

	public static int originalTileSize = 16; // 16x16 tile
	public static int scale = 3; //3x plus grand

	public static final int TILE_SIZE = originalTileSize * scale; //48x48 tile
	public static final int ECART = 16 * scale; //le niveau commence 48 pixels plus bas afin de laisser de la place pour l'UI
	private int maxScreenCol; //Nombre de colonne maximum visible sur l'écran
	private int maxScreenRow; //Nombre de ligne maximum visible sur l'écran
	private int screenWidth;
	private int screenHeight;


	//LEVEL SETTINGS

	private int maxLevelCol; //Nombre de colonne maximum dans le niveau
	private int maxLevelRow; //Nombre de ligne maximum dans le niveau
	private int worldWidth; //Largeur du monde en pixel
	private int worldHeight; //Hauteur du monde en pixel

	private UI ui;

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
	public DrawingPanel(GamePainter painter, Jeu jeu) {
		super();
		this.jeu = jeu;
		this.maxScreenCol = jeu.getTour().getCurrentLevel().getLargeur();
		this.maxScreenRow = jeu.getTour().getCurrentLevel().getLongueur() + 1; //+1 pour laisser de la place pour l'UI
		this.screenWidth = TILE_SIZE * maxScreenCol;
		this.screenHeight = TILE_SIZE * maxScreenRow;
		this.width = this.screenWidth;
		this.height = this.screenHeight;

		// Initialise les max
		this.maxLevelCol = jeu.getTour().getCurrentLevel().getLargeur();
		this.maxLevelRow = jeu.getTour().getCurrentLevel().getLongueur();
		this.worldWidth = TILE_SIZE * maxLevelCol;
		this.worldHeight = TILE_SIZE * maxLevelRow;


		this.setPreferredSize(new Dimension(this.maxScreenCol * TILE_SIZE, this.maxScreenRow * TILE_SIZE));
		this.painter=painter;

		this.ui = new UI(this);

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
		ui.draw(g2);

	}

	/**
	 * @return Jeu instance du jeu
	 * */
	public Jeu getJeu() {
		return jeu;
	}

	public int getScreenWidth() {
		return screenWidth;
	}
}
