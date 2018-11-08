package com.example.yangl.androidsample.imageCorp.matrix;

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
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.yangl.androidsample.R;


/**
 * Desc:通过 BitmapShader 获取T性图片
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/9/30
 * version:
 * update:
 */
public class DrawShapeView extends View {
    
    private final String TAG = "DrawShapView";

    private Bitmap b;

    private Paint paint;
    private BitmapShader shader;
    private Path path;

    int viewW;
    int viewH;

    public DrawShapeView(Context context) {
        super(context);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.image5);
    }

    public DrawShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.image5);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        shader = new BitmapShader(b, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int bw = b.getWidth();
        int bh = b.getHeight();
//        RectF src = new RectF(0, 0, bw, bh);
//        RectF dst = new RectF(0, 0, viewW, viewH);
//        m.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);
//        Log.d(TAG, "onSizeChanged: ");
        this.viewW = w;
        this.viewH = h;
        Matrix matrix = new Matrix();
        matrix.setScale(viewW/bw,viewH/bh);
        shader.setLocalMatrix(matrix);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path = new Path();
        path.moveTo(0,0);
        path.lineTo(viewW -50,0);
        path.lineTo(viewW, viewH);
        path.lineTo(0, viewH);
        path.lineTo(0,0);
        paint.setShader(shader);

        canvas.drawPath(path,paint);
        Log.d(TAG, "onDraw: ");
    }

}
