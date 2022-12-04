package main.java.Engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    private Clip clip;

    URL[] soundUrl = new URL[30];

    public Sound() {
        soundUrl[0] = Sound.class.getResource("/sounds/game/hero/attack.wav");
        soundUrl[1] = Sound.class.getResource("/sounds/game/hero/death.wav");
        soundUrl[2] = Sound.class.getResource("/sounds/game/key_pickup.wav");
        soundUrl[3] = Sound.class.getResource("/sounds/game/monster_attack.wav"); 
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void stop(){
        clip.stop();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
