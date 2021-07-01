package GamePlay;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;

public class Music {
    private Clip clip;

    public Music(String music){
        AudioInputStream audio;
        try{
            URL url=Music.class.getResource(music);
            audio = AudioSystem.getAudioInputStream(url);
            clip= AudioSystem.getClip();
            clip.open(audio);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }



    }
    public void repeat() {
        clip.loop(-1);
    }
    public void stop(){clip.stop();}


    public static void background(){ new Music("/musicfile/background.wav").repeat();}
}
