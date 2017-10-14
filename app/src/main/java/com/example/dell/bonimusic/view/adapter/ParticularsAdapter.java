package com.example.dell.bonimusic.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.modle.Bean.MusicBean;
import com.example.dell.bonimusic.modle.Bean.PlayBean;
import com.example.dell.bonimusic.modle.HttpInterceptor;
import com.example.dell.bonimusic.presenter.PlayPresenter;
import com.example.dell.bonimusic.presenter.SongPresenter;
import com.example.dell.bonimusic.view.activity.LyricActivity;
import com.example.dell.bonimusic.view.activity.ParticularsActivity;
import com.example.dell.bonimusic.view.activity.SinggerActivity;
import com.example.dell.bonimusic.view.interfaces.MainviewP_V;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2017/10/2.
 */
public class ParticularsAdapter extends RecyclerView.Adapter  {
    private  ParticularsActivity context;
//    private String url="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid=";

    List<MusicBean.SongListBean> list;
   private PlayPresenter playPresenter=new PlayPresenter();
    private SongPresenter songPresenter=new SongPresenter();
    private MediaPlayer mediaPlayer;
    private String file_link;



    public ParticularsAdapter(ParticularsActivity context, List<MusicBean.SongListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_xiangqing, null);
        return new ParticularsHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ParticularsHolder particularsHolder= (ParticularsHolder) holder;
        final String ting_uid = list.get(position).getTing_uid();
        particularsHolder.tvname.setText(list.get(position).getTitle());
        particularsHolder.tvsingger.setText(list.get(position).getAuthor()+"-"+list.get(position).getAlbum_title());
        ImageLoader.getInstance().displayImage(list.get(position).getPic_small(),particularsHolder.img);
        final String song_id = list.get(position).getSong_id();
        //点击播放音乐
        particularsHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,"点击条目"+position+"歌曲索引值"+song_id,Toast.LENGTH_LONG).show();
                //跳转到播放页面
//                Intent intent=new Intent(context, LyricActivity.class);
//                context.startActivity(intent);
                playPresenter.play(song_id);
                //加载歌词
                songPresenter.loadsong(song_id);
            }
        });
        //分享 歌手详情 下载 点击事件
        particularsHolder.imgfx.setOnClickListener(new View.OnClickListener() {
            private AlertDialog.Builder ab;
            @Override
            public void onClick(View view) {
                final String[] items = {"分享","歌手详情","下载"};
                ab = new AlertDialog.Builder(context).setTitle(list.get(position).getTitle());
                   ab.setItems(items, new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int index) {
                         if(items[index]==items[0]){
                             UMWeb web = new UMWeb("");
                             web.setTitle("This is music title");//标题
                             web.setDescription("my description");//描述
                             new ShareAction((Activity) context)
                                     .withMedia(web)
                                     .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                                     .open();
                         }else if(items[index]==items[1]){
                                Intent intent=new Intent(context, SinggerActivity.class);
                             intent.putExtra("uid",ting_uid);
                             context.startActivity(intent);
                         }else{
                             Toast.makeText(context,"下载", Toast.LENGTH_SHORT).show();
                         }
                       }
                   });
                ab.create();
                  ab.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ParticularsHolder extends RecyclerView.ViewHolder {
      private ImageView img;
        private TextView tvname;
        private TextView tvsingger;
       private LinearLayout ll;
        private ImageView imgfx;
        public ParticularsHolder(View itemView) {
            super(itemView);
   img=itemView.findViewById(R.id.imgxinagqing);
            tvname=itemView.findViewById(R.id.tvname);
            tvsingger=itemView.findViewById(R.id.tvsingger);
            ll=itemView.findViewById(R.id.xqll);
            imgfx=itemView.findViewById(R.id.fx);
        }
    }
}
