package main.Cases;

/*
 * Class représentant la sortie du labyrinthe
 * @author Anthony Briot
 */
public class Sortie extends Case {
    /*
    * Constructeur de la case sortie
    * @param x : emplacement de la case en X
    * @param y : emplacement de la case en Y
     */
    public Sortie(int x, int y) {
        //Initialisation des attributs
        super(false, x, y);
    }

    /**
     * Getter du type de case
     * @return type de case
     */
    public String getType() {
        return "Sortie";
    }
}
