package main.Cases;

/*
* Class représentant une case du labyrinthe
* @author Anthony Briot
 */
public class Case {


    //le joueur entre en collision avec la case ou non
    private boolean collision;

    /*
    * Constructeur de la classe Case
    * @param collision : le joueur entre en collision avec la case ou non
     */
    public Case(boolean collision){
        //Initialisation des attributs
        this.collision = collision;
    }


    /*
    * Getter de collision
    * @return collision : le joueur entre en collision avec la case ou non
     */
    public boolean getCollision() {
        return this.collision;
    }
}
