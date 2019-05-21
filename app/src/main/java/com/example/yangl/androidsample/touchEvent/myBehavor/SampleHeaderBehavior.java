package com.example.yangl.androidsample.touchEvent.myBehavor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Desc:滑动recyclerView，textView需要消耗滑动从而移动,recyclerview不移动
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/29
 * version:
 * update:
 */
public class SampleHeaderBehavior extends CoordinatorLayout.Behavior<TextView> {

    private final String TAG = "SampleHeaderBehavior";

    public SampleHeaderBehavior() {
    }

    public SampleHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, TextView child, MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull TextView child, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull TextView textView, @NonNull View recyclerView, int dx, int dy,
                                  @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, textView, recyclerView, dx, dy, consumed, type);
        if (recyclerView instanceof RecyclerView) {
            //recyclerView是否可以滑动
            if(canScroll(textView,dy,recyclerView)){
                float finalY = textView.getTranslationY() - dy;//dy为移动分量
                Log.d(TAG, "onNestedPreScroll: can finalY = "+finalY);
                if (dy < -textView.getHeight()) {
                    dy = -textView.getHeight();
                } else if (dy > textView.getHeight()) {
                    dy = textView.getHeight();
                }
                textView.setTranslationY(finalY);
                consumed[1] = dy;
            }
        }
    }

    private boolean canScroll(View child ,int dy,View recyclerView){
        if(dy > 0){
            //上滑
            Log.d(TAG, "canScroll->up:dy = "+dy+"  getY = "+recyclerView.getY());
            if(recyclerView.getY() > 0){
                return true;
            }else {
                return false;
            }
        }else {
            //下滑
            Log.d(TAG, "canScroll->down:dy = "+dy+"  getY = "+recyclerView.getY());
            if(recyclerView.getY() < child.getHeight()){
                return true;
            }else {
                return false;
            }
        }
    }

}
