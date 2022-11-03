package main.Principale;

import main.Personnages.Heros;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
 * Class représentant le labynthe du jeu
 * @author Silvio Brancati
 */
public class Tour {

    //Tableau de niveaux représentant la tour
    private ArrayList<Niveau> niveaux;

    //Personnage du jeu en cours
    private Heros heros;

    //Niveau courant
    private int currentLevel;

    /**
     * Constructeur de la tour
     */
    public Tour() {
        // Création du tableau de niveaux
        this.niveaux = new ArrayList<Niveau>();

        // Création du héros
        this.heros = new Heros();

        // Chargement des niveaux
        this.currentLevel = 1;
    }

    /**
     * Passer au niveau suivant de la tour
     */
    public void nextLevel() {
        if (this.currentLevel < this.niveaux.size()) {
            this.currentLevel++;
        }
    }

    /**
     * Méthode autorisant ou non le déplacement du héros
     * @param axe
     * Axe de déplacement
     * @param dir
     * Direction de déplacement
     */
    public void moveHeros(char axe, int dir) {
        if (this.niveaux.get(this.currentLevel-1).canMove(this.heros, axe, dir)) {
            this.heros.move(axe, dir);
        }
        //this.niveaux.get(currentLevel-1).printMap(this.getHeros());
    }

    /**
     * Charge des niveaux par défaut
     */
    public void loadNiveaux(ArrayList<Niveau> levels) {
        // Chargement des niveaux
        this.niveaux.addAll(levels);
        this.niveaux.get(niveaux.size()-1).setLastLevel(true);
    }

    /**
     * Getter du niveau courant
     * @return Niveau
     */
    public Niveau getCurrentLevel() { return this.niveaux.get(this.currentLevel-1); }

    /*
     * Getter du Heros
     * @return Heros
     */
    public Heros getHeros() {
        return this.heros;
    }
}