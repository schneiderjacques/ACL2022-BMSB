package main.java.Principale;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Tools {
    public static BufferedImage getImageByName(String imgName){
        try {
            return ImageIO.read(Tools.class.getResourceAsStream(imgName + ".png"));
        } catch (Exception e) {
            System.out.println("Echec au chargement de l'image : " + imgName);
            e.printStackTrace();
        }
        return null;
    }
}
