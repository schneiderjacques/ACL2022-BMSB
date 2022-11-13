package main;

import main.Controller.ControllerMouvement;
import main.Principale.Jeu;
import main.Principale.Niveau;
import main.Engine.GameEngineGraphical;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
* Class principale du projet
* @author Jacques Schneider
 */
public class Main {

    /**
    * Méthode principale du projet
    * @param args : arguments de la ligne de commande
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Jeu jeu = new Jeu();

        // creation du jeu particulier et de son afficheur
        ControllerMouvement controller = new ControllerMouvement(jeu);

        // classe qui lance le moteur de jeu generique
        GameEngineGraphical engine = new GameEngineGraphical(jeu, controller);
        engine.run();


    }
}
