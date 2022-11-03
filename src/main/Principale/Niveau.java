package main.Principale;

import main.Cases.*;
import main.Personnages.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Niveau {

    //Longueur du labyrinthe
    private int longueur;

    //Largeur du labyrinthe
    private int largeur;

    // path du fichier contenant le labyrinthe
    private String path;

    //Booleen true quand c'est le dernier niveau
    private boolean lastLevel;

    //Tableau de cases représentant le niveau
    private Case[][] niveau;

    //Monstres du niveau
    private Set<Monstre> monstres;

    //Tour du jeu
    private Tour tour;

    /**
     * Constructeur du niveau
     * @param path
     *  Chemin du fichier contenant le niveau
     */
    public Niveau(String path, Tour t) throws FileNotFoundException {
        this.path = path;
        this.lastLevel = false;
        this.tour = t;
        this.monstres = new HashSet<>();

        // Chargement du fichier
        this.loadFile();


        // Mise en place des threads pour chaque monstre
        for(Monstre m : this.monstres){
            Runnable moveOrAttackMonster = () -> {
                m.moveOrAttack();
                this.printMap(this.tour.getHeros());
            };
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(moveOrAttackMonster, 0, 5, java.util.concurrent.TimeUnit.SECONDS);
        }
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
        if (p.isCollision()){
            if (!this.niveau[targetY][targetX].getCollision()) {
                for(Monstre m : this.monstres){
                    if(m.getX() == targetX && m.getY() == targetY){
                        return false;
                    }
                }
                if (this.tour.getHeros().getX() == targetX && this.tour.getHeros().getY() == targetY) {
                    return false;
                }
                return true;
            }
            return false;
        }else{
            if (!this.niveau[targetY][targetX].getCollision()) {
                return true;
            }else{
                //Si la case est au bord du labyrinthe
                if (targetX == 0 || targetX == this.longueur - 1 || targetY == 0 || targetY == this.largeur - 1) {
                    return false;
                }
                return true;
            }
        }
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
        longueur = Integer.parseInt(dim[0]);
        largeur = Integer.parseInt(dim[1]);

        // Création du labyrinthe
        this.niveau = new Case[longueur][largeur];

        // Parcours du fichier
        int index = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            for (int i = 0; i < line.length(); i++) {
                // Récupération de la case
                char c = line.charAt(i);
                // Création de la case
                //M : Mur
                //V : Vide (sol)
                //S : Sortie
                //K : Clé
                //H : Heal (vie)
                //D : Dégat
                switch (c) {
                    case 'M':
                        this.niveau[index][i] = new Mur(true, index, i);
                        break;
                    case 'V':
                        this.niveau[index][i] = new Sol(index, i);
                        break;
                    case 'S':
                        this.niveau[index][i] = new Exit(index, i);
                        break;
                    case 'K':
                        this.niveau[index][i] = new Key(index, i);
                        break;
                    case 'T':
                        this.niveau[index][i] = new Trappe(index, i);
                        break;
                    case 'H':
                        this.niveau[index][i] = new Vie(index, i);
                        break;
                    case 'D':
                        this.niveau[index][i] = new Degat(index, i);
                        break;
                    case 'B':
                        this.monstres.add(new Boo(index, i, this));
                        this.niveau[index][i] = new Sol(index, i);
                        break;
                    case 'G':
                        this.monstres.add(new Goomba(index, i, this));
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
        return longueur;
    }

    /**
     * Getter LARGEUR
     * @return LARGEUR
     */
    public int getLargeur() {
        return largeur;
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
        System.out.println(this.getLargeur());
        System.out.println(this.getLongueur());
        for (int i = 0 ; i < this.getLongueur() ; i++){
            for (int j = 0; j < this.getLargeur() ; j++){
                if (h.getX() == j && h.getY() == i){
                    System.out.print("P");
                    continue;
                }else{
                    boolean isMonstre = false;
                    for(Monstre m : this.monstres){
                        if(m.getX() == j && m.getY() == i){
                            if (m instanceof Boo){
                                System.out.print("B");
                            }else {
                                System.out.print("G");
                            }
                            isMonstre = true;
                        }
                    }
                    if (isMonstre)continue;
                }
                Case c = this.getCase(j,i);
                System.out.print(c.getType().charAt(0));

            }
            System.out.print("\n");
        }
        System.out.println("===============");
    }

    /**
     * Setter lastLevel
     * @param b
     * Booleen
     */
    public void setLastLevel(boolean b) {
        this.lastLevel = b;
    }

    public Tour getTour(){return this.tour;}
}
