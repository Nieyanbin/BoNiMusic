package com.example.dell.bonimusic.presenter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.bonimusic.modle.Bean.PlayBean;
import com.example.dell.bonimusic.modle.Httputils;
import com.example.dell.bonimusic.utils.App;
import com.example.dell.bonimusic.utils.MusicPlay;
import com.example.dell.bonimusic.view.activity.ParticularsActivity;
import com.example.dell.bonimusic.view.interfaces.MainviewM_P;
import com.example.dell.bonimusic.view.interfaces.MainviewP_V;
import com.nostra13.universalimageloader.core.ImageLoader;


import java.io.IOException;

/**
 * Created by dell on 2017/10/9.
 */
public class PlayPresenter implements MainviewM_P, MediaPlayer.OnPreparedListener {
    private Httputils httputils = new Httputils();
    private String file_link;
    private MainviewP_V view;
    private static PlayBean playBean;
    private  MediaPlayer mediaPlayer;

    public PlayPresenter() {
        mediaPlayer = MusicPlay.playMusic();
        mediaPlayer.setOnPreparedListener(this);
    }
    public void play(String song_id) {
        httputils.initGetBeanM_P(this);
        httputils.getjiexi(song_id, PlayBean.class);
    }
    @Override
    public void Succeed(Object o) {
        playBean = (PlayBean) o;
        file_link = playBean.getBitrate().getFile_link();
        //播放音乐
       playmusic();
        Log.e("nybnybasdadasdadadada", file_link);
    }
    public void playmusic(){
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
        try {
            mediaPlayer.setDataSource(file_link);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
//返回值必须写成静态,方便调用
    public PlayBean getPlayBean() {
        return playBean;
    }

}
