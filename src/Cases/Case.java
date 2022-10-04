package src.Cases;

/*
* Class représentant une case du labyrinthe
* @author ????
 */
public class Case {

    //emplacement de la case en X
    private int x;

    //emplacement de la case en Y
    private int y;

    //le joueur entre en collision avec la case ou non
    private boolean collision;

    /*
    * Constructeur de la classe Case
    * @param x : emplacement de la case en X
    * @param y : emplacement de la case en Y
    * @param collision : le joueur entre en collision avec la case ou non
     */
    public Case(int x, int y, boolean collision){
        //Initialisation des attributs
        this.x = x;
        this.y = y;
        this.collision = collision;
    }
}
