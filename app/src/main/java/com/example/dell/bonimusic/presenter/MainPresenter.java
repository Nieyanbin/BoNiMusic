package com.example.dell.bonimusic.presenter;

import android.util.Log;

import com.example.dell.bonimusic.modle.Bean.MusicBean;
import com.example.dell.bonimusic.modle.Httputils;
import com.example.dell.bonimusic.utils.Jiekou;
import com.example.dell.bonimusic.view.activity.ParticularsActivity;
import com.example.dell.bonimusic.view.fragments.OnlineMusicFragment;
import com.example.dell.bonimusic.view.interfaces.MainviewM_P;
import com.example.dell.bonimusic.view.interfaces.MainviewP_V;
import com.nostra13.universalimageloader.utils.L;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by dell on 2017/9/28.
 */
public class MainPresenter implements MainviewM_P {
    private MainviewP_V mainviewP_v;
   Httputils httputils=new Httputils();
    private Jiekou api=new Jiekou();
    private MusicBean musicBean;

    //加载数据
    public void loadview(String type) {
        httputils.initGetBeanM_P(this);
        Map<String, String> map = new HashMap<>();
        map.put(api.PARAM_METHOD, "baidu.ting.billboard.billList");
        map.put(api.PARAM_TYPE, type);
        map.put(api.PARAM_SIZE, "100");
        map.put(api.PARAM_OFFSET, "0");
        httputils.getHead(api.BASE_URL, map, MusicBean.class);
    }
    public void initGetBeanP_V(MainviewP_V mainviewP_v) {
        this.mainviewP_v=mainviewP_v;
    }
    @Override
    public void Succeed(Object o) {
        musicBean = (MusicBean) o;
        Log.d("Succeed", musicBean.getSong_list().get(0).getTitle());
        mainviewP_v.Succeed(o);
    }
//    public void gain(ParticularsActivity particularsActivity, String type) {
//        httputils.initGetBeanM_P(this);
//        httputils.getjiexi(type,MusicBean.class);
//    }

}
