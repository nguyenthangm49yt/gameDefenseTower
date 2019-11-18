package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Effect {

     public static MediaPlayer mediaPlayer;

     public static void playSound(String path) {
     String musicFileName = path;
     Media sound = new Media(new File(musicFileName).toURI().toString());
     mediaPlayer = new MediaPlayer(sound);
     mediaPlayer.play();
     }

     public static void playSound_mainmenu(){
          playSound("./sound/main-menu-theme.mp3");
     }

     public static void playSound_battle_theme(){
          playSound("./sound/Battle-Theme.mp3");
     }

     public static void playSound_bomb(){
          playSound("./sound/Bomb.mp3");
     }

     public static void playSound_winner(){
          stop_sound();
          playSound("./sound/winner.mp3");
     }

     public static void playSound_loser(){
          stop_sound();
          playSound("./sound/loser.mp3");
     }

     public static void stop_sound(){
          mediaPlayer.stop();
     }

}
