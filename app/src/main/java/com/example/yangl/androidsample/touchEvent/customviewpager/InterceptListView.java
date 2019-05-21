package com.example.yangl.androidsample.touchEvent.customviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/22
 * version:
 * update:
 */
public class InterceptListView extends ListView {

    private String TAG = "InterceptListView";

    public InterceptListView(Context context) {
        super(context);
    }

    public InterceptListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent: down");break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent: move");break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent: up");break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onInterceptTouchEvent: cancel");break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: down");break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: move");break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: up");break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent: cancel");break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: down");break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: move");break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: up");break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent: canncel");break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }
}
