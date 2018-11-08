package com.example.yangl.androidsample.uiTools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.yangl.androidsample.R;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/16
 * version:
 * update:
 */

public class DrawShadowText extends View {

    private Context context;
    private String text;
    @ColorRes
    private int shadowColor;
    private int dx;
    private int dy;
    private int blur;

    public DrawShadowText(Context context , String text , @ColorRes int shadowColor , int dx ,int dy ,int blur) {
        super(context);
        this.context = context;
        this.text = text;
        this.shadowColor = shadowColor;
        this.dx = dx;
        this.dy = dy;
        this.blur = blur;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 建立Paint 物件
        Paint paint1 = new Paint();
        // 设定颜色
        paint1.setColor(ContextCompat.getColor(context,shadowColor));
        paint1.setTextSize(100);
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
        int colorRes = ContextCompat.getColor(context,R.color.mBlackColor);
        paint1.setShadowLayer(20, 0, 0, colorRes);
        // 实心矩形& 其阴影
        canvas.drawText(text, 200,400,paint1);
    }
}
