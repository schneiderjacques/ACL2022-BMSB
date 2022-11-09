package main.Principale;

import main.Cases.*;
import main.Engine.GamePainter;
import main.Personnages.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Niveau implements GamePainter {

    //Longueur du labyrinthe
    private int longueur;

    //Largeur du labyrinthe
    private int largeur;

    // path du fichier contenant le labyrinthe
    private final InputStream path;

    //Booleen true quand c'est le dernier niveau
    private boolean lastLevel;

    //Tableau de cases représentant le niveau
    private Case[][] niveau;

    //Monstres du niveau
    private final Set<Monstre> monstres;

    private final HashMap<Monstre, ScheduledExecutorService> monstresThreads;

    //Tour du jeu
    private final Tour tour;

    //Clé trouvée dans le niveau
    private boolean keyFound = false;

    /**
     * Constructeur du niveau
     * @param t : Tour
     * @param path : Chemin du fichier contenant le niveau
     */
    public Niveau(InputStream path, Tour t) throws FileNotFoundException {
        this.path = path;
        this.lastLevel = false;
        this.tour = t;
        this.monstres = new HashSet<>();
        this.monstresThreads = new HashMap<>();

        // Chargement du fichier
        this.loadFile();

        int delay = 20;
        // Mise en place des threads pour chaque monstre
        for (Monstre m : this.monstres) {
            Runnable moveOrAttackMonster = m::moveOrAttack;
            long period = delay * 100L;
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            this.monstresThreads.put(m, executor);
            executor.scheduleAtFixedRate(moveOrAttackMonster, period, 2000, TimeUnit.MILLISECONDS);
            delay += 1;
        }
    }

    /**
     * Méthode autorisant ou non le déplacement d'un personnage
     * @param p   Personnage à déplacer
     * @param axe Axe de déplacement
     * @param dir Direction de déplacement
     * @return boolean
     */
    public boolean canMove(Personnage p, char axe, int dir) {
        int targetX = p.getX();
        int targetY = p.getY();

        switch (axe) {
            case 'X' -> targetX += dir;
            case 'Y' -> targetY += dir;
        }

        if (!this.niveau[targetY][targetX].getCollision()) {
            for (Monstre m : this.monstres) {
                if (m.getX() == targetX && m.getY() == targetY) {
                    return false;
                }
            }
            return this.tour.getHeros().getX() != targetX || this.tour.getHeros().getY() != targetY;
        }
        if (!p.isCollision()) {
            return targetX != 0 && targetX != this.longueur - 1 && targetY != 0 && targetY != this.largeur - 1;
        }
        return false;
    }

    /**
     * Méthode qui retourne le monstre en face du personnage sinon null
     * @param p : Personnage
     * @return monstre
     */
    public Monstre getMonsterInFront(Personnage p) {
        int targetX = p.getX();
        int targetY = p.getY();

        switch (p.getLM()) {
            case "h" -> targetY -= 1;
            case "b" -> targetY += 1;
            case "g" -> targetX -= 1;
            case "d" -> targetX += 1;
        }

        for (Monstre m : this.monstres) {
            if (m.getX() == targetX && m.getY() == targetY) {
                return m;
            }
        }
        return null;
    }


    /**
     * Charge le labyrinthe depuis un fichier
     */
    private void loadFile() throws FileNotFoundException {
        // Ouvre le fichier
        Scanner sc = new Scanner(this.path);

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
                switch (c) {
                    //M : Mur
                    case 'M' -> this.niveau[index][i] = new Mur(true, index, i);

                    //V : Vide (sol)
                    case 'V' -> this.niveau[index][i] = new Sol(index, i);

                    //S : Sortie (Exit)
                    case 'S' -> this.niveau[index][i] = new Exit(index, i);

                    //K : Clé
                    case 'K' -> this.niveau[index][i] = new Key(index, i);

                    //T : Trappe (piège)
                    case 'T' -> this.niveau[index][i] = new Trappe(index, i);

                    //H : Heal (vie)
                    case 'H' -> this.niveau[index][i] = new Vie(index, i);

                    //D : Dégat
                    case 'D' -> this.niveau[index][i] = new Degat(index, i);

                    //B : Boo (monstre(fantôme))
                    case 'B' -> {
                        this.monstres.add(new Boo(i, index, this));
                        this.niveau[index][i] = new Sol(index, i);
                    }
                    //G : Goomba (monstre)
                    case 'G' -> {
                        this.monstres.add(new Goomba(i, index, this));
                        this.niveau[index][i] = new Sol(index, i);
                    }
                }
            }
            index++;
        }
    }

    /**
     * Getter path
     * @return path
     */
    public InputStream getPath() {
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
    public Case getCase(int x, int y) {
        return this.niveau[y][x];
    }

    /**
     * Print labyrinthe temporaire
     * @param h Heros du niveau
     */
    public void printMap(Heros h) {
        System.out.println(this.getLargeur());
        System.out.println(this.getLongueur());
        for (int i = 0; i < this.getLongueur(); i++) {
            for (int j = 0; j < this.getLargeur(); j++) {
                if (h.getX() == j && h.getY() == i) {
                    System.out.print("P");
                    continue;
                } else {
                    boolean isMonstre = false;
                    for (Monstre m : this.monstres) {
                        if (m.getX() == j && m.getY() == i) {
                            if (m instanceof Boo) {
                                System.out.print("B");
                            } else {
                                System.out.print("G");
                            }
                            isMonstre = true;
                        }
                    }
                    if (isMonstre) continue;
                }
                Case c = this.getCase(j, i);
                System.out.print(c.getType().charAt(0));

            }
            System.out.print("\n");
        }
        System.out.println("===============");
    }

    /**
     * Setter lastLevel
     * @param b boolean
     */
    public void setLastLevel(boolean b) {
        this.lastLevel = b;
    }

    /**
     * Getter tour
     * @return tour
     */
    public Tour getTour() {
        return this.tour;
    }

    /**
     * Dessiner le niveau
     * @param image : image sur laquelle dessiner
     */
    @Override
    public void draw(BufferedImage image) {
        for (int i = 0; i < this.getLongueur(); i++) {
            for (int j = 0; j < this.getLargeur(); j++) {
                this.niveau[i][j].draw(image);
            }
        }
        for (Monstre m : this.monstres) {
            m.draw(image);
        }
        this.getTour().getHeros().draw(image);
    }



    /**
     * Getter de la clé du personnage
     * @return est ce que la clé est trouvée en boolean
     */
    public boolean isKeyFound() {
        return keyFound;
    }

    /**
     * setCanMove
     */
    public void setKeyFound(boolean b) {
        this.keyFound = b;
    }

    /**
    * remplace une case par une case Sol
    * @param x colonne
    * @param y ligne
    */
    public void setCaseToSol(int x, int y){
        this.niveau[x][y] = new Sol(x, y);
    }

    /**
     * Methode qui supprime un monstre du niveau
     * @param m : monstre à supprimer
     */
    public void removeMonstre(Monstre m) {
        this.monstres.remove(m);
        this.monstresThreads.get(m).shutdown();
        this.monstresThreads.remove(m);
    }

    public void resetMonstre(Monstre monstre) {
        Runnable moveOrAttackMonster = monstre::moveOrAttack;
        this.monstresThreads.get(monstre).shutdown();
        this.monstresThreads.remove(monstre);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        this.monstresThreads.put(monstre, executor);
        executor.scheduleAtFixedRate(moveOrAttackMonster, 1500, 2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Methode qui supprime tous les monstres du niveau
     */
    public void deleteNiveau(){
        this.monstresThreads.forEach((monstre, executor) -> executor.shutdown());
        this.monstresThreads.clear();
        this.monstres.clear();
    }
}
