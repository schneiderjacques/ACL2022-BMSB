package src.main.Principale;

import src.main.Cases.Case;
import src.main.Cases.Mur;
import src.main.Cases.Sol;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Class représentant le labynthe du jeu
 * @author Silvio Brancati
 */
public class Labyrinthe {

    //Longueur du labyrinthe
    private static int LONGUEUR;

    //Largeur du labyrinthe
    private static int LARGEUR;

    //Tableau de cases représentant le labyrinthe
    private Case[][] labyrinthe;

    // path du fichier contenant le labyrinthe
    private String path;


    /**
     * Constructeur du labyrinthe
     * @param p
     *  Chemin du fichier contenant le labyrinthe
     */
    public Labyrinthe(String p) throws FileNotFoundException {
        // Initialisation des variables
        this.path = p;

        // Chargement du fichier
        this.loadFile();
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
        this.labyrinthe = new Case[LONGUEUR][LARGEUR];

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
                        this.labyrinthe[index][i] = new Mur(true);
                        break;
                    case 'V':
                        this.labyrinthe[index][i] = new Sol();
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
}