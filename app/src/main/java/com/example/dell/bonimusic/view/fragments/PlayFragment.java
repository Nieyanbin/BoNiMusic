package com.example.dell.bonimusic.view.fragments;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.bonimusic.R;

/**
 * Created by dell on 2017/10/11.
 */
public class PlayFragment extends Fragment{
    private static ImageView imgxz;
    private static ImageView imgxzg;
    private View view;
    private ObjectAnimator animator;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_pager_list,null);
        //初始化
        initview();

        return view;
    }
    //初始化
    private void initview() {
        imgxz = view.findViewById(R.id.imgxz);
        imgxzg=view.findViewById(R.id.imgxzg);
    }
   //添加的动画
    public void donghua() {
        //音乐棍动画
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgxzg, "rotation", 0f, 10f);
        imgxzg.setPivotX(0);
        imgxzg.setPivotY(0);
        animator1.setDuration(2000);
        animator1.start();
        //音乐圆盘动画
        animator = ObjectAnimator.ofFloat(imgxz, "rotation", 0f, 360f);
        animator.setDuration(5000);
        animator.setRepeatCount(-1);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
    }
   //动画使音乐棍移开
    public void fandonghua() {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgxzg, "rotation", 10f, 0f);
        animator1.setDuration(2000);
        animator1.start();
        animator.end();
    }
}
