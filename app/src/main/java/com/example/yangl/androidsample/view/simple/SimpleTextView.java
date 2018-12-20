package com.example.yangl.androidsample.view.simple;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/12/18
 * version:
 * update:
 */
public class SimpleTextView extends android.support.v7.widget.AppCompatTextView {
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
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
