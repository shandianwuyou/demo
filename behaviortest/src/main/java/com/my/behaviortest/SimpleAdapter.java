package com.my.behaviortest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhaopeng on 2018/3/6.
 */

public class SimpleAdapter extends RecyclerView.Adapter {

    private String[] names = new String[]{
            "路飞",
            "香吉",
            "索隆",
            "乔巴",
            "娜美",
            "罗宾",
            "乌索普",
            "甚平"
    };

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position < names.length){
            MyViewHolder mHolder = (MyViewHolder) holder;
            mHolder.mTextView.setText(names[position]);
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.item_text);
        }
    }
}
