package com.example.yangl.androidsample.view.innerIntercept;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/17
 * version:
 * update:
 */
public class InterceptViewGroup extends LinearLayout {

    private boolean canIntercept;

    public InterceptViewGroup(Context context) {
        super(context);
    }

    public InterceptViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            return false;
        }else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

}
