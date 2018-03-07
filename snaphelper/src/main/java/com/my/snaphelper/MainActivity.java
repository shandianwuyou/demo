package com.my.snaphelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(new MyAdapter(this));
//        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper(){
//            @Nullable
//            @Override
//            public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
//                int[] out = new int[2];
//                out[1] = targetView.getHeight();
//                return out;
//            }
//        };
//        pagerSnapHelper.attachToRecyclerView(mRecyclerView);
        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.TOP);
        snapHelperStart.attachToRecyclerView(mRecyclerView);

    }
}
