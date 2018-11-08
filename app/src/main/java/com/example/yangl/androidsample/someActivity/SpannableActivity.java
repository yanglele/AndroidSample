package com.example.yangl.androidsample.someActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpannableActivity extends AppCompatActivity {

    @BindView(R.id.text_view)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable);
        ButterKnife.bind(this);

        SpannableString string = new SpannableString("你房间打开撒理发师");
        Drawable drawable = ContextCompat.getDrawable(this,R.drawable.home_img);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable,ImageSpan.ALIGN_BASELINE);
        string.setSpan(imageSpan,2,3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(string);
        ViewTreeObserver observer = textView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

            }
        });

        final DisplayMetrics dm = this.getResources().getDisplayMetrics();
        textView.measure(
                View.MeasureSpec.makeMeasureSpec(dm.widthPixels, View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(dm.heightPixels, View.MeasureSpec.AT_MOST));

        final int width = textView.getMeasuredWidth();
        final int height = textView.getMeasuredHeight();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setMargins(width,0,0,0);

        VelocityTracker tracker = VelocityTracker.obtain();
        tracker.computeCurrentVelocity(1000);
        int xVelocity = (int) tracker.getXVelocity();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
