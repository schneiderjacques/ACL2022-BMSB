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
    public Tour() throws FileNotFoundException {
        // Création du tableau de niveaux
        this.niveaux = new ArrayList<Niveau>();

        // Création du héros
        this.heros = new Heros();

        // Chargement des niveaux
        this.currentLevel = 1;
        this.loadNiveaux();
    }

    /**
     * Méthode permettant de charger les niveaux
     * @param list
     *  Niveau à charger
     * @throws FileNotFoundException
     */
    public Tour(ArrayList<Niveau> list) throws FileNotFoundException {
        // Création du tableau de niveaux
        this.niveaux = new ArrayList<>();

        // Création du héros
        this.heros = new Heros();

        // Chargement des niveaux
        this.currentLevel = 1;
        this.niveaux.addAll(list);
        this.niveaux.get(this.niveaux.size() - 1).setLastLevel(true);
    }

    /**
     * Passer au niveau suivant de la tour
     */
    public void nextLevel() {
        if (this.currentLevel < this.niveaux.size()) {
            this.currentLevel++;
        }
    }

    public void moveHeros(char axe, int dir) {
        if (this.niveaux.get(this.currentLevel-1).canMove(this.heros, axe, dir)) {
            this.heros.move(axe, dir);
        }
        this.niveaux.get(currentLevel-1).printMap(this.getHeros());
    }

    /**
     * Charge des niveaux par défaut
     */
    private void loadNiveaux() throws FileNotFoundException {
        // Chargement des niveaux
        /**for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.niveaux.add(new Niveau("src/main/resources/niveau_" + i + "_" + j + ".txt"));
            }
        }**/
        this.niveaux.add(new Niveau("resources/level_1.txt"));
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