package main.Cases;

import main.Personnages.Heros;
import main.Principale.Tour;

public interface CaseInterface {

    /**
     * Méthode qui est appelée lorsque le héros se déplace sur la case
     * @param t : Tour du jeu
     */
    void eventCollider(Tour t);
}
