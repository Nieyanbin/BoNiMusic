package com.example.dell.bonimusic.presenter;


import android.util.Log;
import android.view.View;

import com.example.dell.bonimusic.modle.Bean.SearchBean;
import com.example.dell.bonimusic.modle.Httputils;
import com.example.dell.bonimusic.view.interfaces.MainviewM_P;
import com.example.dell.bonimusic.view.interfaces.MainviewP_V;

/**
 * Created by dell on 2017/10/9.
 */
public class SearchPresenter implements MainviewM_P{
private  MainviewP_V mainviewP_V;
private Httputils httputils=new Httputils();
    public void searchshuju(String s) {
       httputils.initGetBeanM_P(this);
     httputils.getsearch(s, SearchBean.class);

    }

    public void search(MainviewP_V mainviewP_V) {
this.mainviewP_V=mainviewP_V;
    }
    @Override
    public void Succeed(Object o) {
        SearchBean searchBean=  (SearchBean) o;
        mainviewP_V.Succeed(searchBean);
    }


}
