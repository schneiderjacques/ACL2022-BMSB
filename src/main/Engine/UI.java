package main.Engine;

import main.Main;
import main.Principale.Niveau;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI{

    private DrawingPanel dp;
    private BufferedImage keyImage;
    private BufferedImage healthImage;
    private BufferedImage healthDecorationImage;
    private boolean imageChanged = false;
    private int maxHealthWidth = 110;
    private int healthBarWidth;
    public UI(DrawingPanel drawingPanel) {
        this.dp = drawingPanel;
        keyImage = getImage("key_not_found");
        healthImage = getImage("health_bar");
        healthDecorationImage = getImage("health_bar_decoration");
        double divider = (this.dp.getJeu().getTour().getHeros().getPDV() / 10) * 100; // 10 max / 10      //10 max / 5
        this.healthBarWidth = (int) (divider * this.maxHealthWidth / 100);

    }
    public void draw(Graphics2D g2) {

        //Barre horizontale grise
        g2.setColor(Color.gray);
        g2.fillRect(this.dp.getCamX(),this.dp.getCamY(), this.dp.getWidth(), DrawingPanel.TILE_SIZE);


        //Mise en place d'une nouvelle fonte et écriture du text du niveau
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setColor(Color.BLACK);
        //affichage du niveau en cours
        g2.drawString("Niveau : " + dp.getJeu().getTour().getLevelNumber(), this.dp.getCamX() + 10, this.dp.getCamY()  + DrawingPanel.TILE_SIZE/2+5);


        //Si la clé est trouvée on change l'image affichée
        if (dp.getJeu().getTour().getCurrentLevel().isKeyFound() && !imageChanged){
            changeImage();
        }
        //affichage du nombre de clés
        g2.drawImage(keyImage, this.dp.getCamX() + (dp.getScreenWidth()-DrawingPanel.TILE_SIZE), this.dp.getCamY(), DrawingPanel.TILE_SIZE,DrawingPanel.TILE_SIZE , null);



        //Calcule de la taille de la barre de vie
        double divider = (this.dp.getJeu().getTour().getHeros().getPDV() / 10) * 100; // 10 max / 10      //10 max / 5
        this.healthBarWidth = (int) (divider * this.maxHealthWidth / 100);


        //affichage de la barre de vie
        g2.drawImage(healthDecorationImage, this.dp.getCamX() + dp.getScreenWidth()/2 - (60), this.dp.getCamY() + 5, 150,42 , null);
        g2.drawImage(healthImage, this.dp.getCamX() + dp.getScreenWidth()/2+20 - (maxHealthWidth/2-12), this.dp.getCamY() + 5, healthBarWidth,42 , null);
    }
    public void changeImage(){
        keyImage = getImage("key_found");
        this.imageChanged = true;
    }
    public BufferedImage getImage(String name){
        BufferedImage image = new BufferedImage(DrawingPanel.TILE_SIZE,DrawingPanel.TILE_SIZE,BufferedImage.TYPE_INT_ARGB);
        try {
            image = ImageIO.read(getClass().getClassLoader().getResource("images/ui/"+name+".png"));
        } catch (IOException e){
            System.out.println("Image not found");
        }
        return image;
    }
}
