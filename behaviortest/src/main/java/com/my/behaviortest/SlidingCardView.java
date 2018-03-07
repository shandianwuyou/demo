package com.my.behaviortest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by zhaopeng on 2018/3/2.
 */

@CoordinatorLayout.DefaultBehavior(SlidingCardBehavior.class)
public class SlidingCardView extends FrameLayout {

    private int mHeaderViewHeight;

    public SlidingCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.card_layout, this);

        RecyclerView recyclerView = findViewById(R.id.card_recyclerview);
        TextView textView = findViewById(R.id.card_text);

        recyclerView.setAdapter(new SimpleAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildViewHolder(view).getAdapterPosition();
                outRect.set(30, position == 0?30:0, 30, 30);
            }
        });

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingCardView);
        int color = a.getColor(R.styleable.SlidingCardView_android_colorBackground, Color.BLACK);
        CharSequence title = a.getText(R.styleable.SlidingCardView_android_text);
        textView.setText(title);
        textView.setBackgroundColor(color);
        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w != oldw || h != oldh){
            mHeaderViewHeight = findViewById(R.id.card_text).getMeasuredHeight();
        }
    }

    public int getHeaderViewHeight(){
        return mHeaderViewHeight;
    }
}
