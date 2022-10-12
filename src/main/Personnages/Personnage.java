package main.Personnages;

/*
* Class représentant un personnage du jeu
* @author Martin Gurtner
 */
public abstract class Personnage {

    //Position du personnage en X
    private int x;
    //Position du personnage en Y
    private int y;
    //Vie du personnage
    private double pdv;
    //Force du personnage
    private double pda;

    /*
    * Constructeur du personnage
    * @param x : position en X
    * @param y : position en Y
    * @param pdv : points de vie
    * @param pda : points d'attaque
     */
    public Personnage(int x, int y, double pdv, double pda){
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
        //throw new Error("Not implemented yet");
        this.x = this.x + dir; 
    }


    /*
    * Méthode permettant de déplacer le personnage en Y
    * @param y
     */
    public void moveY(int dir){
        //throw new Error("Not implemented yet");
        this.y = this.y + dir;
    }

    /*
    * Méthode permettant de déplacer le personnage
    * @param dirX
    * @param dirY
     */
    public void move(int dirX, int dirY){
        //throw new Error("Not implemented yet");
        moveX(dirX);
        moveY(dirY);
    }

    /*
    * Méthode qui permet d'attaquer les personnages en face du personnage
     */
    public void attaque(){
        throw new Error("Not implemented yet");
    }

    /*
     * Getter de la position X du personnage
     * @return x
     */
    public int getX(){
        return x; 
    }

    /*
     * Getter de la position Y du personnage
     * @return y
     */
    public int getY(){
        return y; 
    }

    /**
     * getteur de la vie du personnage
     * @return pdv
     */
    public double getPDV(){
        return pdv; 
    }

    /**
     * getteur de la force du personnage
     * @return pda
     */
    public double getPDA(){
        return pda; 
    }

    /**
     * Getter du type de personnage
     * @return type de personnage
     */
    public abstract String getType();

    /**
     * toString
     * @return String
     */
    public String toString(){
        return "personnage : \n"+
        "Vie : "+getPDV()+"\n"+
        "Attaque : "+getPDA()+"\n"+
        "Position : "+getX()+";"+getY()+"\n\n"; 
    }

}
