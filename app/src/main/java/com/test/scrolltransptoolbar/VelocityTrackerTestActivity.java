package com.test.scrolltransptoolbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/24.
 */

public class VelocityTrackerTestActivity extends Activity {

    private int mMaxVelocity;
    private VelocityTracker tracker;
    private TextView textView;
    private int mPointerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMaxVelocity = ViewConfiguration.get(this).getMaximumFlingVelocity();
        Log.i("mMaxVelocity","mMaxVelocity=>"+mMaxVelocity);
        textView = new TextView(this);
        textView.setLines(4);
        textView.setLayoutParams(new  ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setTextColor(Color.BLACK);
        setContentView(textView);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
       int  action= event.getAction();
        if (tracker==null)
        {
            tracker = VelocityTracker.obtain();

        }else {
            tracker.clear();
        }
        tracker.addMovement(event);
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                //求第一个触点的id， 此时可能有多个触点，但至少一个
                mPointerId = event.getPointerId(0);
                break;
            case MotionEvent.ACTION_MOVE:
                tracker.computeCurrentVelocity(1000,mMaxVelocity);
              float x= tracker.getXVelocity(mPointerId);
                float y= (int) tracker.getXVelocity(mPointerId);
                recodeInfo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                releaseVelocityTracker();
            break;
            case MotionEvent.ACTION_CANCEL:
                releaseVelocityTracker();
                break;
            default:
             break;
        }
        return super.onTouchEvent(event);
    }

    private void releaseVelocityTracker() {
        tracker.clear();
        tracker.recycle();
        tracker=null;
    }

     static final String sFormatStr = "velocityX=%f\nvelocityY=%f";


    /**
     * 记录当前速度
     *
     * @param velocityX x轴速度
     * @param velocityY y轴速度
     */
    private void recodeInfo(final float velocityX, final float velocityY) {
        final String info = String.format(sFormatStr, velocityX, velocityY);
        textView.setText(info);
    }
}
