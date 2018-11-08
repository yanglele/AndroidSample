package com.example.yangl.androidsample.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/9/14
 * version:
 * update:
 */
public class MyButton extends AppCompatButton {

    private String TAG = "MyButton "+"view_life";
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow: ");
    }
}
