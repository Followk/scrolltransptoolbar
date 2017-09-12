package com.test.scrolltransptoolbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/25.
 */

public class SorollToolbartranActivity extends Activity implements GradationScrollView.ScrollViewListener {

    private GradationScrollView sl;
    private ImageView iv;
    private int height;
    private Toolbar toolbar;
    private TextView tltle,tv_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_toolbar_tran);

        sl = (GradationScrollView) findViewById(R.id.sl);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tltle = (TextView) findViewById(R.id.tltle);
        iv = (ImageView) findViewById(R.id.iv);
        tv_t= (TextView) findViewById(R.id.tv_t);

        StatusBarUtil.setTranslucentForImageView(this,tv_t);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
        params.height = getScreenHeight(this)*2/3;
        iv.setLayoutParams(params);
        setLister();

    }

    public  int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    private void setLister() {

        ViewTreeObserver  viewTreeObserver= iv.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = iv.getHeight()-toolbar.getHeight();
                iv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                Log.i("onScrollChanged","height="+height);
            }
        });
    sl.setScrollViewListener(this);
    }


    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        Log.i("onScrollChanged","y="+y);
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            toolbar.setBackgroundColor(Color.argb((int) 0, 255,255,255));
            tltle.setTextColor(Color.argb((int) 0, 1,24,28));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            Log.i("onScrollChanged","alpha="+alpha);
            tltle.setTextColor(Color.argb((int) alpha, 1,24,28));
            toolbar.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
        } else {    //滑动到banner下面设置普通颜色
            toolbar.setBackgroundColor(Color.argb((int) 255, 255,255,255));
        }


        //下面这个写法无效不用
//        // TODO Auto-generated method stub
//        if (y <= 0) {   //设置标题的背景颜色
//            toolbar.getBackground().setAlpha(0);
//            tltle.setTextColor(Color.argb((int) 0, 1,24,28));
//        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
//            float scale = (float) y / height;
//            float alpha = (255 * scale);
//            Log.i("onScrollChanged","alpha="+alpha);
//            tltle.setTextColor(Color.argb((int) alpha, 1,24,28));
//            toolbar.getBackground().mutate().setAlpha((int) alpha);
//        } else {    //滑动到banner下面设置普通颜色
//            toolbar.getBackground().mutate().setAlpha(255);
//        }
    }
}
