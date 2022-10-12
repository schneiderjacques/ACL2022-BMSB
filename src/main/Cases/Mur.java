package main.Cases;

/*
* Class représentant les murs du labyrinthe
* @author Anthony Briot
 */
public class Mur extends Case {
    /*
    * Constructeur de la classe Mur
    * @param x : emplacement de la case en X
    * @param y : emplacement de la case en Y
     */
    public Mur(boolean collision, int x, int y) {
        //Initialisation des attributs
        super(collision, x, y);
    }

    /**
     * Getter du type de case
     * @return type de case
     */
    public String getType() {
        return "Mur";
    }
}
