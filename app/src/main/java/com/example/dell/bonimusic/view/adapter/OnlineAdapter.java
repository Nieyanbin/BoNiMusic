package com.example.dell.bonimusic.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.modle.Bean.MusicBean;
import com.example.dell.bonimusic.modle.Bean.MusicBeanChild;
import com.example.dell.bonimusic.presenter.MainPresenter;
import com.example.dell.bonimusic.view.activity.ParticularsActivity;
import com.example.dell.bonimusic.view.interfaces.MainviewP_V;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dell on 2017/9/28.
 */
public class OnlineAdapter extends RecyclerView.Adapter{
    private List<MusicBeanChild> musicBeanChildren;
    Context context;
    private MainPresenter mainPresenter;
    private final int TYPE1 = 0;
    private final int TYPE2 = 1;

    public OnlineAdapter(List<MusicBeanChild> musicBeanChildren, Context context, MainPresenter mainPresenter) {
        this.musicBeanChildren = musicBeanChildren;
        this.context = context;
        this.mainPresenter = mainPresenter;
    }

    @Override
    public int getItemViewType(int position) {
        String type = musicBeanChildren.get(position).getType();
        if (type.equals("#")) {
            return TYPE1;
        } else {
            return TYPE2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int itemViewType = getItemViewType(viewType);
        if (itemViewType == 0) {
            View view = View.inflate(context, R.layout.online_item_tou, null);
            return new Holder1(view);
        } else {
            View view = View.inflate(context, R.layout.bangdanitem, null);
            return new Holder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 0) {
            if (holder instanceof Holder1) {
                Holder1 holder1 = (Holder1) holder;
                holder1.textView.setText(musicBeanChildren.get(position).getListName());
            }
        } else {
            if (holder instanceof Holder2) {
                final Holder2 holder2 = (Holder2) holder;
                holder2.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String type = musicBeanChildren.get(position).getType();
                        Intent intent = new Intent(context, ParticularsActivity.class);
                        intent.putExtra("type", type);
                        context.startActivity(intent);
                    }
                });
                mainPresenter.loadview(musicBeanChildren.get(position).getType());
                mainPresenter.initGetBeanP_V(new MainviewP_V() {
                    @Override
                    public void Succeed(Object o) {
                        MusicBean musicBean = (MusicBean) o;
                        List<MusicBean.SongListBean> song_list = musicBean.getSong_list();
                        int size = song_list.size();
                        switch (size) {
                            case 1:
                                ImageLoader.getInstance().displayImage(musicBean.getBillboard().getPic_s444(), holder2.imageView);
                                holder2.music1.setText(song_list.get(0).getTitle() + "-" + song_list.get(0).getArtist_name());
                                holder2.music2.setVisibility(View.GONE);
                                holder2.music3.setVisibility(View.GONE);
                                break;
                            case 2:
                                ImageLoader.getInstance().displayImage(musicBean.getBillboard().getPic_s444(), holder2.imageView);
                                holder2.music1.setText(song_list.get(0).getTitle() + "-" + song_list.get(0).getArtist_name());
                                holder2.music2.setText(song_list.get(1).getTitle() + "-" + song_list.get(1).getArtist_name());
                                holder2.music3.setVisibility(View.GONE);
                                break;
                            case 3:
                                ImageLoader.getInstance().displayImage(musicBean.getBillboard().getPic_s444(), holder2.imageView);
                                holder2.music1.setText(song_list.get(0).getTitle() + "-" + song_list.get(0).getArtist_name());
                                holder2.music2.setText(song_list.get(1).getTitle() + "-" + song_list.get(1).getArtist_name());
                                holder2.music3.setText(song_list.get(2).getTitle() + "-" + song_list.get(2).getArtist_name());
                                break;
                            default:
                                ImageLoader.getInstance().displayImage(musicBean.getBillboard().getPic_s444(), holder2.imageView);
                                holder2.music1.setText(song_list.get(0).getTitle() + "-" + song_list.get(0).getArtist_name());
                                holder2.music2.setText(song_list.get(1).getTitle() + "-" + song_list.get(1).getArtist_name());
                                holder2.music3.setText(song_list.get(2).getTitle() + "-" + song_list.get(2).getArtist_name());
                                break;
                        }
                    }
                });

            }
        }
    }

    @Override
    public int getItemCount() {
        return musicBeanChildren.size();
    }
    class Holder1 extends RecyclerView.ViewHolder {
        TextView textView;

        public Holder1(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item1_textView);
        }
    }

    class Holder2 extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView music1;
        TextView music2;
        TextView music3;
        LinearLayout linearLayout;

        public Holder2(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.bangdanimg);
            music1 = itemView.findViewById(R.id.tv1);
            music2 = itemView.findViewById(R.id.tv2);
            music3 = itemView.findViewById(R.id.tv3);
            linearLayout = itemView.findViewById(R.id.dll);
        }
    }
}
