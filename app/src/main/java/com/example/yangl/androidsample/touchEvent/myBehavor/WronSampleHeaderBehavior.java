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

import static android.content.ContentValues.TAG;

/**
 * Desc:滑动recyclerView，textView需要消耗滑动从而移动,recyclerview不移动
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/29
 * version:
 * update:
 */
public class WronSampleHeaderBehavior extends CoordinatorLayout.Behavior<TextView> {

    private final String TAG = "SampleHeaderBehavior";

    // 界面整体向上滑动，达到列表可滑动的临界点
    private boolean upReach;
    // 列表向上滑动后，再向下滑动，达到界面整体可滑动的临界点
    private boolean downReach;
    // 列表上一个全部可见的item位置
    private int lastPosition = -1;

    private int lastDy = 0;

    public WronSampleHeaderBehavior() {
    }

    public WronSampleHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, TextView child, MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downReach = false;
                upReach = false;
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
                                  @NonNull TextView child, @NonNull View target, int dx, int dy,
                                  @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if (target instanceof RecyclerView) {

            if (canScroll(child, dy, target)) {
                float finalY = child.getTranslationY() - dy;
                if (finalY < -child.getHeight()) {
                    finalY = -child.getHeight();
                } else if (finalY > 0) {
                    finalY = 0;
                }
                child.setTranslationY(finalY);
                consumed[1] = dy;
                Log.d(TAG, "consumed: " + consumed[1]);
            }
        }
    }

    private boolean canScroll(View child, float dy, View target) {
        if (dy > 0) {
            Log.d(TAG, "canScroll->up:dy = "+dy+"  getY = "+target.getY());
            if (child.getTranslationY() == -child.getHeight()) {
                return false;
            } else {
                return true;
            }
        } else {
            Log.d(TAG, "canScroll->down:dy = "+dy+"  getY = "+target.getY());
            RecyclerView list = (RecyclerView) target;
            int pos = ((LinearLayoutManager) list.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
            if (pos == 0) {
                return true;
            } else {
                return false;
            }
        }
}
}
