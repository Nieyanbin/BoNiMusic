package com.example.dell.bonimusic.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.modle.Bean.MusicBean;
import com.example.dell.bonimusic.modle.Bean.MusicBeanChild;
import com.example.dell.bonimusic.presenter.MainPresenter;
import com.example.dell.bonimusic.view.activity.MainActivity;
import com.example.dell.bonimusic.view.adapter.OnlineAdapter;


import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/9/28.
 */
@ContentView(R.layout.onlinemusic)
public class OnlineMusicFragment extends Fragment{
    private List<MusicBeanChild> musicBeanChildren = new ArrayList<>();
    private View view;
    private RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = x.view().inject(this,inflater,container);
        rv = view.findViewById(R.id.rv1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(linearLayoutManager);
        initDate();
        return view;
    }

    private void initDate() {
//将数据存到数组中
        String[] title = getResources().getStringArray(R.array.music_title);
        String[] type = getResources().getStringArray(R.array.musiclist_type);
        for (int i = 0; i < title.length; i++) {
            MusicBeanChild musicBeanChild = new MusicBeanChild();
            musicBeanChild.setType(type[i]);
            musicBeanChild.setListName(title[i]);
            musicBeanChildren.add(musicBeanChild);
        }
        //添加适配器
        rv.setAdapter(new OnlineAdapter(musicBeanChildren,getActivity(),new MainPresenter()));


    }



}
