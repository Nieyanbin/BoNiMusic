package com.example.dell.bonimusic.presenter;


import android.util.Log;

import com.example.dell.bonimusic.modle.Bean.SearchBean;
import com.example.dell.bonimusic.modle.Bean.SongBean;
import com.example.dell.bonimusic.modle.Httputils;
import com.example.dell.bonimusic.view.fragments.SongFragment;
import com.example.dell.bonimusic.view.interfaces.MainviewM_P;

/**
 * Created by dell on 2017/10/13.
 */
public class SongPresenter implements MainviewM_P{
private Httputils httputils=new Httputils();
    public static SongBean songBean;

    public void loadsong(String song_id) {
        httputils.setview(this);
       httputils.getsong(song_id,SongBean.class);
    }

    @Override
    public void Succeed(Object o) {
        songBean = (SongBean) o;
        String title = songBean.getTitle();
        Log.e("聂雁宾","聂雁宾"+title);
    }

    public SongBean getSongBean() {
        return songBean;
    }
}
