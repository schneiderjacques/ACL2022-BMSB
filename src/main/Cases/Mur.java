package main.Cases;

/*
* Class représentant les murs du labyrinthe
* @author ????
 */
public class Mur extends Case {
    /*
    * Constructeur de la classe Mur
    * @param x : emplacement de la case en X
    * @param y : emplacement de la case en Y
     */
    public Mur(int x, int y) {
        //Initialisation des attributs
        super(x, y, true);
    }
}
