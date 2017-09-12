package com.test.scrolltransptoolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

/**
 * Created by Administrator on 2017/9/5.
 */

public class ViewFlipperRollView extends ViewFlipper implements View.OnTouchListener, View.OnClickListener {
    private Context context;
    private GestureDetector mGestureDetector;
    private ViewFlipperRollAdater adapter;
    private LayoutInflater inflater;
    public static final int FLING_MIN_DISTANCE = 80;
    public static final int FLING_MIN_VELOCITY = 120;
    private  boolean  isFlag=false;
    public ViewFlipperRollView(Context context) {
        super(context);
    }

    public ViewFlipperRollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        inflater = LayoutInflater.from(context);
        mGestureDetector = new GestureDetector(new simpleGestureListener());


        setOnTouchListener(this);
    }

    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                context.getResources().getDisplayMetrics());
    }

    public void setAdapter(ViewFlipperRollAdater adapter) {
        this.adapter = adapter;

        if (adapter != null) {
            removeAllViews();
            int count = adapter.getCount();
            for (int i = 0; i < count; i++) {
                View view = adapter.getView(i, this,inflater);
                view.setOnClickListener(this);
                view.setTag(i);
                // 添加到ViewFlipper
                ViewFlipperRollView.this.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
            }
        }



    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouchEvent(event);
        return   mGestureDetector.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
       int position= (int) v.getTag();
        if (mOnNoticeClickListener != null) {
            if (!isFlag)
            {
                mOnNoticeClickListener.onNotieClick(position);
            }else {
                isFlag=false;
            }

        }
    }




        private void ToRightAnimation() {
        clearAnimation();
        setInAnimation(AnimationUtils.loadAnimation(context, R.anim.right_notify_in));
        setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.right_notify_out));
    }

    private void ToLeftAnimation() {
        clearAnimation();
        setInAnimation(AnimationUtils.loadAnimation(context, R.anim.horizontal_notify_in));
        setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.horizontal_notify_out));
    }
    private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener {



        @Override
        public boolean onDown(MotionEvent e) {
      setClickable(true);
            return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            setClickable(false);
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // 当像左侧滑动的时候
                ToLeftAnimation();
                showNext();
                isFlag=true;
            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // 当像右侧滑动的时候
                ToRightAnimation();
                showPrevious();
                isFlag=true;
            }else {
                isFlag=false;
            }
            return false;
        }
    }

    public interface ViewFlipperRollAdater {
        int getCount();

        View getView(int i, ViewFlipperRollView viewFlipperRollView, LayoutInflater inflater);
    }

    /**
     * 通知点击监听接口
     */
    public interface OnNoticeClickListener {


        void onNotieClick(int position);
    }

    private OnNoticeClickListener mOnNoticeClickListener;
    /**
     * 设置通知点击监听器
     *
     * @param onNoticeClickListener 通知点击监听器
     */
    public void setOnNoticeClickListener(OnNoticeClickListener onNoticeClickListener) {
        mOnNoticeClickListener = onNoticeClickListener;
    }

    public boolean dispatchTouchEvent(MotionEvent ev){
        //先执行滑屏事件
        mGestureDetector.onTouchEvent(ev);
        super.dispatchTouchEvent(ev);
        return true;
    }
}
