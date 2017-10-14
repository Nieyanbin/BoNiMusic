package com.example.dell.bonimusic.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.bonimusic.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by dell on 2017/9/28.
 */
@ContentView(R.layout.mymusic)
public class MyMusicFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= x.view().inject(this,inflater,container);
        TextView tv=view.findViewById(R.id.tv);
        tv.setText("暂无本地音乐");
        return view;
    }
}
