package main;

import main.Controller.ControllerMouvement;
import main.Principale.Jeu;
import main.Principale.Niveau;
import main.engine.GameEngineGraphical;
import main.model.JeuPainter;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
* Class principale du projet
* @author Jacques Schneider
 */
public class Main {

    /*
    * Méthode principale du projet
    * @param args
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Jeu jeu = new Jeu();

        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<Niveau>();
        for (int i = 2; i <= 2; i++) {
            list.add(new Niveau("src/resources/level_" + i + ".txt", jeu.getTour()));
        }

        // Chargement des niveaux dans la tour
        jeu.getTour().loadNiveaux(list);

        /*ControllerMouvement cm = new ControllerMouvement(jeu); //Ajout du controleur des mouvements
        //Interface de test
        System.out.println("Labyrinthe n°1 chargé taille : ("+jeu.getTour().getCurrentLevel().getLargeur() + "-"+jeu.getTour().getCurrentLevel().getLongueur() + ")");
        jeu.getTour().getCurrentLevel().printMap(jeu.getTour().getHeros());
        System.out.println("Vous vous trouvez en " + jeu.getTour().getHeros().getX() + ";" + jeu.getTour().getHeros().getY());
        Frame f = new Frame("War y haut");
        f.setVisible(true);
        f.addKeyListener(cm);*/

        // creation du jeu particulier et de son afficheur
        Jeu game = new Jeu();
        JeuPainter painter = new JeuPainter(jeu);
        ControllerMouvement controller = new ControllerMouvement(jeu);

        // classe qui lance le moteur de jeu generique
        GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller);
        engine.run();


    }
}
