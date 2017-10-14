package com.example.dell.bonimusic.modle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.dell.bonimusic.modle.Bean.MusicBean;
import com.example.dell.bonimusic.presenter.MainPresenter;
import com.example.dell.bonimusic.presenter.PlayPresenter;
import com.example.dell.bonimusic.presenter.SearchPresenter;
import com.example.dell.bonimusic.presenter.SongPresenter;
import com.example.dell.bonimusic.utils.Jiekou;
import com.example.dell.bonimusic.view.interfaces.MainviewM_P;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2017/9/28.
 */
public class Httputils<T> {
    OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(new HttpInterceptor()).build();
    Gson gson=new Gson();
    private Jiekou jiekou=new Jiekou();
    private MainPresenter view;
    private SearchPresenter searchPresenter;
    private PlayPresenter playPresenter;
    private SongPresenter songPresenter;
    //列表项
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
       T obj = (T) msg.obj;
       view.Succeed(obj);
    }
};
    //搜索歌曲
    private Handler handler1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            T obj = (T) msg.obj;
            searchPresenter.Succeed(obj);
        }
    };
    //播放音乐
    private Handler handler2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            T obj = (T) msg.obj;
            playPresenter.Succeed(obj);
        }
    };
    //获取歌词
    private Handler handler3=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            T obj = (T) msg.obj;
            songPresenter.Succeed(obj);
        }
    };
    /**
     * 网络请求 GetHead方法，获取列表
     */
    public void getHead(String url, Map<String, String> map, final Class<T> clazz) {
        StringBuilder builder = new StringBuilder(url);
        for (Map.Entry<String, String> ent : map.entrySet()) {
//            request.addHeader(ent.getKey(), ent.getValue());
            builder.append("&" + ent.getKey() + "=" + ent.getValue());
        }
        Log.d("zzz", builder.toString());
        Request request = new Request.Builder()
                .url(builder.toString())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            //失败
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("zzzzzz", "错误" + call.toString() + e.getLocalizedMessage());
            }

            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                T c = (T) gson.fromJson(str, clazz);
                Message message = handler.obtainMessage();
                message.obj = c;
                handler.sendMessage(message);
            }
        });

    }
    //播放音乐
    public void getjiexi(String songid, final Class<T> clazz){
     String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid="+songid;
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
           //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                T t = gson.fromJson(string, clazz);
                Message message = handler2.obtainMessage();
                message.obj = t;
                handler2.sendMessage(message);
            }
        });
    }
    //获取歌词
    public void getsong(String song_id, final Class<T> clazz){
        String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.lry&songid="+song_id;
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                T t = gson.fromJson(string, clazz);
                Message message = handler3.obtainMessage();
                message.obj = t;
                handler3.sendMessage(message);
            }
        });
    }
    //搜索歌曲
    public void getsearch(String query, final Class<T> clazz){
        String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.catalogSug&query="+query;
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                T t = gson.fromJson(string, clazz);
                Message message = handler1.obtainMessage();
                message.obj = t;
                handler1.sendMessage(message);

            }
        });
    }

    public void initGetBeanM_P(MainPresenter mainPresenter) {
        this.view=mainPresenter;
    }

    public void initGetBeanM_P(SearchPresenter searchPresenter) {
        this.searchPresenter=searchPresenter;
    }
    public void initGetBeanM_P(PlayPresenter playPresenter) {
        this.playPresenter=playPresenter;
    }
    public void setview(SongPresenter view) {
        this.songPresenter = view;
    }
}
