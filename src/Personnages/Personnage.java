package src.Personnages;

/*
* Class représentant un personnage du jeu
* @author ????
 */
public class Personnage {

    //Position du personnage en X
    private int x;
    //Position du personnage en Y
    private int y;
    //Vie du personnage
    private int pdv;
    //Force du personnage
    private int pda;

    /*
    * Constructeur du personnage
    * @param x : position en X
    * @param y : position en Y
    * @param pdv : points de vie
    * @param pda : points d'attaque
     */
    public Personnage(int x, int y, int pdv, int pda){
        //Initialisation des attributs
        this.x = x;
        this.y = y;
        this.pdv = pdv;
        this.pda = pda;
    }

    /*
    * Méthode permettant de déplacer le personnage en X
    * @param x
     */
    public void moveX(int dir){
        throw new Error("Not implemented yet");
    }


    /*
    * Méthode permettant de déplacer le personnage en Y
    * @param y
     */
    public void moveY(int dir){
        throw new Error("Not implemented yet");
    }

    /*
    * Méthode permettant de déplacer le personnage
    * @param dirX
    * @param dirY
     */
    public void move(int dirX, int dirY){
        throw new Error("Not implemented yet");
    }

    /*
    * Méthode qui permet d'attaquer les personnages en face du personnage
     */
    public void attaque(){
        throw new Error("Not implemented yet");
    }

}
