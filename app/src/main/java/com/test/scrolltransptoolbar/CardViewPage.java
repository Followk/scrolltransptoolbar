package com.test.scrolltransptoolbar;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/9/12.
 */

public class CardViewPage  extends ViewPager{
    private   float  mLastOffset;
    private CardAdapter cardAdapter;

    public CardViewPage(Context context) {
        super(context);
    }

    public CardViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onPageScrolled(int position, float positionOffset, int offsetPixels) {
        super.onPageScrolled(position, positionOffset, offsetPixels);
        cardAdapter = (CardAdapter) getAdapter();
        if (cardAdapter ==null)
        {
            return;
        }

        // If we're going backwards, onPageScrolled receives the last position
        // instead of the current one
        int realCurrentPosition;
        int nextPosition;
        float realOffset;
        //positionOffset  如果往左边滑动就是逐渐变大  0->1 ，然后归0，如果往右滑动  1-》0  ，最后归0。
        //下面这个判断区分左右，
        boolean goingLeft = mLastOffset > positionOffset;
        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        if (nextPosition > getAdapter().getCount() - 1
                || realCurrentPosition > cardAdapter.getCount() - 1) {
            return;
        }
        CardView currentCard = cardAdapter.getCardViewAt(realCurrentPosition);
        if (currentCard!=null)
        {
            float  scclex=(float) (1 + 0.1 * (1 - realOffset));
            float  sccley=(float)(1 + 0.1 * (1 - realOffset));
            currentCard.setScaleX(scclex);
            currentCard.setScaleY(sccley);
            currentCard.setCardElevation((cardAdapter.getBaseElevation() + cardAdapter.getBaseElevation()
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));

        }



        CardView nextCard = cardAdapter.getCardViewAt(nextPosition);

        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (nextCard != null) {
            nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
            nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
            nextCard.setCardElevation((cardAdapter.getBaseElevation() + cardAdapter.getBaseElevation()
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
        }

        mLastOffset = positionOffset;


    }




}
