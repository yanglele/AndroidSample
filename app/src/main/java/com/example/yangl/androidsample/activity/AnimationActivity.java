package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.animation.propertyAnimation.ObjectAnimatorActivity;
import com.example.yangl.androidsample.animation.propertyAnimation.RotateTestActivity;
import com.example.yangl.androidsample.animation.propertyAnimation.ValueAnimListenerActivity;
import com.example.yangl.androidsample.animation.propertyAnimation.ValueAnimatorActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends AppCompatActivity {

    @BindView(R.id.object_animation)
    Button objectAnimation;
    @BindView(R.id.value_animation)
    Button valueAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
    }

    public void onObjectAnimationViewClicked(View view) {
        Intent intent = new Intent(AnimationActivity.this, ObjectAnimatorActivity.class);
        startActivity(intent);
    }

    public void onValueAnimationViewClicked(View view) {
        Intent intent = new Intent(AnimationActivity.this, ValueAnimatorActivity.class);
        startActivity(intent);
    }

    public void onValueAnimationListenerClicked(View view){
        Intent intent = new Intent(AnimationActivity.this, ValueAnimListenerActivity.class);
        startActivity(intent);
    }

    public void onRotateClicked(View view){
        Intent intent = new Intent(AnimationActivity.this, RotateTestActivity.class);
        startActivity(intent);

    }


}
