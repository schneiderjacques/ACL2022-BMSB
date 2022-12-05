package main.java.Principale;

import main.java.Engine.Cmd;
import main.java.Engine.Game;
import main.java.Engine.Sound;
import main.java.Main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
* Class représentant le jeu en lui même
* @author Jacques Schneider
 */


public class Jeu implements Game {

    /**
     * Etat du jeu, 0 = Menu démarrer, 1 = Affichage du jeu
     */
    private int gameState;

    //Labyritnhe du jeu en cours
    private Tour tour;

    private boolean isFinished;

    //Tableau des scores
    private ArrayList<String> scores;

    public static Sound sound = new Sound();

    //Nom du joueur
    private String namePlayer;

    /**
     * Constructeur du jeu
     */
    public Jeu() throws FileNotFoundException {
        // Mise en place de l'état du Jeu
        this.gameState = 0;

        this.isFinished = false;
        //Création du labyrinthe
        this.tour = new Tour();

        //Initialisation des scores
        this.scores = new ArrayList<>();

        loadScores();
    }

    /**
     * Getter de la tour
     * @return Tour
     */
    public Tour getTour() { return this.tour; }

    @Override
    public void evolve(Cmd commande) {
        //Evolution du jeu
    }

    /**
     * Mute le jeu
     */
    public void mute() {
    	sound.mute();
    }

    /**
     * Méthode permettant de savoir si le jeu est fini
     * @return boolean
     */
    @Override
    public boolean isFinished() {return isFinished;}

    /**
     * Méthode permettant de savoir si le jeu est gagné
     * @return boolean
     */
    public boolean isWon() {
        return tour.isWon();
    }

    /**
     * Méthode permettant de savoir si le jeu est perdu
     * @return boolean
     */
    public boolean isLost() {
        return tour.isLost();
    }

    /**
     * Démarre le jeu après le menu
     */
    public void demarreJeu() {
        this.gameState = 1;
        tour.demarreTour();
    }

    /**
     * Getter de GameState
     * @return gameState int 0 = menu, 1 = jeu
     * */
    public int getGameState(){
        return this.gameState;
    }

    /**
     * Setter de GameState
     * @param gameState entier représentant l'état du jeu
     * Nouvelle valeur de gameState
     * */
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public void restartJeu() {
        tour = new Tour();
        this.setGameState(1);
        tour.setLevelChanged(true);
        tour.demarreTour();
    }

    public void pauseJeu() {
        this.tour.getCurrentLevel().pauseMonstres();
        this.tour.getCurrentLevel().pauseArmes();
        this.setGameState(4);
    }

    public void resumeLevel() {
        this.tour.resumeLevel();
        this.setGameState(1);
    }

    public void loadScores() {
        Scanner sc = new Scanner(Objects.requireNonNull(Main.class.getResourceAsStream("/Scores.txt")));
        while (sc.hasNextLine()) {
            scores.add(sc.nextLine());
        }
        scores.sort((o1, o2) -> {
            String[] split1 = o1.split(" ");
            String[] split2 = o2.split(" ");
            return Integer.parseInt(split2[1]) - Integer.parseInt(split1[1]);
        });
    }

    public void addScore() throws FileNotFoundException {
        int score = tour.getLevelNumber();
        boolean added = false;
        for(int i = 0; i < scores.size(); i++) {
            if (scores.get(i).split(" ")[0].equals(namePlayer)) {
                if (Integer.parseInt(scores.get(i).split(" ")[1]) < score) {
                    scores.set(scores.indexOf(scores.get(i)), namePlayer + " " + score);
                }
                added = true;
            }
        };
        if (!added) {
            scores.add(namePlayer + " " + score);
        }
        //System.out.println(scores);
        scores.sort((o1, o2) -> {
            String[] split1 = o1.split(" ");
            String[] split2 = o2.split(" ");
            return Integer.parseInt(split2[1]) - Integer.parseInt(split1[1]);
        });

        PrintWriter writer = new PrintWriter("src/main/resources/Scores.txt");
        writer.print("");
        for (String s : scores) {
            writer.println(s);
        }
        writer.close();
    }

    public void setNamePlayer(String name) {
        this.namePlayer = name;
    }

    public ArrayList<String> getScores() {
        return scores;
    }
}
