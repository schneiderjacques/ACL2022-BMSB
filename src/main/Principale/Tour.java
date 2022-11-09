package main.Principale;

import main.Personnages.Heros;
import main.Personnages.Monstre;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class représentant le labynthe du jeu
 * @author Silvio Brancati
 */
public class Tour {

    //Tableau de niveaux représentant la tour
    private final ArrayList<Niveau> niveaux;

    //Fini ou non
    private boolean fini;

    //Niveau a changé
    private boolean levelChanged;

    //Personnage du jeu en cours
    private final Heros heros;

    //Niveau courant
    private int currentLevel;

    /**
     * Constructeur de la tour
     */
    public Tour() {
        // Création du tableau de niveaux
        this.niveaux = new ArrayList<>();

        // Création du héros
        this.heros = new Heros();

        // Chargement des niveaux
        this.currentLevel = 1;

        this.fini = false;
    }

    /**
     * Passer au niveau suivant de la tour
     */
    public void nextLevel() {
        if (this.currentLevel < this.niveaux.size()) {
            this.currentLevel++;
            this.levelChanged = true;
        } else {
            this.fini = true;
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
        switch (axe) {
            case 'X':
                if (dir > 0){
                    this.heros.setLm("d");
                } else {
                    this.heros.setLm("g");
                }
                break;
            case 'Y':
                if (dir > 0){
                    this.heros.setLm("b");
                } else {
                    this.heros.setLm("h");
                }
                break;
        }
        if (this.niveaux.get(this.currentLevel-1).canMove(this.heros, axe, dir) && this.heros.canMove()) {
            this.heros.move(axe, dir);
            this.triggerCaseEvent();
        }
    }

    /**
     * Méthode qui fait attaquer le héros si un monstre est en face de lui
     */
    public void heroAttaque(){
        Monstre m = this.niveaux.get(this.currentLevel-1).getMonsterInFront(this.heros);
        if (m != null) {
            this.heros.attaque(m);
        }
    }

    /**
     * On déclange l'effet de la case sur laquelle le joueur vient de se déplacer
     */
    public void triggerCaseEvent(){
        this.getCurrentLevel().getCase(this.heros.getX(), this.heros.getY()).eventCollider(this);
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

    /**
     * Getter du Heros
     * @return Heros
     */
    public Heros getHeros() {
        return this.heros;
    }

    /**
     * Setter de fini
     * @param fini
     * Fini ou non
     */
    public void setFini(boolean fini) {
        this.fini = fini;
    }

    /**
     * Retourne si la tour est gagnée ou non
     * @return boolean
     */
    public boolean isWon() {
        return this.heros.estVivant() && this.fini;
    }

    /**
     * Retourne si la tour est perdue ou non
     * @return boolean
     */
    public boolean isLost() {
        return !this.heros.estVivant();
    }

    /**
     * Getter de levelChanged
     * @return levelChanged
     */
    public boolean levelChanged() {
        return this.levelChanged;
    }

    /**
     * Setter de levelChanged
     * @param levelChanged
     * Nouvelle valeur de levelChanged
     */
    public void setLevelChanged(boolean levelChanged) {
        this.levelChanged = levelChanged;
    }
}
