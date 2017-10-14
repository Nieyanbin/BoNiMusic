package com.example.dell.bonimusic.utils;

import android.media.MediaPlayer;

import com.example.dell.bonimusic.modle.Bean.PlayBean;

/**
 * Created by dell on 2017/10/12.
 */
public class MusicPlay {
    private static MediaPlayer mediaPlayer;
    public static MediaPlayer playMusic(){
        if(mediaPlayer==null){
            synchronized (MediaPlayer.class){
                if(mediaPlayer==null){
                    mediaPlayer=new MediaPlayer();
                }
            }
        }
        return  mediaPlayer;
    }
}
