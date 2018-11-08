package com.example.yangl.androidsample.imageCorp.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.yangl.androidsample.R;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/9/30
 * version:
 * update:
 */
public class NormalShapeView extends View {

    private Bitmap b;
    private Matrix m;

    public NormalShapeView(Context context) {
        super(context);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        m = new Matrix();
    }

    public NormalShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.image5);
        m = new Matrix();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int bw = b.getWidth();
        int bh = b.getHeight();
        RectF src = new RectF(0, 0, bw, bh);
        RectF dst = new RectF(0, 0, w, h);
        m.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);

//        float[] pts = {0, 0, 0, bh, bw, bh, bw, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        m.mapPoints(pts, 8, pts, 0, 4);
//        int DX = 40;
//        pts[14] -= DX;
//        m.setPolyToPoly(pts, 0, pts, 8, 4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(b, m, null);
    }

}
