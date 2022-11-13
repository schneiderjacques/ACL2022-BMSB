package main.screen;

import main.Engine.DrawingPanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuScreen{
    /**
     * Image de fond du menu démarrer
     * */
    private BufferedImage backgroundImage;
    /**
     * DrawingPanel du jeu
     * */
    private DrawingPanel dp;


    /**
     * Titre du jeu
     * */
    private final String title = "ACL BMSB - 2022";
    /**
     * Nom du bouton démarrer
     * */
    private final String launch = "Launch";
    /**
     * Nom du bouton quitter
     * */
    private final String exit = "Exit";

    /**
     * Constructeur de la classe MenuScreen
     * @param dp DrawingPanel du jeu
     * */
    public MenuScreen(DrawingPanel dp){
        this.dp = dp;
        this.backgroundImage = new BufferedImage(DrawingPanel.TILE_SIZE,DrawingPanel.TILE_SIZE,BufferedImage.TYPE_INT_ARGB);
        try{
            backgroundImage = ImageIO.read(getClass().getClassLoader().getResource("images/menu/background.jpg"));
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResource("font/Minecraft.ttf").openStream()));
        } catch (IOException|FontFormatException e) {
            e.printStackTrace();
        }
    }


    /**
     * Dessine le menu démarrer
     * @param g2 Graphics du DrawingPanel
     *
     * */
    public void draw(Graphics2D g2) {
        g2.drawImage(this.backgroundImage , 0, 0 , dp.getScreenWidth(), dp.getScreenHeight(), null);

        Color start = new Color(228,236,240,255);
        Color end = new Color(133,182,183,255);
        g2.setFont(new Font("Minecraft", Font.BOLD, 50));

        GradientPaint grandient = new GradientPaint(0,0,start,dp.getScreenWidth(),dp.getScreenHeight(),end);
        g2.setPaint(grandient);
        g2.drawString(title,dp.getScreenWidth()/2 - (title.length()*14),dp.getScreenHeight()/2 + 130);

        g2.setFont(new Font("Minecraft", Font.BOLD, 30));
        g2.drawString(launch,dp.getScreenWidth()/2 - (launch.length()*13),dp.getScreenHeight()/2 + 200);
        g2.drawString(exit,dp.getScreenWidth()/2 - (exit.length()*13),dp.getScreenHeight()/2 + 250);


    }
}
