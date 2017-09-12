package com.test.scrolltransptoolbar;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2017/9/8.
 */

public class My3DTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            page.setAlpha(1);
            page.setPivotX( page.getWidth());
            page.setPivotY(page.getHeight()*0.5f);
            page.setRotationY(position*90);

        } else if (position <= 1) { // (0,1]
            page.setAlpha(1);
            page.setPivotX(0f);
            page.setPivotY(page.getHeight()*0.5f);
            page.setRotationY(position*90);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(0);
        }

    }
}
