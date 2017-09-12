package com.test.scrolltransptoolbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class ViewFlipperRollActivity extends Activity implements ViewFlipperRollView.OnNoticeClickListener {

    private List<ImgBean> imgBeens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewflipper_roll);
        ViewFlipperRollView  vf = (ViewFlipperRollView) findViewById(R.id.vf);

        imgBeens = getList();
        vf.setAdapter(new MyAdater());
        vf.setOnNoticeClickListener(this);
    }

    @Override
    public void onNotieClick(int position) {
        Toast.makeText(ViewFlipperRollActivity.this,"position"+position,Toast.LENGTH_LONG).show();
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


    private class MyAdater implements ViewFlipperRollView.ViewFlipperRollAdater {
        @Override
        public int getCount() {
            return imgBeens.size();
        }

        @Override
        public View getView(int i, ViewFlipperRollView viewFlipperRollView, LayoutInflater inflater) {
           View  view= inflater.inflate(R.layout.item_vf,null);
          ImageView  iv= (ImageView) view.findViewById(R.id.iv);
           TextView  tv= (TextView) view.findViewById(R.id.tv);
            ImgBean  imgBean=imgBeens.get(i);
            iv.setBackgroundResource(imgBean.img);
            tv.setText(imgBean.shuoming);
            return view;
        }
    }
}
