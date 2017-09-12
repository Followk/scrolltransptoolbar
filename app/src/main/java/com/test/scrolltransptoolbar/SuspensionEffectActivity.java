package com.test.scrolltransptoolbar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

/**
 * ScrollingTricks的效果
 * Created by Administrator on 2017/8/29.
 */
public class SuspensionEffectActivity extends Activity implements GradationScrollView.ScrollViewListener {

    private GradationScrollView scrollview;
    private RelativeLayout top_rl;
    private RelativeLayout top_rl1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspension_effect);


        findViewById();
    }

    private void findViewById() {
        scrollview = (GradationScrollView) findViewById(R.id.scrollview);
        top_rl = (RelativeLayout) findViewById(R.id.top_rl);
        top_rl1 = (RelativeLayout) findViewById(R.id.top_rl1);
        scrollview.setScrollViewListener(this);
        top_rl.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("onGlobalLayout","onGlobalLayout++");
                onScrollChanged(null,0,scrollview.getScrollY(),0,0);
                top_rl.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        Log.i("onScrollChanged","y++"+y);
        int mBuyLayout2ParentTop = Math.max(y, top_rl.getTop());

        top_rl1.setTranslationY(mBuyLayout2ParentTop);
        //top_rl1.layout(0,mBuyLayout2ParentTop,top_rl1.getWidth(),mBuyLayout2ParentTop+top_rl1.getHeight());
    }
}
