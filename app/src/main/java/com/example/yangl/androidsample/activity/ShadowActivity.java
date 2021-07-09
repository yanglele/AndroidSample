package com.example.yangl.androidsample.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yangl.androidsample.R;

import java.util.HashMap;

public class ShadowActivity extends AppCompatActivity {

    private static HashMap<Context,String> map = new HashMap<Context, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new DrawCanvas(this));
        setContentView(R.layout.activity_shadow);
        map.put(this,"1");
        String s =  map.get(this);
        Toast.makeText(this,"s",Toast.LENGTH_SHORT).show();
    }

    class DrawCanvas extends View
    {
        private Bitmap bitmap = null;
        public DrawCanvas(Context context)
        {
            super(context);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.home_img);
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            // 建立Paint 物件
            Paint paint1 = new Paint();
            // 设定颜色
            paint1.setColor(getResources().getColor(R.color.mWhiteColor));
            paint1.setTextSize(100);
            // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
            int colorRes = getResources().getColor(R.color.mBlackColor);
            paint1.setShadowLayer(20, 0, 0, colorRes);
            // 实心矩形& 其阴影
            canvas.drawText("我很爱你", 200,400,paint1);
//            Paint paint2 = new Paint();
//            paint2.setColor(Color.GREEN);
//            paint2.setShadowLayer(10, 5, 2, Color.YELLOW);
//            canvas.drawText("你真傻", 20,60,paint2);
//
//            Paint paint3 = new Paint();
//            paint3.setColor(Color.RED);
//            paint3.setShadowLayer(30, 5, 2, Color.GREEN);
//            canvas.drawCircle(50, 130,30, paint3);
//
//            Paint paint4 = new Paint();
//            paint4.setShadowLayer(5, 8, 7, Color.DKGRAY);
//            canvas.drawBitmap(bitmap, 50, 200, paint4);
        }
    }
}
