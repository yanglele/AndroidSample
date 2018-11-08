package com.example.yangl.androidsample.animation.propertyAnimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ValueAnimListenerActivity extends AppCompatActivity {

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.begin)
    Button begin;
    @BindView(R.id.cancel)
    Button cancel;

    ValueAnimator valueAnimator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_anim_listener);
        ButterKnife.bind(this);
    }

    public void begin(View view){
        valueAnimator = ValueAnimator.ofFloat(0, UISizeUtils.getContentViewHeight(this) - imageView.getHeight());
        valueAnimator.setTarget(imageView);
        valueAnimator.setRepeatCount(2);
        valueAnimator.setDuration(2000).start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageView.setTranslationY((Float) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(ValueAnimListenerActivity.this,"动画开始",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(ValueAnimListenerActivity.this,"动画结束",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Toast.makeText(ValueAnimListenerActivity.this,"动画取消",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Toast.makeText(ValueAnimListenerActivity.this,"动画重复",Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void cancel(View view){
        valueAnimator.removeAllUpdateListeners();
        valueAnimator.removeAllListeners();
        valueAnimator.cancel();
    }

}
