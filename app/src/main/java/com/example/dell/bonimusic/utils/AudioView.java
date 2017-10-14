package com.example.dell.bonimusic.utils;

/**
 * Created by dell on 2017/10/8.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class AudioView extends View{

    private int width;
    private int height;
    private int rectCount = 10;
    private Paint paint;
    private int offset;
    private int rectWidth;

    public AudioView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画矩形
        for (int i=0;i<rectCount;i++) {
            canvas.drawRect((float)(width*0.1+rectWidth*i+offset), (float)getRectHight(), (float)(width*0.1+rectWidth*(i+1)), (float)height, paint);
        }

        postInvalidateDelayed(300);//300毫秒刷新
    }


    private double getRectHight() {
        double h = 0;
        h = Math.random()*height;
        return h;
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        rectWidth = width/15;
        offset = width/30;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = measureWidth(widthMeasureSpec);//得到View的宽
        height = measureHight(heightMeasureSpec);//得到View的高
        setMeasuredDimension(width,height);
        init();//初始化
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHight(int heightMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}
