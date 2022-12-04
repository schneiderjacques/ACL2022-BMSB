package main.java.Principale;

import main.java.Cases.Case;
import main.java.Personnages.Heros;
import main.java.Personnages.Monstre;
import main.java.Main;

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

        // Création des niveaux
        ArrayList<Niveau> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            list.add(new Niveau(Main.class.getResourceAsStream("/level_"+ i +".txt"), this));
        }

        // Chargement des niveaux dans la tour
        this.loadNiveaux(list);

        // Création du héros
        this.heros = new Heros();

        // Chargement des niveaux
        this.currentLevel = 1;

        this.fini = false;
    }

    /**
     * Méthode permettant de démarrer le premier niveau
     */
    public void demarreTour(){
        this.getCurrentLevel().demarreNiveau();
    }


    /**
     * Passer au niveau suivant de la tour
     */
    public void nextLevel() {
        if (this.currentLevel < this.niveaux.size()) {
            this.niveaux.get(this.currentLevel-1).deleteNiveau();
            this.currentLevel++;
            this.levelChanged = true;
            this.heros.reset();
            this.getCurrentLevel().demarreNiveau();
        } else {
            this.fini = true;
            this.niveaux.get(this.currentLevel-1).deleteNiveau();
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
        if (m != null && this.heros.canAttack()) {
            this.heros.attaque(m);
            if (!m.estVivant()){
                this.niveaux.get(this.currentLevel-1).removeMonstre(m);
            }
        }
    }

    /**
     * On déclange l'effet de la case sur laquelle le joueur vient de se déplacer
     */
    public void triggerCaseEvent(){
        Case c = this.getCurrentLevel().getCaseObject(this.heros.getX(), this.heros.getY());
        if (c != null) {
            c.eventCollider(this);
        }
    }

    /**
     * Charge des niveaux par défaut
     */
    public void loadNiveaux(ArrayList<Niveau> levels) {
        this.niveaux.clear();
        this.currentLevel = 1;
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
     * Getter du niveau courant en tant qu'entier
     * @return int
     */
    public int getLevelNumber(){
        return this.currentLevel;
    }
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

    /**
     * Methode qui clear le niveau courant
     */
    public void clearLevel(){this.niveaux.get(this.currentLevel-1).deleteNiveau();}

    /**
     * Methode qui stop les monstres du niveau courant
     */
    public void pauseLevel(){
        this.niveaux.get(this.currentLevel-1).pauseMonstres();
    }

    public void resumeLevel(){
        this.niveaux.get(this.currentLevel-1).demarreNiveau();
    }
}
