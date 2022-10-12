package main;

import main.Controller.ControllerMouvement;
import main.Principale.Jeu;

import java.awt.*;
import java.io.FileNotFoundException;

/*
* Class principale du projet
* @author Jacques Schneider
 */
public class Main {

    /*
    * Méthode principale du projet
    * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {
        Jeu jeu = new Jeu();
        ControllerMouvement cm = new ControllerMouvement(jeu); //Ajout du controleur des mouvements
        //Interface de test
        System.out.println("Labyrinthe n°1 chargé taille : ("+jeu.getTour().getCurrentLevel().getLargeur() + "-"+jeu.getTour().getCurrentLevel().getLongueur() + ")");
        jeu.getTour().getCurrentLevel().printMap(jeu.getTour().getHeros());
        System.out.println("Vous vous trouvez en " + jeu.getTour().getHeros().getX() + ";" + jeu.getTour().getHeros().getY());
        Frame f = new Frame("War y haut");
        f.setVisible(true);
        f.addKeyListener(cm);
    }
}
