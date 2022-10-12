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
    * Méthode permettant de déplacer le personnage
    * @param dirX
    * @param dirY
     */
    public void move(char axe, int dir){
        switch (axe){
            case 'X':
                this.x += dir;
                break;
            case 'Y':
                this.y += dir;
                break;
        }
    }

    /*
    * Méthode qui permet d'attaquer les personnages en face du personnage
     */
    public void attack(){
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
     * Setter de la position X du personnage
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter de la position Y du personnage
     * @param y
     */
    public void setY(int y) {
        this.y = y;
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
