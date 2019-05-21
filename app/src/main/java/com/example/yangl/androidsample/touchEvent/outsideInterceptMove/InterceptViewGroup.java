package com.example.yangl.androidsample.touchEvent.outsideInterceptMove;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
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
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent: move");
                if (canIntercept) {
                    return true;
                } else {
                    return false;
                }
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent: up");
                return false;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onInterceptTouchEvent: cancel");
            default:
                return false;
        }
    }

    float downY;

    float lastY = 0;

    boolean firstCome = true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: down "+downY);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: up");break;
            case MotionEvent.ACTION_MOVE:
                int dy = 0;
                if(firstCome){
                    firstCome = !firstCome;
                    dy = 0;
                }else {
                    dy = (int)( event.getRawY() - lastY );
                }
                ((ViewGroup)(getParent())).scrollBy(0,-dy);
                lastY = event.getRawY();
                Log.d("viewGroup move", "onTouchEvent: move Y = "+event.getRawY()+" dy = "+dy);break;
            case MotionEvent.ACTION_CANCEL:
                firstCome = true;
                Log.d(TAG, "onTouchEvent: cancel");break;
                default:break;
        }
        return true;
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
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                lastY = ev.getRawY();
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

    public void setCanIntercept(boolean canIntercept,int viewY) {
        this.canIntercept = canIntercept;
    }
}
