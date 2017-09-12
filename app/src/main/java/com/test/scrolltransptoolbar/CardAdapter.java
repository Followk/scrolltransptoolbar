package com.test.scrolltransptoolbar;

import android.support.v7.widget.CardView;

/**
 * Created by Administrator on 2017/9/12.
 * 卡片接口类
 */

public interface CardAdapter  {

    //数值越大间距越大
    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);


    int getCount();
}
