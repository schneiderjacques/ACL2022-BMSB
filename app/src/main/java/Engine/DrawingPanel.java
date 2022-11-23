package main.java.Engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 */

import main.java.Principale.Jeu;
import main.java.screen.EndScreen;
import main.java.screen.MenuScreen;
import main.java.screen.UIScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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

	/**
	 * Met en place l'UI
	 */
	private UIScreen uiScreen;

	private Jeu jeu;

	/**
	 * Ecran de démarrage du jeu
	 */
	private MenuScreen menuScreen;

	private EndScreen endScreen;


	/**
	 * Camera position X
	 */
	private int camX;
	/**
	 * Camera position Y
	 */
	private int camY;
	/**
	 * Max camera position X
	 */
	private int offsetMaxX;
	/**
	 * Max camera position Y
	 */
	private int offsetMaxY;
	/**
	 * Min camera position X
	 */
	private int offsetMinX = 0;
	/**
	 * Min camera position Y
	 */
	private int offsetMinY = 0;

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

		//Viewport Size
		this.maxScreenCol = 20;
		this.maxScreenRow = 12; //+1 pour laisser de la place pour l'UI

		// Taille du monde
		this.maxLevelCol = jeu.getTour().getCurrentLevel().getLargeur();
		this.maxLevelRow = jeu.getTour().getCurrentLevel().getLongueur();

		this.screenWidth = TILE_SIZE * maxScreenCol; //960 pixels
		this.screenHeight = TILE_SIZE * maxScreenRow; //576 pixels









		this.worldWidth = TILE_SIZE * maxLevelCol;
		this.worldHeight = TILE_SIZE * maxLevelRow + TILE_SIZE; //+48 pixels pour laisser de la place pour l'UI

		this.width = worldWidth;
		this.height = worldHeight;



		//Dimensions du monde
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setSize(worldWidth, worldHeight);


		this.painter=painter;

		//Camera max X and max Y
		this.offsetMaxX = worldWidth - screenWidth;
		this.offsetMaxY = worldHeight - screenHeight;

		//Creation du menu de depart
		this.menuScreen = new MenuScreen(this);

		//Creation de l'ecran de fin
		this.endScreen = new EndScreen(this);

		//Creation de l'UI
		this.uiScreen = new UIScreen(this);

		// cree l'image buffer et son graphics
		this.nextImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.currentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		requestFocus();


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
		switch (this.jeu.getGameState()) {
			case 0:
				this.menuScreen.draw(g2);
				break;
			case 1:
				this.camX = this.jeu.getTour().getHeros().getX() * TILE_SIZE - screenWidth / 2;
				this.camY = this.jeu.getTour().getHeros().getY() * TILE_SIZE - screenHeight / 2;
				checkCamera();
				g2.translate(-camX, -camY);

				g2.drawImage(this.currentImage, 0, 0, this.worldWidth, this.worldHeight, 0, 0, worldWidth, worldHeight, null);
				uiScreen.draw(g2);
				break;
			case 2:
				this.endScreen.setTitle("You won !");
				this.endScreen.draw(g2);
				this.requestFocus();
				break;
			case 3:
				this.endScreen.setTitle("You lost !");
				this.endScreen.draw(g2);
				this.requestFocus();
				break;
		}

		g2.dispose();
	}

	/**
	 * @return Jeu instance du jeu
	 * */
	public Jeu getJeu() {
		return jeu;
	}

	/**
	 * @return screenWidth largeur de l'écran
	 * */
	public int getScreenWidth() {
		return screenWidth;
	}
	/**
	 * Retourne la hauteur de l'écran
	 * @return screenHeight hauteur de l'écran
	 */
	public int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * Vérifie si la caméra est dans les limites du niveau
	 *
	 * */
	public void checkCamera(){
		if(camX < offsetMinX){
			camX = offsetMinX;
		}
		if(camX > offsetMaxX){
			camX = offsetMaxX;
		}
		if(camY < offsetMinY){
			camY = offsetMinY;
		}
		if(camY > offsetMaxY){
			camY = offsetMaxY;
		}
	}
	/**
	 * @return X position de la caméra
	 */
	public int getCamX() {
		return camX;
	}
	/**
	 * @return Y position de la caméra
	 */
	public int getCamY() {
		return camY;
	}

	/**
	 * @return MenuScreen instance du menu
	 */
	public MenuScreen getMenuScreen() {
		return menuScreen;
	}

	/**
	 * @return EndScreen instance de l'écran de fin
	 */
	public EndScreen getEndScreen() {
		return endScreen;
	}
}
