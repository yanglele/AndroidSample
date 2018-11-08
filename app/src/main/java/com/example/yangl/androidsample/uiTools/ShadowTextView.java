package com.example.yangl.androidsample.uiTools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/16
 * version:
 * update:
 */

public class ShadowTextView extends AppCompatTextView {

    private Context context;
    private String text;
    @ColorRes
    private int shadowColor;
    private int dx;
    private int dy;
    private int blur;

    public ShadowTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 建立Paint 物件
        Paint paint1 = new Paint();
        // 设定颜色
        paint1.setColor(0xFFFFFF00);
        paint1.setTextSize(100);
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
        paint1.setShadowLayer(10, 10, 10, 0xFFFF00FF);
        // 实心矩形& 其阴影
        canvas.drawText("我很爱你", 200,400,paint1);

        paint1.setShadowLayer(10, -10, -10, 0xFFFF00FF);
        // 实心矩形& 其阴影
        canvas.drawText("我很爱你", 200,400,paint1);

    }

}
