package com.my.behaviortest;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;

/**
 * Created by zhaopeng on 2018/3/6.
 */

public class SlidingCardBehavior extends CoordinatorLayout.Behavior<SlidingCardView> {

    private static final String TAG = "我的消息";
    private int mInitialOffset;

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, SlidingCardView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        int offset = (parent.getChildCount() - 1) * child.getHeaderViewHeight();
        int height = View.MeasureSpec.getSize(parentHeightMeasureSpec) - offset;
        Log.i(TAG, "onMeasureChild: " + height);
        child.measure(parentWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        return true;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, SlidingCardView child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
//        SlidingCardView previous = getPreviousView(parent, child);
        int index = parent.indexOfChild(child);
        child.offsetTopAndBottom(index * child.getHeaderViewHeight());
        mInitialOffset = child.getTop();
        return true;
    }

    private SlidingCardView getPreviousView(CoordinatorLayout parent, SlidingCardView child) {
        int curIndex = parent.indexOfChild(child);
        for(int i = curIndex - 1; i >= 0 ; i--){
            View view = parent.getChildAt(i);
            if(view instanceof SlidingCardView){
                return (SlidingCardView) view;
            }
        }
        return null;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull SlidingCardView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        boolean isVertical = (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
        return isVertical && child == directTargetChild;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout parent, @NonNull SlidingCardView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        consumed[1] = scroll(child, dy, mInitialOffset, mInitialOffset + child.getHeight() - child.getHeaderViewHeight());
        shiftSlide(consumed[1], parent, child);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout parent, @NonNull SlidingCardView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        int shift = scroll(child, dyUnconsumed, mInitialOffset, mInitialOffset + child.getHeight() - child.getHeaderViewHeight());
        shiftSlide(shift, parent, child);
    }

    private void shiftSlide(int shift, CoordinatorLayout parent, SlidingCardView child) {
        if(shift == 0){
            return;
        }

        if(shift > 0){//向上滑
            SlidingCardView curView = child;
            SlidingCardView preView = getPreviousView(parent, curView);
            while (preView != null){
                int offset = preView.getTop() + preView.getHeaderViewHeight() - curView.getTop();
                if(offset > 0){
                    preView.offsetTopAndBottom(-offset);
                }
                curView = preView;
                preView = getPreviousView(parent, curView);
            }
        }else{//向下滑
            SlidingCardView curView = child;
            SlidingCardView nextView = getNextView(parent, curView);
            while (nextView != null){
                int offset = curView.getTop() + curView.getHeaderViewHeight() - nextView.getTop();
                if(offset > 0){
                    nextView.offsetTopAndBottom(offset);
                }

                curView = nextView;
                nextView = getNextView(parent, curView);
            }
        }
    }

    private SlidingCardView getNextView(CoordinatorLayout parent, SlidingCardView child) {
        int curIndex = parent.indexOfChild(child);
        int nextIndex = curIndex + 1;
        if(nextIndex < parent.getChildCount()){
            return (SlidingCardView) parent.getChildAt(nextIndex);
        }
        return null;
    }

    private int scroll(SlidingCardView child, int dyUnconsumed, int min, int max) {
        int dy = child.getTop() - dyUnconsumed;
        if(dy < min){
            dy = min;
        }else if(dy > max){
            dy = max;
        }
        int offset = dy - child.getTop();
        child.offsetTopAndBottom(offset);
        return -offset;
    }
}
