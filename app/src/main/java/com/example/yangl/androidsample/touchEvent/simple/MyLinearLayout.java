package com.example.yangl.androidsample.touchEvent.simple;

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
 * data:2019/1/11
 * version:
 * update:
 */
public class MyLinearLayout extends LinearLayout {

    private static final String TAG = "MyLinearLayout";

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    float downY;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch(event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                downY = event.getY();
//                Log.d(TAG, "onTouchEvent: down "+downY);
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.d(TAG, "onTouchEvent: up");break;
//            case MotionEvent.ACTION_MOVE:
//                int dy = (int) (event.getY() - downY);
//                ((ViewGroup)getParent()).scrollTo(0,-dy);
//                Log.d("intercept move", "onTouchEvent: move" + event.getY() +"  dy = "+dy);break;
//            case MotionEvent.ACTION_CANCEL:
//                Log.d(TAG, "onTouchEvent: cancel");break;
//            default:break;
//        }
//        return true;
//    }
}
