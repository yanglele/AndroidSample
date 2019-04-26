package com.example.yangl.androidsample.touchEvent.scrollconflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/3/30
 * version:
 * update:
 */
public class ScrollableTextView extends android.support.v7.widget.AppCompatTextView {

    public ScrollableTextView(Context context) {
        super(context);
    }

    public ScrollableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float y = 0f;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = event.getY();
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = event.getY() - y;
                this.scrollTo(0,(int)dy);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
