package com.example.yangl.androidsample.touchEvent.myBehavor;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/29
 * version:
 * update:
 */
public class RecyclerViewBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
    private final String TAG = "RecyclerViewBehavior";

    public RecyclerViewBehavior() {
    }

    public RecyclerViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        //计算列表y坐标，最小为0
        float y = dependency.getHeight() + dependency.getTranslationY();
        if (y < 0) {
            y = 0;
        }
        Log.d(TAG, "onDependentViewChanged: dependency.getTranslationY() = "+dependency.getTranslationY());
        Log.d(TAG, "onDependentViewChanged: y = "+y);
        child.setY(y);
        return true;
    }
}
