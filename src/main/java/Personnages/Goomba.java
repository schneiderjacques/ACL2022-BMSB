package main.java.Personnages;

import main.java.Principale.Niveau;
import main.java.Principale.Tools;

/**
 * Class représentant un personnage du jeu
 * @author Arthur Moitrier
 */
public class Goomba extends Monstre {

    /**
     * Constructeur du monstre Goomba
     * @param x : position en X
     * @param y : position en Y
     * @param n : niveau dans lequel se trouve le monstre
     */
    public Goomba(int x, int y, Niveau n) {
        super(x, y, true, 15, 2, n, 3);

        /**
         * initialisation des images du monstre
         */
        this.initImage(9);
        for (int i = 0; i < 9; i++) {
            this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/idle/down/sprite_idle_down" + i), i);
        }
    }

    /**
     * Constructeur du monstre Goomba
     * @param x : position en X
     * @param y : position en Y
     */
    public Goomba(int x, int y) {super(x, y, true, 15, 2,3);}

    /**
     * Méthode permettant de changer les frames du monstre
     */
    @Override
    public void changeFrame() {
        this.setFrame(0);
        switch (this.getLM()) {
            case "d":
                if (this.getAnimationAttack()) {
                    this.initImage(8);
                    for (int i = 0; i <= 7; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/attack/right/sprite_attack_right" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/idle/right/sprite_idle_right" + i), i);
                    }
                } else {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/attacked/right/sprite_attacked_right" + i), i);
                    }
                }
                break;
            case "g":
                if (this.getAnimationAttack()) {
                    this.initImage(8);
                    for (int i = 0; i <= 7; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/attack/left/sprite_attack_left" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/idle/left/sprite_idle_left" + i), i);
                    }
                } else {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/attacked/left/sprite_attacked_left" + i), i);
                    }
                }

                break;
            case "h":
                if (this.getAnimationAttack()) {
                    this.initImage(8);
                    for (int i = 0; i <= 7; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/attack/left/sprite_attack_left" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/idle/up/sprite_idle_up" + i), i);
                    }
                } else {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/attacked/up/sprite_attacked_up" + i), i);
                    }
                }

                break;
            case "b":
                if (this.getAnimationAttack()) {
                    this.initImage(8);
                    for (int i = 0; i <= 7; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/attack/left/sprite_attack_left" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/idle/down/sprite_idle_down" + i), i);
                    }
                } else {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/goomba/tiles/attacked/down/sprite_attacked_down" + i), i);
                    }
                }

                break;
        }
    }
}
