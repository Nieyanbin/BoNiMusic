package com.example.dell.bonimusic.utils;

import android.app.Application;
import android.media.MediaPlayer;

import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.presenter.PlayPresenter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by dell on 2017/9/28.
 */
public class App extends Application{

    private UMShareAPI umShareAPI;
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        init();
        umShareAPI = UMShareAPI.get(this);

    }


    private void init() {
            DisplayImageOptions options=new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .showImageOnLoading(R.mipmap.ic_launcher)
                    .build();

           ImageLoaderConfiguration con=new ImageLoaderConfiguration.Builder(this)
                   .defaultDisplayImageOptions(options)
                   .build();

            ImageLoader.getInstance().init(con);

        }

    public UMShareAPI getUmShareAPI() {
        return umShareAPI;
    }
}
