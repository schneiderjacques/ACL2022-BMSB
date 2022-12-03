package main.java.Principale;

import main.java.Cases.*;
import main.java.Engine.GamePainter;
import main.java.Personnages.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.InputStream;
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

    // Case de sortie du niveau
    private Exit exitCase;

    //Tableau de cases représentant les objets du niveau
    private Case[][] objetNiveau;

    //Monstres du niveau
    private final Set<Monstre> monstres;

    private final HashMap<Monstre, ScheduledExecutorService> monstresThreads;

    //Tour du jeu
    private final Tour tour;

    //Clé trouvée dans le niveau
    private boolean keyFound = false;


    /**
     * Constructeur du niveau
     *
     * @param t    : Tour
     * @param path : Chemin du fichier contenant le niveau
     */
    public Niveau(InputStream path, Tour t) {
        this.path = path;
        this.lastLevel = false;
        this.tour = t;
        this.monstres = new HashSet<>();
        this.monstresThreads = new HashMap<>();
        // Chargement du fichier
        try {
            this.loadFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Les fichiers du niveau n'ont pas été trouvés");
        }
    }

    /**
     * Méthode permettant de démarrer l'IA des monstres
     */
    public void demarreNiveau() {
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
     *
     * @param p   Personnage à déplacer
     * @param axe Axe de déplacement
     * @param dir Direction de déplacement
     * @return boolean
     */
    public boolean canMove(Personnage p, char axe, int dir) {
        int targetX = p.getX();
        int targetY = p.getY();

        // on change l'orientation du personnage
        if (axe == 'X') {
            if (dir > 0) {
                p.setLastMove("d");
            } else {
                p.setLastMove("g");
            }
        } else {
            if (dir > 0) {
                p.setLastMove("b");
            } else {
                p.setLastMove("h");
            }
        }

        switch (axe) {
            case 'X' -> targetX += dir;
            case 'Y' -> targetY += dir;
        }
        if(this.objetNiveau[targetY][targetX] != null && this.objetNiveau[targetY][targetX].getCollision()){
            return false;
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
            return targetX != 0 && targetY != this.longueur - 1 && targetY != 0 && targetX != this.largeur - 1;
        }
        return false;
    }

    /**
     * Méthode qui retourne le monstre en face du personnage sinon null
     *
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

        String[] collisionMur =
                {"27","15","31","55","59","40","1","17","4","14","18","68","13","16","60","3","10","11","12","23","24","25", "36", "37", "38", "0", "26", "28"};

        // Création du labyrinthe
        this.niveau = new Case[longueur][largeur];

        // Création des objets du labyrinthe
        this.objetNiveau = new Case[longueur][largeur];

        // Parcours du fichier
        int index = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] cases = line.split("\t");
            //loop sur les cases
            for (int i = 0; i < cases.length; i++) {
                Case c = null;
                if (this.isNumeric(cases[i])) {
                    if(Arrays.stream(collisionMur).anyMatch(cases[i]::equals)) {
                        c = new Mur(true, index, i); //C'est un mur
                    } else {
                        c = new Sol(index, i); //C'est un sol
                    }

                    String imgName = String.format("%03d", Integer.parseInt(cases[i]));
                    c.setImage(Tools.getImageByName("/images/game/tiles/tile" + imgName),0);

                } else {
                    //C'est une case spéciale
                    char l = cases[i].charAt(0);
                    c = new Sol(index, i);
                    c.setImage(Tools.getImageByName("/images/game/tiles/tile" + "092"),0);
                    switch (l){
                        case 'E' -> {
                            this.objetNiveau[index][i] = new Exit(index, i);
                            this.exitCase = (Exit) this.objetNiveau[index][i];
                        }
                        case 'K' -> {
                            this.objetNiveau[index][i] = new Key(index, i);
                        }
                        case 'H' -> {
                            this.objetNiveau[index][i] = new Vie(index, i);
                        }
                        case 'D' -> {
                            this.objetNiveau[index][i] = new Degat(index, i);
                        }
                        case 'T' -> {
                            this.objetNiveau[index][i] = new Trappe(index, i);
                        }
                        case 'W' -> {
                            this.objetNiveau[index][i] = new Passage(index, i);
                            c.setImage(Tools.getImageByName("/images/game/tiles/tile" + "055"),0);
                        }
                        case 'C' -> {
                            this.objetNiveau[index][i] = new DalleDesacPassage(index, i);
                        }
                        case 'G' -> {
                            this.monstres.add(new Goomba(i, index, this));
                        }
                        case 'X' -> {
                            this.objetNiveau[index][i] = new Dalle(index, i);
                        }
                        case 'B' -> {
                            this.monstres.add(new Boo(i, index, this));
                        }
                    }


                }

                this.niveau[index][i] = c;

            }
            /*for (int i = 0; i < line.length(); i++) {

                // Récupération de la case
                char c = line.charAt(i);

                // Création de la case
                switch (c) {
                    //0 : Vide (sol)
                    case '0' -> this.niveau[index][i] = new Sol(index, i);

                    //1 : Mur
                    case '1' -> this.niveau[index][i] = new Mur(true, index, i);

                    //2 : Dégat
                    case '2' -> this.niveau[index][i] = new Degat(index, i);

                    //3 : Heal (vie)
                    case '3' -> this.niveau[index][i] = new Vie(index, i);

                    //4 : Clé
                    case '4' -> this.niveau[index][i] = new Key(index, i);

                    //5 : Sortie (Exit)
                    case '5' -> this.niveau[index][i] = new Exit(index, i);

                    //6 : Trappe (piège)
                    case '6' -> this.niveau[index][i] = new Trappe(index, i);

                    //7 : Boo (monstre(fantôme))
                    case '7' -> {
                        this.monstres.add(new Boo(i, index, this));
                        this.niveau[index][i] = new Sol(index, i);
                    }
                    //8 : Goomba (monstre)
                    case '8' -> {
                        this.monstres.add(new Goomba(i, index, this));
                        this.niveau[index][i] = new Sol(index, i);
                    }

                    //9 : Passage
                    case '9' -> this.niveau[index][i] = new Passage(index, i);

                    //D : Dalle
                    case 'D' -> this.niveau[index][i] = new Dalle(index, i);
                }
            }*/
            index++;
        }
        //this.printMap(new Heros());
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Getter path
     *
     * @return path
     */
    public InputStream getPath() {
        return path;
    }

    /**
     * Getter LONGUEUR
     *
     * @return LONGUEUR
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * Getter LARGEUR
     *
     * @return LARGEUR
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Getter lastLevel
     *
     * @return lastLevel
     */
    public boolean isLastLevel() {
        return lastLevel;
    }

    /**
     * Getter labyrinthe
     *
     * @return labyrinthe
     */
    public Case[][] getNiveau() {
        return niveau;
    }

    /**
     * Getter Case
     *
     * @param x entier représentant l'axe des X du labyrinthe
     * @param y entier représentant l'axe des Y du labyrinthe
     * @return CASE[][]
     */
    public Case getCase(int x, int y) {
        return this.niveau[y][x];
    }

    /**
     * Getter CaseObject
     *
     * @param x entier représentant l'axe des X du labyrinthe
     * @param y entier représentant l'axe des Y du labyrinthe
     * @return CASE[][]
     */
    public Case getCaseObject(int x, int y) {
        return this.objetNiveau[y][x];
    }





    /**
     * Print labyrinthe temporaire
     *
     * @param h Heros du niveau
     */
    public void printMap(Heros h) {
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
     *
     * @param b boolean
     */
    public void setLastLevel(boolean b) {
        this.lastLevel = b;
    }

    /**
     * Getter tour
     *
     * @return tour
     */
    public Tour getTour() {
        return this.tour;
    }

    /**
     * Dessiner le niveau
     *
     * @param image : image sur laquelle dessiner
     */
    @Override
    public void draw(BufferedImage image) {
        for (int i = 0; i < this.getLongueur(); i++) {
            for (int j = 0; j < this.getLargeur(); j++) {
                this.niveau[i][j].draw(image);
            }
        }
        for (int i = 0; i < this.getLongueur(); i++) {
            for (int j = 0; j < this.getLargeur(); j++) {
                if (this.objetNiveau[i][j] != null) this.objetNiveau[i][j].draw(image);
            }
        }


        for (Monstre m : this.monstres) {
            m.draw(image);
        }
        this.getTour().getHeros().draw(image);

    }


    /**
     * Getter de la clé du personnage
     *
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
        exitCase.open();
    }

    /**
     * remplace une case par une case Sol
     *
     * @param x colonne
     * @param y ligne
     */
    public void setCaseToSol(int x, int y) {
        this.objetNiveau[x][y] = null;
    }

    /**
     * Methode qui supprime un monstre du niveau
     *
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
    public void deleteNiveau() {
        this.monstresThreads.forEach((monstre, executor) -> executor.shutdown());
        this.monstresThreads.clear();
        this.monstres.clear();
    }

    /**
     * Methode qui stop les threads des monstres
     */
    public void pauseMonstres() {
        this.monstresThreads.forEach((monstre, executor) -> executor.shutdown());
        this.monstresThreads.clear();
    }
}
