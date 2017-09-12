package com.test.scrolltransptoolbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 * ViewPager  PagerPageTransformer学习
 */
public class ViewPagerPageTransformerActivity extends Activity {

    private ViewPager vp;
    private List<ImgBean> imgBeens;
    private List<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_transformer);
        vp = (ViewPager) findViewById(R.id.vp);
        imgBeens = getList();
        imageViews = getListViews();
        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgBeens.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageViews.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imageViews.get(position));
                return imageViews.get(position);
            }
        });


        //vp.setPageTransformer(true, new DepthPageTransformer());
//        vp.setPageTransformer(true, new My3DTransformer());
        vp.setPageTransformer(true, new RotateDownPageTransformer());

    }




    public List<ImgBean> getList() {
        List<ImgBean>  imgBeen=new ArrayList<>();
        imgBeen.add(new ImgBean(R.mipmap.img1,"美女"));
        imgBeen.add(new ImgBean(R.mipmap.img2,"西斯美女"));
        imgBeen.add(new ImgBean(R.mipmap.img3,"漂亮美女"));
        imgBeen.add(new ImgBean(R.mipmap.img4,"混血美女"));
        imgBeen.add(new ImgBean(R.mipmap.img5,"韩国美女"));
        imgBeen.add(new ImgBean(R.mipmap.img6,"日本美女"));

        return imgBeen;
    }

    public List<ImageView> getListViews() {
        List<ImageView>  imageViews=new ArrayList<>();
        for (int i = 0; i <imgBeens.size() ; i++) {
            ImageView  imageView=new ImageView(getApplicationContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imgBeens.get(i).img);
            imageView.setTag(i);//设置标记
            imageViews.add(imageView);
        }

        return imageViews;
    }
}
