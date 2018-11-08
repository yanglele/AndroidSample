package com.example.yangl.androidsample.scrollView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/28
 * version:
 * update:
 */

public class ScrollViewNoScroll extends ScrollView{

    GestureDetector gestureDetector ;
    private boolean change;

    public ScrollViewNoScroll(Context context) {
        super(context);
    }

    public void setGestureDetector(GestureDetector gestureDetector){
        this.gestureDetector = gestureDetector;
    }

    public void changeScrollViewScroll(boolean change){
        this.change = change;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        if(change){
            return true;
        }else {
            return false;
        }
    }

}
