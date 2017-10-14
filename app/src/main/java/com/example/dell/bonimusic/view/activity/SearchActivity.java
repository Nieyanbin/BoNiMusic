package com.example.dell.bonimusic.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.bonimusic.R;
import com.example.dell.bonimusic.modle.Bean.SearchBean;
import com.example.dell.bonimusic.presenter.SearchPresenter;
import com.example.dell.bonimusic.view.adapter.SearchAdapter;
import com.example.dell.bonimusic.view.interfaces.MainviewP_V;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements MainviewP_V{

    private ImageView imgback;
    private ImageView imgsousuo;
    private RelativeLayout relativeLayout;
    private EditText et;
  private SearchPresenter searchPresenter=new SearchPresenter();
    private RecyclerView rv;
private SearchAdapter ad;
    private SearchBean searchBean;
    private LinearLayout gonell;
    private TextView tv;
    private RelativeLayout r1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //初始化数据
        initview();
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgsousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setVisibility(View.VISIBLE);
                //获取输入框的数据
                String s = et.getText().toString();
                searchPresenter.searchshuju(s);
                searchPresenter.search(SearchActivity.this);

            }
        });
    }
    private void initview() {
        r1 = (RelativeLayout) findViewById(R.id.rl);
        tv = (TextView) findViewById(R.id.tv);
        imgback = (ImageView) findViewById(R.id.imgback);
        gonell = (LinearLayout) findViewById(R.id.gonell);
        rv = (RecyclerView) findViewById(R.id.rv);
        relativeLayout = (RelativeLayout) findViewById(R.id.imgll);
        et = (EditText) findViewById(R.id.et);
        imgsousuo = (ImageView) findViewById(R.id.imgsousuo);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void Succeed(Object o) {
        searchBean = (SearchBean) o;
        List<SearchBean.SongBean> song = searchBean.getSong();
        //判断集合是否有数据
        if(song!=null){
            //隐藏
           gonell.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
           ad=new SearchAdapter(SearchActivity.this,searchBean.getSong());
           rv.setAdapter(ad);

       }else{
            //显示
           tv.setVisibility(View.VISIBLE);
            //隐藏
           gonell.setVisibility(View.GONE);
            rv.setVisibility(View.GONE);
       }
    }
}
