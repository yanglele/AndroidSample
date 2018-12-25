package com.example.yangl.androidsample.touchEvent.outsideIntercect;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
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

    private String TAG = "InterceptViewGroup";

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
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent: down");
                return false;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent: up");
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent: move");
                if (canIntercept) {
                    return true;
                } else {
                    return false;
                }
//                return false;
            default:
                return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: down");break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: up");break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: move");break;
            default:
                break;
        }
        return true;
//        return true;
//        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: down");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: move");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setCanIntercept(boolean canIntercept) {
        this.canIntercept = canIntercept;
    }
}
