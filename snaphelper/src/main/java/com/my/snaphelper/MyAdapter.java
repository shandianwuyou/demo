package com.my.snaphelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by zhaopeng on 2018/3/6.
 */

public class MyAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public MyAdapter(Context context){
        this.mContext = context;
    }

    private String[] images = new String[]{
        "http://img4.xiazaizhijia.com/walls/20160708/241x139_d6d6c3_2f172c09d079701.jpg",
        "http://img5.xiazaizhijia.com/walls/20151231/241x139_b2bff5_7d58439ee9fd9f3.jpg",
        "http://img4.xiazaizhijia.com/walls/20151217/241x139_617524_be7f9d21931984a.jpg",
        "http://img5.xiazaizhijia.com/walls/20150915/241x139_052398_f7399f90fbde505.jpg",
        "http://pic47.nipic.com/20140902/8607306_233416291000_2.jpg"
    };

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int pos = position % images.length;
        if(holder instanceof MyViewHolder){
            MyViewHolder mHolder = (MyViewHolder) holder;
            mHolder.textView.setText(images[pos]);
            Glide.with(mContext)
                    .load(images[pos])
                    .apply(new RequestOptions().centerCrop())
                    .thumbnail(0.1f)
                    .into(mHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return images.length * 5;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_iamge);
            textView = itemView.findViewById(R.id.item_text);
        }
    }
}
