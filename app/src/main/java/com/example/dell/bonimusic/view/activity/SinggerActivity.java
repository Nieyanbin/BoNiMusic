package com.example.dell.bonimusic.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.modle.Bean.ParticularsBean;
import com.example.dell.bonimusic.modle.HttpInterceptor;
import com.example.dell.bonimusic.view.interfaces.MainviewP_V;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SinggerActivity extends AppCompatActivity{
    OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(new HttpInterceptor()).build();
    private TextView singgername;
    private ImageView singgerimg;
    private TextView singgerjj;
    private String uid;
    private String url;
    private Handler handler=new Handler(){



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ParticularsBean obj = (ParticularsBean) msg.obj;
            String name = obj.getName();
            singgername.setText("姓名："+name);
            String intro = obj.getIntro();
            singgerjj.setText("简介：\n"+intro);
            String avatar_s500 = obj.getAvatar_s500();
            ImageLoader.getInstance().displayImage(avatar_s500,singgerimg);
            url = obj.getUrl();

        }
    };
    private TextView singgermore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singger);
        Intent intent = getIntent();
        //获取到uid
         uid = intent.getStringExtra("uid");

        Toast.makeText(this,uid,Toast.LENGTH_SHORT).show();
        initView();

        jiexi();
    }

    private void jiexi() {
        String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.artist.getInfo&tinguid="+uid;
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                ParticularsBean particularsBean = gson.fromJson(string, ParticularsBean.class);
                Message message=new Message();
                message.obj=particularsBean;
                handler.sendMessage(message);
            }
        });
    }

    private void initView() {
        singgername = (TextView) findViewById(R.id.singgername);
        singgerimg = (ImageView) findViewById(R.id.singgerimg);
        singgerjj = (TextView) findViewById(R.id.singgerjj);
        singgermore = (TextView) findViewById(R.id.singgermore);
        singgermore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent=new Intent(SinggerActivity.this,WebViewActivity.class);
                intent.putExtra("web",url);
                startActivity(intent);
            }
        });
    }
}
