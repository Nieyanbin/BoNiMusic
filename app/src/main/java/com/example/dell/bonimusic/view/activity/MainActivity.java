package com.example.dell.bonimusic.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.modle.Bean.MusicBean;
import com.example.dell.bonimusic.modle.Bean.PlayBean;
import com.example.dell.bonimusic.presenter.MainPresenter;
import com.example.dell.bonimusic.presenter.PlayPresenter;
import com.example.dell.bonimusic.presenter.SearchPresenter;

import com.example.dell.bonimusic.utils.App;
import com.example.dell.bonimusic.utils.MusicPlay;
import com.example.dell.bonimusic.view.adapter.MainAdapter;
import com.example.dell.bonimusic.view.interfaces.MainviewP_V;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager vp;
    private ImageView imgcehua;
    private SlidingMenu menu;
    private ImageView imgsousuo;
    private ImageView play_img;
    private TextView play_title;
    private TextView play_name;
    private CheckBox play_imgbofang;
    private ImageView play_next;
    private  PlayPresenter playPresenter;
    private MediaPlayer mediaPlayer;
    private PlayBean playBean;
    private RelativeLayout rlrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据
        initview();
        //点击跳转
      rlrl.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
           Intent in=new Intent(MainActivity.this,LyricActivity.class);
              startActivity(in);
          }
      });
        //调用方法
        mediaPlayer = MusicPlay.playMusic();
        //播放的点击事件，实现播放暂停
        play_imgbofang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                  if(b){
                      mediaPlayer.start();
                  }else{
                      mediaPlayer.pause();
                  }
            }
        });
        //音乐播放下一首
        play_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"下一首",Toast.LENGTH_SHORT).show();
            }
        });
        //tablayout联动viewpage
        MainAdapter adapter=new MainAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //搜索的点击跳转
        imgsousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(in);
            }
        });
        //侧滑的点击事件
        imgcehua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menu.isMenuShowing()){

                }else{
                    menu.showMenu();
                }
            }
        });
        initMenu();
    }
    //初始化数据
    private void initview() {
        tabLayout = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        imgcehua = (ImageView) findViewById(R.id.imgcehua);
        imgsousuo = (ImageView) findViewById(R.id.imgsousuo);
        tabLayout.addTab(tabLayout.newTab().setText("我的音乐"));
        tabLayout.addTab(tabLayout.newTab().setText("在线音乐"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        play_img = (ImageView) findViewById(R.id.iv_play_bar_cover);
        play_title = (TextView) findViewById(R.id.tv_play_bar_title);
        play_name = (TextView) findViewById(R.id.tv_play_bar_name);
        play_imgbofang = (CheckBox) findViewById(R.id.iv_play_bar_play);
        play_next = (ImageView) findViewById(R.id.iv_play_bar_next);
        rlrl = (RelativeLayout) findViewById(R.id.rlrl);
    }

    //侧滑
    private void initMenu() {
        menu = new SlidingMenu(MainActivity.this);
//设置从哪里滑出
        menu.setMode(SlidingMenu.LEFT);

        //设置模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.du);
        menu.setBehindOffsetRes(R.dimen.du);

        //设置渐入渐出的效果
        menu.setFadeDegree(0.35f);

        menu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_CONTENT);

        menu.setMenu(R.layout.cehuaitem);
        TextView tvry=menu.findViewById(R.id.yejian);
        tvry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("name", MODE_PRIVATE);
                boolean night = sp.getBoolean("night", false);
                if(night){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sp.edit().putBoolean("night",false).commit();

                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sp.edit().putBoolean("night",true).commit();
                }
                recreate();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean playing = mediaPlayer.isPlaying();
        if(playing){

            play_imgbofang.setChecked(true);
            //初始化p层
            playPresenter = new PlayPresenter();
                //调用bean类
                playBean = playPresenter.getPlayBean();
                String title = playBean.getSonginfo().getTitle();
                Log.e("dasdadada聂雁宾","dasdasdsad"+title);
                //赋值
                ImageLoader.getInstance().displayImage(playBean.getSonginfo().getPic_small(),play_img);
                play_title.setText(playBean.getSonginfo().getTitle());
                play_name.setText(playBean.getSonginfo().getAuthor());
        }
    }
}
