package com.test.scrolltransptoolbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
public class ViewPagerCardViewActivity extends Activity {
    private  List<CardView>   views=new ArrayList<>();
    private List<ImgBean> imgBeens;
    private MyAdapter adapter;
    private CardViewPage viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_card_view);

       findViewById();
    }


    //初始化控件
    private void findViewById() {
        viewPager = (CardViewPage) findViewById(R.id.vp);
        imgBeens = getList();

        adapter = new MyAdapter();
        viewPager.setAdapter(adapter);
        //预加载3页
        viewPager.setOffscreenPageLimit(3);
    }


    /**
     * 初始化数据
     * @return
     */
    public List<ImgBean> getList() {
        List<ImgBean>  imgBeen=new ArrayList<>();
        views.add(null);
        views.add(null);
        views.add(null);
        views.add(null);
        views.add(null);
        imgBeen.add(new ImgBean(R.mipmap.img2,"西斯美女"));
        imgBeen.add(new ImgBean(R.mipmap.img3,"漂亮美女"));
        imgBeen.add(new ImgBean(R.mipmap.img4,"混血美女"));
        imgBeen.add(new ImgBean(R.mipmap.img5,"韩国美女"));
        imgBeen.add(new ImgBean(R.mipmap.img6,"日本美女"));

        return imgBeen;
    }




    private  class  MyAdapter extends PagerAdapter implements CardAdapter{
        private float mBaseElevation;
        @Override
        public int getCount() {
            return imgBeens.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//固定写法
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);//固定写法

            //当前卡片消失就移除
            views.set(position,null);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View  view= LayoutInflater.from(container.getContext()).inflate(R.layout.item_vp_view,container,false);
            container.addView(view);//必须这么写，要不然显示不出来
            CardView card_view  = (CardView) view.findViewById(R.id.card_view);
            ImageView  img= (ImageView) view.findViewById(R.id.img);
            TextView context= (TextView) view.findViewById(R.id.context);
            ImgBean  imgBean=  imgBeens.get(position);
            img.setImageResource(imgBean.getImg());
            context.setText(imgBean.getShuoming());

            if (mBaseElevation == 0) {
                mBaseElevation = card_view.getCardElevation();//获取卡片阴影
                Log.i("mBaseElevation","mBaseElevation=="+mBaseElevation);
            }

//            设置z轴的最大高度值
            card_view.setMaxCardElevation(mBaseElevation*MAX_ELEVATION_FACTOR);

            //保留每一个卡片实体类
            views.set(position, card_view);

            view.setTag(position);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                int position= (int) v.getTag();

                        viewPager.setCurrentItem(position);


                }
            });
            return view;
        }

        @Override
        public float getBaseElevation() {
            return  mBaseElevation;
        }

        @Override
        public CardView getCardViewAt(int position) {
            return views.get(position);
        }
    }
}
