package main.Principale;

import main.Cases.Case;
import main.Cases.Mur;
import main.Cases.Sol;
import main.Personnages.Heros;
import main.Personnages.Monstre;
import main.Personnages.Personnage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Niveau {

    //Longueur du labyrinthe
    private static int LONGUEUR;

    //Largeur du labyrinthe
    private static int LARGEUR;

    // path du fichier contenant le labyrinthe
    private String path;

    //Booleen true quand c'est le dernier niveau
    private boolean lastLevel;

    //Tableau de cases représentant le niveau
    private Case[][] niveau;

    //Monstres du niveau
    private Set<Monstre> monstres;

    /**
     * Constructeur du niveau
     * @param path
     *  Chemin du fichier contenant le niveau
     */
    public Niveau(String path) throws FileNotFoundException {
        this.path = path;
        this.lastLevel = false;

        // Chargement du fichier
        this.loadFile();
    }

    /**
     * Méthode autorisant ou non le déplacement d'un personnage
     * @param p
     *  Personnage à déplacer
     * @param axe
     *  Axe de déplacement
     * @param dir
     *  Direction de déplacement
     * @return boolean
     */
    public boolean canMove(Personnage p, char axe, int dir) {
        int targetX = p.getX();
        int targetY = p.getY();

        switch (axe) {
            case 'X':
                targetX += dir;
                break;
            case 'Y':
                targetY += dir;
                break;
        }

        if (!this.niveau[targetY][targetX].getCollision()) {
            return true;
        }

        return false;
    }

    /**
     * Charge le labyrinthe depuis un fichier
     */
    private void loadFile() throws FileNotFoundException {
        // Ouvre le fichier
        File file = new File(this.path);
        Scanner sc = new Scanner(file);

        // Récupération des dimensions du labyrinthe
        String[] dim = (sc.nextLine()).split(" ");
        LONGUEUR = Integer.parseInt(dim[0]);
        LARGEUR = Integer.parseInt(dim[1]);

        // Création du labyrinthe
        this.niveau = new Case[LONGUEUR][LARGEUR];

        // Parcours du fichier
        int index = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            for (int i = 0; i < line.length(); i++) {
                // Récupération de la case
                char c = line.charAt(i);
                // Création de la case
                switch (c) {
                    case 'M':
                        this.niveau[index][i] = new Mur(true, index, i);
                        break;
                    case 'V':
                        this.niveau[index][i] = new Sol(index, i);
                        break;
                }
            }
            index++;
        }
    }

    /**
     * Getter path
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * Getter LONGUEUR
     * @return LONGUEUR
     */
    public int getLongueur() {
        return LONGUEUR;
    }

    /**
     * Getter LARGEUR
     * @return LARGEUR
     */
    public int getLargeur() {
        return LARGEUR;
    }

    /**
     * Getter lastLevel
     * @return lastLevel
     */
    public boolean isLastLevel() {
        return lastLevel;
    }

    /**
     * Getter labyrinthe
     * @return labyrinthe
     */
    public Case[][] getNiveau() {
        return niveau;
    }

    /**
     * Getter Case
     * @param x entier représentant l'axe des X du labyrinthe
     * @param y entier représentant l'axe des Y du labyrinthe
     * @return CASE[][]
     */
    public Case getCase(int x, int y){ return this.niveau[y][x]; }

    /**
     * Print labyrinthe temporaire
     * @param h
     * Heros du niveau
     * @return String
     */
    public void printMap(Heros h){
        for (int i = 0 ; i < this.getLongueur() ; i++){
            for (int j = 0; j < this.getLargeur() ; j++){
                if (h.getX() == j && h.getY() == i){
                    System.out.print("P");
                    continue;
                }
                Case c = this.getCase(j,i);
                System.out.print(c.getClass().getSimpleName().charAt(0));

            }
            System.out.print("\n");
        }
    }

    /**
     * Setter lastLevel
     * @param b
     * Booleen
     */
    public void setLastLevel(boolean b) {
        this.lastLevel = b;
    }
}
