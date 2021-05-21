package com.example.yangl.androidsample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.yangl.androidsample.R;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/10/19
 * version:
 * update:
 */
public class MyOwnerView extends View {

    int height=0,width= 0;
    public MyOwnerView(Context context) {
        this(context,null);
    }

    public MyOwnerView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyOwnerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyOwnerView, defStyleAttr, 0);
        try {
            height = typedArray.getDimensionPixelSize(R.styleable.MyOwnerView_android_layout_height,0);
            width = typedArray.getDimensionPixelSize(R.styleable.MyOwnerView_android_layout_width,0);
        }catch (UnsupportedOperationException e){
        }
        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

    }
}
