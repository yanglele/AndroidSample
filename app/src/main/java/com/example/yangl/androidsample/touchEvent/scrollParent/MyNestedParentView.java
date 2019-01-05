package com.example.yangl.androidsample.touchEvent.scrollParent;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.yangl.androidsample.uiTools.UISizeUtils;

import java.util.Arrays;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/27
 * version:
 * update:
 */
public class MyNestedParentView extends FrameLayout implements NestedScrollingParent {


    public static final int MARGIN_TOP = 100;

    public static final String TAG = MyNestedParentView.class.getSimpleName();

    private NestedScrollingParentHelper parentHelper;

    public MyNestedParentView(Context context) {
        super(context);
    }

    public MyNestedParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        parentHelper = new NestedScrollingParentHelper(this);

    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.d(TAG, String.format("onStartNestedScroll, child = %s, target = %s, nestedScrollAxes = %d", child, target, nestedScrollAxes));
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        Log.d(TAG, String.format("onNestedScrollAccepted, child = %s, target = %s, nestedScrollAxes = %d", child, target, nestedScrollAxes));
        parentHelper.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {
        Log.d(TAG, "onStopNestedScroll");
        parentHelper.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        float scrollY = getY() + dyUnconsumed;
        //上滑
        if(scrollY >=0 && scrollY <= ((View)getParent()).getHeight() - getHeight()){
            setY(scrollY);
        }
//        if(scrollY <= MARGIN_TOP){
//        }else if(scrollY+getHeight() <  UISizeUtils.getScreenHeight((Activity) getContext())){
//
//         }
//        //下滑
//        if(scrollY < 0 && scrollY+getHeight() <  UISizeUtils.getScreenHeight((Activity) getContext())){
//            setY(scrollY);
//        }
        Log.d(TAG, String.format("onNestedScroll, dxConsumed = %d, dyConsumed = %d, dxUnconsumed = %d, dyUnconsumed = %d", dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed));
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // 应该移动的Y距离
        final float afterMarginTop = getY() + dy;

        int consumedY;
        // 如果超过了父View的上边界，只消费子View到父View上边的距离
        //向上滑<=100 不消耗，或者向下滑>100不消耗
        if ((afterMarginTop <= MARGIN_TOP && dy < 0) || (dy > 0  && afterMarginTop >= MARGIN_TOP)) {
            //父布局不消耗事件
            consumedY = 0;
        } else {
            // 其他情况下父布局全部消费
            consumedY = dy;
        }

        // 对父View进行移动
        setY(getY() + consumedY);

        // 将父View消费掉的放入consumed数组中
        consumed[1] = consumedY;

        Log.d(TAG, "onNestedPreScroll: ");
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG, String.format("onNestedFling, velocityX = %f, velocityY = %f, consumed = %b", velocityX, velocityY, consumed));
        return true;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.d(TAG, String.format("onNestedPreFling, velocityX = %f, velocityY = %f", velocityX, velocityY));
        return true;
    }

    @Override
    public int getNestedScrollAxes() {
        Log.d(TAG, "getNestedScrollAxes");
        return parentHelper.getNestedScrollAxes();
    }

}
