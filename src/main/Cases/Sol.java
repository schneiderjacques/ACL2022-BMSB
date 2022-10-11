package main.Cases;

/*
* Class représentant une case sol du labyrinthe
* author Anthony Briot
 */
public class Sol extends Case {
    /*
    * Constructeur de la case sol
    * @param x : emplacement de la case en X
    * @param y : emplacement de la case en Y
     */
    public Sol() {
        //Initialisation des attributs
        super(false);
    }

    /**
     * Getter du type de case
     * @return type de case
     */
    public String getType() {
        return "Sol";
    }
}
