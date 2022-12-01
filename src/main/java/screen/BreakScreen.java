package main.java.screen;

import main.java.Engine.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BreakScreen {

    /**
     * Image de fond du menu démarrer
     */
    private BufferedImage backgroundImage;
    /**
     * DrawingPanel du jeu
     */
    private DrawingPanel dp;


    /**
     * Titre du jeu
     */
    private String title = "Pause";
    /**
     * Nom du bouton restart
     */
    private final String restart = "Restart";
    /**
     * Nom du bouton quitter
     */
    private final String exit = "Quitter";

    /**
     * Nom du bouton continuer
     */
    private final String resume = "Continuer";

    /**
     *
     */
    private int commandNum = 0;

    /**
     * Constructeur de la classe MenuScreen
     *
     * @param dp DrawingPanel du jeu
     */
    public BreakScreen(DrawingPanel dp) {
        this.dp = dp;
        this.backgroundImage = new BufferedImage(DrawingPanel.TILE_SIZE, DrawingPanel.TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
        try {
            backgroundImage = ImageIO.read(MenuScreen.class.getResource("/images/menu/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, MenuScreen.class.getResource("/font/Minecraft.ttf").openStream()));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }


    /**
     * Dessine le menu démarrer
     *
     * @param g2 Graphics du DrawingPanel
     */
    public void draw(Graphics2D g2) {
        //Afficher la commande en cours


        //Image de fond
        g2.drawImage(this.backgroundImage, 0, 0, dp.getScreenWidth(), dp.getScreenHeight(), null);


        //Setup de la font et du gradient de couleur
        Color start = new Color(228, 236, 240, 255);
        Color end = new Color(133, 182, 183, 255);
        g2.setFont(new Font("Minecraft", Font.BOLD, 50));
        GradientPaint grandient = new GradientPaint(0, 0, start, dp.getScreenWidth(), dp.getScreenHeight(), end);
        g2.setPaint(grandient);

        //Dessine le titre du jeu
        g2.drawString(title, dp.getScreenWidth() / 2 - (title.length() * 20), dp.getScreenHeight() / 2 + 50);

        if (commandNum == 0) {
            g2.drawString(">", dp.getScreenWidth() / 2 - 150, dp.getScreenHeight() / 2 + 155);
        } else if (commandNum == 1) {
            g2.drawString(">", dp.getScreenWidth() / 2 - 150, dp.getScreenHeight() / 2 + 205);
        }else if (commandNum == 2) {
            g2.drawString(">", dp.getScreenWidth() / 2 - 150, dp.getScreenHeight() / 2 + 255);
        }


        //Nouvelle font size
        g2.setFont(new Font("Minecraft", Font.BOLD, 30));
        //Dessine le bouton continuer
        g2.drawString(resume, dp.getScreenWidth() / 2 - (resume.length() * 13), dp.getScreenHeight() / 2 + 150);
        //Dessine le bouton démarrer
        g2.drawString(restart, dp.getScreenWidth() / 2 - (restart.length() * 13), dp.getScreenHeight() / 2 + 200);
        //Dessine le bouton quitter
        g2.drawString(exit, dp.getScreenWidth() / 2 - (exit.length() * 13), dp.getScreenHeight() / 2 + 250);



    }


    /**
     * @param commandNum numéro de commande
     *
     * */
    public void setCommandNum(int commandNum) {
        this.commandNum = commandNum;
    }

    /**
     * @return numéro de commande
     * */
    public int getCommandNum() {
        return commandNum;
    }

    /**
     * Set le titre du jeu
     * @param title titre du jeu
     * */
    public void setTitle(String title) {
        this.title = title;
    }



}
