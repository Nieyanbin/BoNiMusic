package com.example.dell.bonimusic.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dell.bonimusic.view.fragments.MyMusicFragment;
import com.example.dell.bonimusic.view.fragments.OnlineMusicFragment;

/**
 * Created by dell on 2017/9/28.
 */
public class MainAdapter extends FragmentStatePagerAdapter {
    int nNumOfTabs;
    public MainAdapter(FragmentManager fm,int nNumOfTabs) {
        super(fm);
        this.nNumOfTabs=nNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                MyMusicFragment tab1=new MyMusicFragment();
                return tab1;
            case 1:
                OnlineMusicFragment tab2=new OnlineMusicFragment();
                return tab2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return nNumOfTabs;
    }
}
