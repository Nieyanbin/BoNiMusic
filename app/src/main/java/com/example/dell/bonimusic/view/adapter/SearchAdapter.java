package com.example.dell.bonimusic.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.modle.Bean.SearchBean;
import com.example.dell.bonimusic.presenter.PlayPresenter;

import java.util.List;

/**
 * Created by dell on 2017/10/9.
 */
public class SearchAdapter extends RecyclerView.Adapter{
    Context context;
    List<SearchBean.SongBean> list;
    PlayPresenter playPresenter=new PlayPresenter();
    public SearchAdapter(Context context, List<SearchBean.SongBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchHolder searchHolder = new SearchHolder(LayoutInflater.from(context).inflate(R.layout.search_item, parent, false));
        return searchHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
   if(holder instanceof SearchHolder){
       ((SearchHolder) holder).tvname.setText(list.get(position).getSongname());
       ((SearchHolder) holder).tvsingger.setText(list.get(position).getArtistname());
       final String songid = list.get(position).getSongid();
       ((SearchHolder) holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,"点击了"+position,Toast.LENGTH_SHORT).show();
                playPresenter.play(songid);
            }
        });
   }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class SearchHolder extends RecyclerView.ViewHolder {
      private TextView tvname;
        private TextView tvsingger;
        private LinearLayout ll;
        public SearchHolder(View itemView) {
            super(itemView);
         tvname=itemView.findViewById(R.id.search_name);
            tvsingger=itemView.findViewById(R.id.search_singger);
            ll=itemView.findViewById(R.id.sousuo_ll);
        }
    }
}
