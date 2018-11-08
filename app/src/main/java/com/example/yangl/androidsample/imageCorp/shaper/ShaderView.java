package com.example.yangl.androidsample.imageCorp.shaper;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

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
public class ShaderView extends AppCompatImageView {

    private Bitmap bitmap;

    private Paint paint;
    private BitmapShader shader;

    private final int longerLineExtra = 30;//直角梯形下底减上底的差值
    private final int picSpace = 3;//两个梯形之间的距离

    private int viewWeight;
    private int viewHeight;

    private boolean leftShape = true;//是否是左侧梯形


    public ShaderView(Context context) {
        super(context);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TShapeView);
        leftShape = typedArray.getBoolean(R.styleable.TShapeView_left_shape,true);
        typedArray.recycle();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image5);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        viewWeight = (UISizeUtils.getScreenWidth((Activity) getContext())
                - UISizeUtils.dip2px(getContext(),longerLineExtra)
                - UISizeUtils.dip2px(getContext(),picSpace))/2
                + UISizeUtils.dip2px(getContext() ,longerLineExtra);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(viewWeight,heightMeasureSpec);
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
        Matrix matrix = new Matrix();
        matrix.setScale(scale,scale);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //根据路径画出相应的图形
        Path path = new Path();
        if(leftShape){
            path.moveTo(0,0);
            path.lineTo(viewWeight  ,0);
            path.lineTo(viewWeight - UISizeUtils.dip2px(getContext(),longerLineExtra), viewHeight);
            path.lineTo(0, viewHeight);
            path.lineTo(0,0);
            canvas.drawPath(path,paint);
            drawMaskLayer(canvas,path);
        }else {
            path.moveTo(UISizeUtils.dip2px(getContext(),longerLineExtra),0);
            path.lineTo(viewWeight,0);
            path.lineTo(viewWeight, viewHeight);
            path.lineTo(0, viewHeight);
            path.lineTo(UISizeUtils.dip2px(getContext(),longerLineExtra),0);
            canvas.drawPath(path,paint);
        }


    }

    private void drawMaskLayer(Canvas canvas ,Path path){
        Paint maskPaint = new Paint();
        LinearGradient linearGradient = new LinearGradient(viewWeight - UISizeUtils.dip2px(getContext(),100) , 0,viewWeight,0
                , Color.parseColor("#003CB950"),Color.parseColor("#FF3CB950")
                ,Shader.TileMode.CLAMP);
        maskPaint.setShader(linearGradient);
        canvas.drawPath(path,maskPaint);
    }
}
