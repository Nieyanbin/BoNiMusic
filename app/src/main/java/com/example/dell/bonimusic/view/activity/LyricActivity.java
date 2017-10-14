package com.example.dell.bonimusic.view.activity;

import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.modle.Bean.PlayBean;
import com.example.dell.bonimusic.presenter.PlayPresenter;
import com.example.dell.bonimusic.utils.App;
import com.example.dell.bonimusic.utils.MusicPlay;
import com.example.dell.bonimusic.utils.Player;
import com.example.dell.bonimusic.view.fragments.MyFragmentPageAdapter;
import com.example.dell.bonimusic.view.fragments.PlayFragment;

public class LyricActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private MyFragmentPageAdapter mAdapter;
    private ImageView imgback;
    private TextView tvname;
    private ViewPager vp;
    private SeekBar skbProgress;
    private ImageView imgxunhuan;
    private ImageView imgzuo;
    private CheckBox imgzj;
    private ImageView imgyou;
    private PlayFragment playFragment;
    private Player player;
    private MediaPlayer mediaPlayer;
    private TextView tvsingger;
    private String title;
    private String author;
    private PlayBean playBean;
    private PlayPresenter playPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyric);
        //初始化
        initview();
        //这里因为是3.0一下版本，所以需继承FragmentActivity，通过getSupportFragmentManager()获取FragmentManager
        //3.0及其以上版本，只需继承Activity，通过getFragmentManager获取事物
        FragmentManager fm = getSupportFragmentManager();
        //初始化自定义适配器
        mAdapter =  new MyFragmentPageAdapter(fm);
        //绑定自定义适配器
        mViewPager.setAdapter(mAdapter);
        //赋值
        assignment();
        //判断音乐是否播放
//        if(mediaPlayer.isPlaying()){
//            imgzj.setChecked(true);
//            playFragment.donghua();
//        }else{
//            imgzj.setChecked(false);
//        }
    }
    //赋值
    private void assignment() {


        if(playBean!=null){
            title = playBean.getSonginfo().getTitle();
            author = playBean.getSonginfo().getAuthor();
            tvname.setText(title);
            tvsingger.setText(author);
        }

}
    //初始化
    private void initview() {
        playPresenter = new PlayPresenter();
        playBean = playPresenter.getPlayBean();
        tvsingger = (TextView) findViewById(R.id.tvsingger);
        playFragment = new PlayFragment();
        mediaPlayer = MusicPlay.playMusic();
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        imgback = (ImageView) findViewById(R.id.imgback);
        tvname = (TextView) findViewById(R.id.tvname);
        vp = (ViewPager) findViewById(R.id.viewpager);
        skbProgress = (SeekBar) findViewById(R.id.skbProgress);
        skbProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        player = new Player(skbProgress);
        imgxunhuan = (ImageView) findViewById(R.id.imgxunhuanbofang);
        imgzuo = (ImageView) findViewById(R.id.imgzuo);
        imgzuo.setOnClickListener(this);
        imgzj = (CheckBox) findViewById(R.id.imgzj);
        imgzj.setOnClickListener(this);
        imgyou = (ImageView) findViewById(R.id.imgyou);
        imgyou.setOnClickListener(this);
        //关闭此页面
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //播放的点击事件，实现播放暂停
        imgzj.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    mediaPlayer.start();
                    playFragment.donghua();
                }else{
                    mediaPlayer.pause();
                    playFragment.fandonghua();
                }
            }
        });
    }
//上一首 暂停播放 下一首
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgzuo:

                break;
            case R.id.imgzj:

                break;
            case R.id.imgyou:
                break;
        }
    }
    //改变seekbar的值
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {
        int progress;
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // 原本是(progress/seekBar.getMax())*player.mediaPlayer.getDuration()
            this.progress = progress * player.mediaPlayer.getDuration()
                     /seekBar.getMax();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // seekTo()的参数是相对与影片时间的数字，而不是与seekBar.getMax()相对的数字
            player.mediaPlayer.seekTo(progress);
        }
    }
}
