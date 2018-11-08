package com.example.yangl.androidsample.animation.propertyAnimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yangl.androidsample.R;

public class ObjectAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
    }

    public void rotateAnim(View view){
        ObjectAnimator.ofFloat(view,"rotationX",0.0F,360.0F)
                .setDuration(1500)
                .start();
    }

    public void rotateAnimSet(final View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "zhy", 1.0F, 0.0F)
                .setDuration(500);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                view.setAlpha(animatedValue);
                view.setScaleX(animatedValue);
                view.setScaleY(animatedValue);
            }
        });
    }

    public void propertyValuesHolder(View view){
        PropertyValuesHolder alphaView = PropertyValuesHolder.ofFloat("alpha",1.0F,0.0F,1.0F);
        PropertyValuesHolder scaleXView = PropertyValuesHolder.ofFloat("scaleX",1.0F,0.0F,1.0F);
        PropertyValuesHolder scaleYView = PropertyValuesHolder.ofFloat("scaleY",1.0F,0.0F,1.0F);
        ObjectAnimator.ofPropertyValuesHolder(view,alphaView,scaleXView,scaleYView).setDuration(1000).start();
    }
}
