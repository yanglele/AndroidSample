package com.example.yangl.androidsample.touchEvent.outsideIntercept2;

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
public class InterceptViewGroup2 extends LinearLayout {

    private String TAG = "outsideIntercept2.InterceptViewGroup2";

    private boolean canIntercept;

    public InterceptViewGroup2(Context context) {
        super(context);
    }

    public InterceptViewGroup2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptViewGroup2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent: down");
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent: move");
                return false;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent: up");
                return false;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onInterceptTouchEvent: cancel");
            default:
                return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: down");break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: up");break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: move");break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent: cancel");break;
                default:break;
        }
        return false;
    }

//    public boolean diapatchTpuchEvent(MotionEvent ev){
//        boolean consume = false;
//        if(onInterceptTouchEvent(ev)){
//            consume = onTouchEvent(ev);
//        }else {
//            consume = child.diapatchTouchEvent(ev);
//        }
//        return consume;
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d(TAG, "dispatchTouchEvent: return false");
//        return false;
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: down");break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: up");break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: move");break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent: cancel");break;
            default:break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setCanIntercept(boolean canIntercept) {
        this.canIntercept = canIntercept;
    }
}
