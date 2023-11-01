package com.elixir.controller.objects;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
    public static void playButtonClickSound() {
        String soundFile = "/audio/Cursor2.mp3";
        Media sound = new Media(SoundPlayer.class.getResource(soundFile).toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}