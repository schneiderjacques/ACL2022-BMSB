package main.Cases;

/*
* Class représentant une case du labyrinthe
* @author Anthony Briot
 */
public abstract class Case {

    //Position de la case en X
    private int x;

    //Position de la case en Y
    private int y;

    //le joueur entre en collision avec la case ou non
    private boolean collision;

    /*
    * Constructeur de la classe Case
    * @param collision : le joueur entre en collision avec la case ou non
     */
    public Case(boolean collision, int x, int y){
        //Initialisation des attributs
        this.x = x;
        this.y = y;
        this.collision = collision;
    }

    /*
    * Getter de collision
    * @return collision : le joueur entre en collision avec la case ou non
     */
    public boolean getCollision() {
        return this.collision;
    }

    /**
     * Getter du type de case
     * @return type de case
     */
    public abstract String getType();
}
