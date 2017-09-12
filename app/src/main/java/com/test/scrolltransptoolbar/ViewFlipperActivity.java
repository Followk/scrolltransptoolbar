package com.test.scrolltransptoolbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */
public class ViewFlipperActivity  extends Activity implements JinDongKuaiBaoView.OnNoticeClickListener {

    private JinDongKuaiBaoView jinDongKuaiBaoView;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper);
        jinDongKuaiBaoView = (JinDongKuaiBaoView) findViewById(R.id.jindongkuaibaoview);
        List<String> notices = new ArrayList<>();
        notices.add("大促销下单拆福袋，亿万新年红包随便拿");
        notices.add("家电五折团，抢十亿无门槛现金红包");
        notices.add("星球大战剃须刀首发送200元代金券");
        jinDongKuaiBaoView.setScrollType(JinDongKuaiBaoView.SCROLL_TYPE_HORIZONTAL);
        jinDongKuaiBaoView.addNotice(notices);
        jinDongKuaiBaoView.startFlipping();
        jinDongKuaiBaoView.setOnNoticeClickListener(this);


    }


    @Override
    public void onNotieClick(int position, String notice) {

        Toast.makeText(ViewFlipperActivity.this,"position"+position,Toast.LENGTH_LONG).show();
    }


}
