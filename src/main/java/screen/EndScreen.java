package main.java.screen;

import main.java.Engine.DrawingPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class EndScreen {

        /**
         * Image de fond du menu démarrer
         */
        private BufferedImage backgroundImage;
        /**
         * DrawingPanel du jeu
         */
        private DrawingPanel dp;

        /**
        * Liste des scores du jeu
        */
        private ArrayList<String> scores;

        /**
        * Score courant du joueur
        */
        private int scoreCourant;


        /**
        * Titre du jeu
         */
        private String title = "";
        /**
         * Nom du bouton restart
         */
        private final String restart = "Restart";
        /**
         * Nom du bouton quitter
         */
        private final String exit = "Quitter";

        private final Color[] colors = {Color.ORANGE, Color.getHSBColor(244, 244, 244), Color.getHSBColor(212, 129, 39), Color.getHSBColor(175, 206, 208)};

        /**
         *
         */
        private int commandNum = 0;

        /**
         * Constructeur de la classe MenuScreen
         *
         * @param dp DrawingPanel du jeu
         */
        public EndScreen(DrawingPanel dp) {
            this.dp = dp;
            this.scoreCourant = 0;
            this.scores = new ArrayList<>();
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
            g2.drawString(title, dp.getScreenWidth() / 2 - (title.length() * 17), dp.getScreenHeight() / 2 + 130);

            if (commandNum == 0) {
                g2.drawString(">", dp.getScreenWidth() / 2 - 150, dp.getScreenHeight() / 2 + 205);
            } else if (commandNum == 1) {
                g2.drawString(">", dp.getScreenWidth() / 2 - 150, dp.getScreenHeight() / 2 + 255);
            }


            //Nouvelle font size
            g2.setFont(new Font("Minecraft", Font.BOLD, 30));
            //Dessine le bouton restart
            g2.drawString(restart, dp.getScreenWidth() / 2 - (restart.length() * 13), dp.getScreenHeight() / 2 + 200);
            //Dessine le bouton quitter
            g2.drawString(exit, dp.getScreenWidth() / 2 - (exit.length() * 13), dp.getScreenHeight() / 2 + 250);


            //Dessine le score courant
            g2.setFont(new Font("Minecraft", Font.BOLD, 28));
            g2.drawString("Votre score", dp.getScreenWidth() / 2 -450, dp.getScreenHeight() / 2 + 130);
            g2.drawString("est de :", dp.getScreenWidth() / 2 -415, dp.getScreenHeight() / 2 + 160);
            g2.setFont(new Font("Minecraft", Font.BOLD, 28));
            g2.setColor(Color.green);
            g2.drawString(String.valueOf(scoreCourant), dp.getScreenWidth() / 2 -375, dp.getScreenHeight() / 2 + 190);

            //Dessine les scores
            g2.setPaint(grandient);
            g2.setFont(new Font("Minecraft", Font.BOLD, 35));
            g2.drawString("Scores", dp.getScreenWidth() / 2 + 200, dp.getScreenHeight() / 2 + 130);
            g2.setFont(new Font("Minecraft", Font.BOLD, 25));

            //Affichage des 4 premiers scores
            for (int i = 0; i < 4; i++) {
                if (i < scores.size()) {
                    String num = (i + 1) + " - " + scores.get(i);
                    g2.setColor(colors[i]);
                    g2.drawString(num, dp.getScreenWidth() / 2 + 230, dp.getScreenHeight() / 2 + 170 + (i * 30));
                }
            }




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


    public void setScores(ArrayList<String> scores) {
        this.scores = scores;
    }


    public void setScoreCourant(int levelNumber) {
        this.scoreCourant = levelNumber;
    }
}
