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
        final float shouldMoveY = getY() + dy;

        // 获取到父View的容器的引用，这里假定父View容器是View
        final View parent = (View) getParent();

        int consumedY;
        // 如果超过了父View的上边界，只消费子View到父View上边的距离
        //向上滑<=100 不消耗，或者向下滑>100不消耗
        if ((shouldMoveY <= MARGIN_TOP && dy < 0) || (dy > 0  && shouldMoveY >= MARGIN_TOP)) {
            //0
            consumedY = 0;
        } else if (shouldMoveY >= parent.getHeight() - getHeight()) {
            // 如果超过了父View的下边界，只消费子View到父View
            //0
            consumedY = (int) (parent.getHeight() - getHeight() - getY());
        } else {
            // 其他情况下全部消费
            consumedY = dy;
        }

        // 对父View进行移动
        setY(getY() + consumedY);

        // 将父View消费掉的放入consumed数组中
        consumed[1] = consumedY;

        Log.d(TAG, String.format("onNestedPreScroll, dx = %d, dy = %d, consumed = %s", dx, dy, Arrays.toString(consumed)));
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
