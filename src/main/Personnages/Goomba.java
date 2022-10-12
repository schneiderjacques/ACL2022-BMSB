package main.Personnages;

/*
 * Class représentant un personnage du jeu
 * @author Arthur Moitrier
 */
public class Goomba extends Monstre {

    public Goomba(int x, int y, double pdv, double pda) {
        super(x, y, pdv, pda);
    }

    @Override
    public void moveRandom() {
        throw new Error("Not yet implemented");
    }

    @Override
    public String getType() {
        return "Goomba";
    }
}
