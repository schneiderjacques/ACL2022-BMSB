package main.Personnages;

/*
 * Class représentant un monstre du jeu
 * @author Arthur Moitrier
 */
public abstract class Monstre extends Personnage {

    /*
     * Constructeur du monstre
     * @param x : position en X
     * @param y : position en Y
     * @param pdv : points de vie
     * @param pda : points d'attaque
     */
    public Monstre(int x, int y, double pdv, double pda) {
        super(x, y, pdv, pda);
    }

    /*
     * Méthode permettant de déplacer le monstre
     */
    public abstract void moveRandom();

    public boolean attaque(Personnage adv){
        if(((adv.getX() == getX()+1 && getLM() == "d") ||
                (adv.getX() == getX()-1 && getLM() == "g") ||
                (adv.getY() == getY()-1 && getLM() == "h") ||
                (adv.getY() == getY()+1 && getLM() == "b" )) &&
                !adv.beMonster()
        ){
            adv.recevoirDegats(getPDA());
            return true;
        }
        return false;
    }

    public boolean beMonster(){
        return true;
    }
}
