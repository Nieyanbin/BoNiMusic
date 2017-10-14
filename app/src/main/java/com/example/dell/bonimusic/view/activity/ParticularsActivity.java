package com.example.dell.bonimusic.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.modle.Bean.MusicBean;
import com.example.dell.bonimusic.modle.Bean.PlayBean;
import com.example.dell.bonimusic.modle.HttpInterceptor;
import com.example.dell.bonimusic.modle.Httputils;
import com.example.dell.bonimusic.presenter.MainPresenter;
import com.example.dell.bonimusic.view.adapter.ParticularsAdapter;
import com.example.dell.bonimusic.view.interfaces.MainviewP_V;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMShareAPI;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ParticularsActivity extends AppCompatActivity implements MainviewP_V{
    OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(new HttpInterceptor()).build();
private Httputils httputils=new Httputils();
    private String type;
    private RecyclerView rv;
    private ImageView imgtu;
    private TextView tvname;
    private ImageView imgback;
    private List<MusicBean> list;
    private ParticularsAdapter ad;
    private Gson gson=new Gson();
    private String name;
    private String pic_s444;
    private MusicBean musicBean;
   private MainPresenter mainPresenter=new MainPresenter();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private LinearLayout gonell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particulars);
        initview();
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        Log.e("adadsadadad","adasdadadadsada"+type);
        mainPresenter.initGetBeanP_V(this);
        mainPresenter.loadview(type);
    }
    private void initview() {
        gonell = (LinearLayout) findViewById(R.id.gonell);
        imgback = (ImageView) findViewById(R.id.imgback);
        tvname = (TextView) findViewById(R.id.tvname);
        imgtu = (ImageView) findViewById(R.id.imgtu);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void Succeed(Object o) {
        MusicBean musicBean= (MusicBean) o;
        pic_s444 = musicBean.getBillboard().getPic_s444();
        name = musicBean.getBillboard().getName();
            //更新界面
            ImageLoader.getInstance().displayImage(pic_s444,imgtu);
            tvname.setText(name);
            //适配器
            ad = new ParticularsAdapter(ParticularsActivity.this,musicBean.getSong_list());
            rv.setAdapter(ad);
       if(musicBean!=null){
           gonell.setVisibility(View.GONE);
       }
    }

}
