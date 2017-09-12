package com.test.scrolltransptoolbar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */
public class RecyclerviewActivity extends Activity {

    private RecyclerView recyview;
    private Activity context;
    private List<String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = RecyclerviewActivity.this;
        setContentView(R.layout.activity_recyclerview);

        getListData();
        recyview = (RecyclerView) findViewById(R.id.recyview);
        recyview.setLayoutManager(new LinearLayoutManager(context));
        recyview.setAdapter(new MyAdater());
    }

    public List<String> getListData() {
        listData = new ArrayList<>();

        for (int i=0;i<50;i++)
        {
            listData.add("00"+i);
        }


        return listData;
    }

    private class MyAdater extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          //View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent,false);
            View view = LayoutInflater.from(context).inflate(R.layout.item_view,null);
            MyViewHOlder   viewHOlder=new MyViewHOlder(view);
            return viewHOlder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHOlder  myViewHOlder=  (MyViewHOlder)holder;
            String  str =  listData.get(position);
            myViewHOlder.context_tv.setText(str);
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }


    }

    private class MyViewHOlder extends RecyclerView.ViewHolder {

        public final TextView context_tv;

        public MyViewHOlder(View itemView) {
            super(itemView);
            context_tv = (TextView) itemView.findViewById(R.id.context_tv);
        }
    }
}
