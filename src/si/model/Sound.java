package si.model;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Sound {
    private static final Logger LOGGER = Logger.getLogger(Sound.class.getName());
    private static Clip menuSound;
    private static Clip gameSound;

    public static void playBulletSound() {
        Clip bulletSound = initSound("D:\\Program\\Java_work\\Bouncy_Asteroids\\sounds\\bulletSound.wav");
        if (bulletSound != null) {
            bulletSound.start();
        }
    }

    public static Clip initSound(String path){
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

    public static void playMenuSound() {
        if (menuSound == null) { menuSound = initSound("D:\\Program\\Java_work\\Bouncy_Asteroids\\sounds\\menuSound.wav");
        } else menuSound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopMenuSound() {
        menuSound.stop();
    }

    public static void playGameSound() {
        if (gameSound == null) { gameSound = initSound("D:\\Program\\Java_work\\Bouncy_Asteroids\\sounds\\gameSound.wav");
        } else gameSound.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
