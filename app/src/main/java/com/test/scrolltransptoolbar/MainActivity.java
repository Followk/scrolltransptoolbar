package com.test.scrolltransptoolbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * https://juejin.im/entry/587c3a911b69e6006bf16e22   参考
 */
public class MainActivity extends AppCompatActivity {

    private TextView next,next1,next2,next3,next4,next5,next6,next7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = (TextView) findViewById(R.id.next);
        next1 = (TextView) findViewById(R.id.next1);
        next2 = (TextView) findViewById(R.id.next2);
        next3 = (TextView) findViewById(R.id.next3);
        next4 = (TextView) findViewById(R.id.next4);
        next5 = (TextView) findViewById(R.id.next5);
        next6 = (TextView) findViewById(R.id.next6);
        next7 = (TextView) findViewById(R.id.next7);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(MainActivity.this,VelocityTrackerTestActivity.class);
                v.getContext().startActivity(intent);
            }
        });


        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(MainActivity.this,SorollToolbartranActivity.class);
                v.getContext().startActivity(intent);
            }
        });


        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(MainActivity.this,SuspensionEffectActivity.class);
                v.getContext().startActivity(intent);
            }
        });


        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(MainActivity.this,RecyclerviewActivity.class);
                v.getContext().startActivity(intent);
            }
        });


        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(MainActivity.this,ViewFlipperActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        next5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(MainActivity.this, ViewFlipperRollActivity .class);
                v.getContext().startActivity(intent);
            }
        });

        next6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(MainActivity.this, ViewPagerPageTransformerActivity .class);
                v.getContext().startActivity(intent);
            }
        });


        next7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(MainActivity.this, ViewPagerCardViewActivity .class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
