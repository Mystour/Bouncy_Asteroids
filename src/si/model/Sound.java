package si.model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Sound {
    private final Logger LOGGER = Logger.getLogger(Sound.class.getName());
    private final Clip menuSound;
    private final Clip gameSound;

    private static Sound instance = null;

    public Sound() {
        menuSound = initSound("D:\\Program\\Java_work\\Bouncy_Asteroids\\sounds\\menuSound.wav");
        gameSound = initSound("D:\\Program\\Java_work\\Bouncy_Asteroids\\sounds\\gameSound.wav");
    }

    public static Sound getInstance(){
        if(instance == null){
            instance = new Sound();
        }
        return instance;
    }

    public void playBulletSound() {
        playSound("D:\\Program\\Java_work\\Bouncy_Asteroids\\sounds\\bulletSound.wav");
    }

    public void hitSound() {
        playSound("D:\\Program\\Java_work\\Bouncy_Asteroids\\sounds\\hitSound.wav");
    }

    public void propsSound() {
        playSound("D:\\Program\\Java_work\\Bouncy_Asteroids\\sounds\\propsSound.wav");
    }

    private void playSound(String path) {
        Clip sound = initSound(path);
        if (sound != null) {
            sound.start();
        }
    }

    private Clip initSound(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip sound = AudioSystem.getClip();
            sound.open(audioInputStream);
            return sound;
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            LOGGER.log(Level.SEVERE, "Error with initializing sound.", ex);
            return null;
        }
    }

    public void playMenuSound() {
        if (menuSound != null) {
            menuSound.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopMenuSound() {
        if (menuSound != null) {
            menuSound.stop();
        }
    }

    public void playGameSound() {
        if (gameSound != null) {
            gameSound.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stopGameSound() {
        if (gameSound != null) {
            gameSound.stop();
        }
    }
}