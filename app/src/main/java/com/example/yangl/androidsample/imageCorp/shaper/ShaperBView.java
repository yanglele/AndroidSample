package com.example.yangl.androidsample.imageCorp.shaper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/10/10
 * version:
 * update:
 */
public class ShaperBView extends AppCompatImageView {

    private Bitmap bitmap;
    private Matrix matrix;

    private Paint paint;
    private BitmapShader shader;
    private Path path;

    private int viewWeight;
    private int viewHeight;


    public ShaperBView(Context context) {
        super(context);
    }

    public ShaperBView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image5);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        matrix = new Matrix();

        viewWeight = (UISizeUtils.getScreenWidth((Activity) getContext()) - UISizeUtils.dip2px(getContext(),3+30))/2
                + UISizeUtils.dip2px(getContext() ,30);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = viewWeight;
        setMeasuredDimension(width,heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        int bw = bitmap.getWidth();
        int bh = bitmap.getHeight();

        viewHeight = h;
        //设置缩放比例，取较大的缩放比例，另一边裁剪掉
        float scaleX = (float)viewWeight/bw;
        float scaleY = (float)h/bh;
        float scale = Math.max(scaleX,scaleY);
        matrix.setScale(scale,scale);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //根据路径画出相应的图形
        path = new Path();
        path.moveTo(0,0);
        path.lineTo(viewWeight,0);
        path.lineTo(viewWeight, viewHeight);
        path.lineTo(UISizeUtils.dip2px(getContext(),30), viewHeight);
        path.lineTo(0,0);
        paint.setShader(shader);

        canvas.drawPath(path,paint);
    }
}
