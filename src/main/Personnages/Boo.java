package main.Personnages;

/*
 * Class représentant un personnage du jeu
 * @author Arthur Moitrier
 */
public class Boo extends Monstre{

    public Boo(int x, int y, double pdv, double pda) {
        super(x, y, pdv, pda);
    }

    @Override
    public void move() {

    }

    @Override
    public String getType() {
        return "Boo";
    }
}
