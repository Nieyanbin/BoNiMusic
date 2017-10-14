package com.example.dell.bonimusic.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.modle.Bean.SongBean;
import com.example.dell.bonimusic.presenter.SongPresenter;
import com.example.dell.bonimusic.view.interfaces.MainviewP_V;

/**
 * Created by dell on 2017/10/11.
 */
public class SongFragment extends Fragment{

    private View view;
    private TextView tv_song;
    private TextView tv_title;
    private SongPresenter songPresenter=new SongPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.song_item,null);
        initview();


        return view;
    }

    private void initview() {
        tv_song = view.findViewById(R.id.tv_song);
        tv_title = view.findViewById(R.id.tv_title);
        SongBean songBean = songPresenter.getSongBean();
        String title = songBean.getTitle();
        String lrcContent = songBean.getLrcContent();
        tv_title.setText(title);
        tv_song.setText(lrcContent);
    }

}
