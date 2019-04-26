package com.example.yangl.androidsample.touchEvent;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/4/2
 * version:
 * update:
 */
public class MyScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {

    public MyScrollingViewBehavior() {
    }

    public MyScrollingViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {
        return true;
    }
}
