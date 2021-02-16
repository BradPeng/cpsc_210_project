package ui.helpers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SoundPlayer {

    // EFFECTS: loads .wav file from "./data/dingSound.wav" and play sound
    public static void playSound() {
        URL soundbyte;

        try {
            soundbyte = new File("./data/dingSound.wav").toURI().toURL();
            java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
            clip.play();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
