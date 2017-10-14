package com.example.dell.bonimusic.view.fragments;

/**
 * Created by dell on 2017/10/11.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 自定义fragment适配器
 * @author ZHF
 *
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    public MyFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PlayFragment();
            case 1:
                return new SongFragment();
            default:
                return null;
        }
    }
}
