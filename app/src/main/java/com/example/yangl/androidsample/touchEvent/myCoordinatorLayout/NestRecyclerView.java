package com.example.yangl.androidsample.touchEvent.myCoordinatorLayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/26
 * version:
 * update:
 */
public class NestRecyclerView extends RecyclerView {

    private String TAG = "NestRecyclerView";

    public NestRecyclerView(Context context) {
        super(context);
        setNestedScrollingEnabled(true);
    }

    public NestRecyclerView(Context context,@Nullable AttributeSet attrs){
        super(context,attrs);
        setNestedScrollingEnabled(true);

    }

    @Override
    public boolean startNestedScroll(int axes) {
        Log.d(TAG, "startNestedScroll: ");
        return super.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        Log.d(TAG, "stopNestedScroll: ");
        super.stopNestedScroll();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.d(TAG, "onTouchEvent: "+e.getAction());
        return super.onTouchEvent(e);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Log.d(TAG, "dispatchNestedPreScroll: ");
        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }
}
