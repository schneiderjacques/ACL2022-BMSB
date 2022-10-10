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
    public Mur(boolean collision) {
        //Initialisation des attributs
        super(collision);
    }
}
