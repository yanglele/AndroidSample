package com.example.yangl.androidsample.touchEvent.simple;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/18
 * version:
 * update:
 */
public class SimpleTextView extends android.support.v7.widget.AppCompatTextView {

    private final String TAG = "SimpleTextView";
    public SimpleTextView(Context context) {
        super(context);
    }

    public SimpleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    float downY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN :
                downY = event.getY();
                Log.d(TAG, "onTouchEvent: action down");
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY();
                int dy = (int)(moveY - downY);
                scrollTo(0,-dy);
                Log.d(TAG, "onTouchEvent: action move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: action up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent: action cancel");
                break;
                default:break;
        }
        return true;
    }

}
