package com.test.scrolltransptoolbar;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class JinDongKuaiBaoView extends ViewFlipper implements View.OnClickListener, View.OnTouchListener {
    private Context context;
    private List<String> mNotices;
    public final static int SCROLL_TYPE_VERTICAL = 0;
    public final static int SCROLL_TYPE_HORIZONTAL = 1;
    private GestureDetector mGestureDetector;
    public static final int FLING_MIN_DISTANCE = 80;
    public static final int FLING_MIN_VELOCITY = 120;
    private boolean  isFling=false;


    public JinDongKuaiBaoView(Context context) {
        super(context);
    }

    Handler  handler=new Handler();
    public JinDongKuaiBaoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
       // mGestureDetector = new GestureDetector(new simpleGestureListener());
        this.context = context;
        // 轮播间隔时间为3s
        setFlipInterval(3000);
        // 内边距5dp
        setPadding(dp2px(5f), dp2px(5f), dp2px(5f), dp2px(5f));

          setScrollType(SCROLL_TYPE_VERTICAL);

       // setOnTouchListener(this);
    }


    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                context.getResources().getDisplayMetrics());
    }

    public void setScrollType(int type) {
        clearAnimation();
        switch (type) {
            case SCROLL_TYPE_VERTICAL://垂直滚动动画设置
                // 设置enter和leave动画
                setInAnimation(AnimationUtils.loadAnimation(context, R.anim.notify_in));
                setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.notify_out));
                break;
            case SCROLL_TYPE_HORIZONTAL://水平滚动动画设置
                setInAnimation(AnimationUtils.loadAnimation(context, R.anim.horizontal_notify_in));
                setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.horizontal_notify_out));

                break;
            default:
                break;
        }
    }


    /**
     * 添加需要轮播展示的公告
     *
     * @param notices
     */
    public void addNotice(List<String> notices) {

        this.mNotices = notices;
        removeAllViews();
        for (int i = 0; i < mNotices.size(); i++) {
            // 根据公告内容构建一个TextView
            String notice = notices.get(i);
            TextView textView = new TextView(context);
            textView.setSingleLine();
            textView.setText(notice);
            textView.setTextSize(13f);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(Color.parseColor("#666666"));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            // 将公告的位置设置为textView的tag方便点击是回调给用户
            textView.setTag(i);
             textView.setOnClickListener(this);
            // 添加到ViewFlipper
            JinDongKuaiBaoView.this.addView(textView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        }
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        String notice = (String) mNotices.get(position);
        if (mOnNoticeClickListener != null) {
            mOnNoticeClickListener.onNotieClick(position, notice);
        }
    }


//    private void ToRightAnimation() {
//        clearAnimation();
//        setInAnimation(AnimationUtils.loadAnimation(context, R.anim.right_notify_in));
//        setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.right_notify_out));
//    }
//
//    private void ToLeftAnimation() {
//        clearAnimation();
//        setInAnimation(AnimationUtils.loadAnimation(context, R.anim.horizontal_notify_in));
//        setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.horizontal_notify_out));
//    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return true ;
    }

    /**
     * 通知点击监听接口
     */
    public interface OnNoticeClickListener {
        void onNotieClick(int position, String notice);
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


//    private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            isFling=true;
//            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
//                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
//                // 当像左侧滑动的时候
//                //设置View进入屏幕时候使用的动画
//                //设置View退出屏幕时候使用的动画
//                 ToLeftAnimation();
//                showNext();
//            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
//                    && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
//                // 当像右侧滑动的时候
//                ToRightAnimation();
//                showPrevious();
//            }
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    startFlipping();
//                }
//            },0);
//
//            return true;
//        }
//    }
}
