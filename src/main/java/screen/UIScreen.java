package main.java.screen;

import main.java.Engine.DrawingPanel;
import main.java.Principale.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UIScreen {

    private DrawingPanel dp;
    private BufferedImage keyImage;

    private BufferedImage keyImageFound;
    private BufferedImage healthImage;
    private BufferedImage healthDecorationImage;
    private boolean imageChanged = false;
    private int maxHealthWidth = 110;
    private int healthBarWidth;

    /**
     * Constructeur de la classe UIScreen
     * @param drawingPanel DrawingPanel du jeu
     * */
    public UIScreen(DrawingPanel drawingPanel) {
        this.dp = drawingPanel;
        keyImage = Tools.getImageByName("/images/ui/key_not_found");
        keyImageFound = Tools.getImageByName("/images/ui/key_found");
        healthImage = Tools.getImageByName("/images/ui/health_bar");
        healthDecorationImage = Tools.getImageByName("/images/ui/health_bar_decoration");
        double divider = (this.dp.getJeu().getTour().getHeros().getPDV() / 10) * 100; // 10 max / 10      //10 max / 5
        this.healthBarWidth = (int) (divider * this.maxHealthWidth / 100);

    }
    /**
     * Dessine l'UI
     * @param g2 Graphics du DrawingPanel
     *
     * */
    public void draw(Graphics2D g2) {

        //Barre horizontale grise

        g2.setColor(new Color(92,105,159, 255));
        g2.fillRect(this.dp.getCamX(),this.dp.getCamY(), this.dp.getWidth(), DrawingPanel.TILE_SIZE);


        //Mise en place d'une nouvelle fonte et écriture du text du niveau
        g2.setFont(new Font("Minecraft", Font.BOLD, 20));
        g2.setColor(Color.BLACK);
        //affichage du niveau en cours
        g2.drawString("Niveau : " + dp.getJeu().getTour().getLevelNumber(), this.dp.getCamX() + 10, this.dp.getCamY()  + DrawingPanel.TILE_SIZE/2+5);


        //Si la clé est trouvée on change l'image affichée
        if (dp.getJeu().getTour().getCurrentLevel().isKeyFound() && !imageChanged){
            g2.drawImage(keyImageFound, this.dp.getCamX() + (dp.getScreenWidth()-DrawingPanel.TILE_SIZE), this.dp.getCamY(), DrawingPanel.TILE_SIZE,DrawingPanel.TILE_SIZE , null);
        } else {
            g2.drawImage(keyImage, this.dp.getCamX() + (dp.getScreenWidth()-DrawingPanel.TILE_SIZE), this.dp.getCamY(), DrawingPanel.TILE_SIZE,DrawingPanel.TILE_SIZE , null);
        }



        //Calcule de la taille de la barre de vie
        double divider = (this.dp.getJeu().getTour().getHeros().getPDV() / 10) * 100; // 10 max / 10      //10 max / 5
        this.healthBarWidth = (int) (divider * this.maxHealthWidth / 100);


        //affichage de la barre de vie
        g2.drawImage(healthDecorationImage, this.dp.getCamX() + dp.getScreenWidth()/2 - (60), this.dp.getCamY() + 5, 150,42 , null);
        g2.drawImage(healthImage, this.dp.getCamX() + dp.getScreenWidth()/2+20 - (maxHealthWidth/2-12), this.dp.getCamY() + 5, healthBarWidth,42 , null);
    }
}
