package main.java.Personnages;

import main.java.Principale.Niveau;
import main.java.Principale.Tools;

/**
 * Class représentant un personnage du jeu
 * @author Arthur Moitrier
 */
public class Boo extends Monstre {

    /**
     * Constructeur du monstre Boo
     * @param x : position en X
     * @param y : position en Y
     * @param n : niveau dans lequel se trouve le monstre
     */
    public Boo(int x, int y, Niveau n) {
        super(x, y, false, 10, 1, n, 5);

        /**
         * initialisation des images du monstre
         */
        this.initImage(9);
        for (int i = 0; i < 9; i++) {
            this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/idle/down/sprite_idle_down" + i), i);
        }
    }

    /**
     * Constructeur du monstre Boo
     * @param x : position en X
     * @param y : position en Y
     */
    public Boo(int x, int y) {
        super(x, y, false, 10, 1, 5);
    }

    /**
     * Méthode permettant de changer les frames du monstre
     */
    @Override
    public void changeFrame() {
        this.setFrame(0);
        switch (this.getLM()) {
            case "d":
                if (this.getAnimationAttack()) {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/attack/right/sprite_attack_right" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/idle/right/sprite_idle_right" + i), i);
                    }
                } else {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/attacked/right/sprite_attacked_right" + i), i);
                    }
                }
                break;
            case "g":
                if (this.getAnimationAttack()) {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/attack/left/sprite_attack_left" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/idle/left/sprite_idle_left" + i), i);
                    }
                } else {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/attacked/left/sprite_attacked_left" + i), i);
                    }
                }

                break;
            case "h":
                if (this.getAnimationAttack()) {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/attack/up/sprite_attack_up" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/idle/up/sprite_idle_up" + i), i);
                    }
                } else {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/attacked/up/sprite_attacked_up" + i), i);
                    }
                }

                break;
            case "b":
                if (this.getAnimationAttack()) {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/attack/down/sprite_attack_down" + i), i);
                    }
                } else if (!this.getAnimationAttacked()) {
                    this.initImage(9);
                    for (int i = 0; i <= 8; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/idle/down/sprite_idle_down" + i), i);
                    }
                } else {
                    this.initImage(3);
                    for (int i = 0; i <= 2; i++) {
                        this.setImage(Tools.getImageByName("/images/game/monsters/boo/tiles/attacked/down/sprite_attacked_down" + i), i);
                    }
                }

                break;
        }
    }
}
