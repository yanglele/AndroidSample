package com.example.yangl.androidsample.touchEvent.outsideInterceptMove;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/17
 * version:
 * update:
 */
public class InterceptView extends View {
    private String TAG = "InterceptView";

    public InterceptView(Context context) {
        super(context);
    }

    public InterceptView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float downY;
    float lastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: down "+downY);
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: up");break;
            case MotionEvent.ACTION_MOVE:
                int dy = (int) (event.getRawY() - lastY);
                ((ViewGroup)(getParent())).scrollBy(0,-dy);
                lastY = event.getRawY();
                Log.d("View move", "onTouchEvent: move" + event.getRawY() +"  dy = "+dy);break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent: cancel");break;
            default:break;
        }
        return true;
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = event.getY();
                lastY = event.getRawY();
                Log.d(TAG, "dispatchTouchEvent: down");break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: up");break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: move");break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent: cancel");break;
            default:break;
        }
        return super.dispatchTouchEvent(event);
    }


}
