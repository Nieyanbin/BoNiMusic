package com.example.dell.bonimusic.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.dell.bonimusic.R;

public class WebViewActivity extends AppCompatActivity {
private WebView webView;
    private ProgressBar pg1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //传值
        Intent intent = getIntent();
        pg1=(ProgressBar) findViewById(R.id.progressBar1);
        //接收值
        String web = intent.getStringExtra("web");
        webView = (WebView) findViewById(R.id.webView);
        //网络
        webView.loadUrl(web);
        //设置进度条
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根
               //判断
                if(newProgress==100){
                    pg1.setVisibility(View.GONE);//加载完网页进度条消失
                }
                else{
                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pg1.setProgress(newProgress);//设置进度值
                }
            }
        });
    }
}
