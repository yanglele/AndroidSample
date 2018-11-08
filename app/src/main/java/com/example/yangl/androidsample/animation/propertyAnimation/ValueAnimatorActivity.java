package com.example.yangl.androidsample.animation.propertyAnimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.animation.BezierEvaluator2;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ValueAnimatorActivity extends AppCompatActivity {

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.run_function)
    Button runFunction;
    @BindView(R.id.image_view1)
    ImageView imageView1;
    @BindView(R.id.tip_point)
    ImageView tipPoint;

    private ValueAnimator lineValueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        ButterKnife.bind(this);
    }

    public void onLineRunClick(View view) {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, UISizeUtils.getContentViewHeight(this) - imageView.getHeight());
        valueAnimator.setTarget(imageView);
        valueAnimator.setDuration(1000).start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageView.setTranslationY((Float) valueAnimator.getAnimatedValue());
            }
        });
    }

    public void onLineDirRunClick(View view) {
        lineValueAnimator = new ValueAnimator();
        PointF pointF = new PointF(UISizeUtils.getScreenWidth(this) - imageView.getWidth(),UISizeUtils.getContentViewHeight(this) - imageView.getHeight());
        lineValueAnimator.setDuration(10000);
        lineValueAnimator.setObjectValues(new PointF(0, 0),pointF);
        lineValueAnimator.setInterpolator(new LinearInterpolator());
        lineValueAnimator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                PointF pointF = new PointF();
                //x=vt y=vt
                pointF.x = ((PointF)endValue).x*fraction;
                pointF.y = ((PointF)endValue).y*fraction;
                return pointF;
            }
        });
        lineValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) lineValueAnimator.getAnimatedValue();
                imageView.setX(pointF.x);
                imageView.setY(pointF.y);
                Log.d("11111", "onAnimationUpdate: "+animation.getAnimatedValue());
            }
        });
        lineValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(ValueAnimatorActivity.this,"动画结束",Toast.LENGTH_SHORT).show();
            }
        });
        lineValueAnimator.start();
    }

    public void onLineFunctionRunClick(View view){
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
        {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue)
            {
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF point = (PointF) animation.getAnimatedValue();
                imageView.setX(point.x);
                imageView.setY(point.y);
                Log.d("Point", "onAnimationUpdate: x="+point.x+"  y = "+point.y);
            }
        });
    }

    public void onBezierClick(View view){

        Point startPoint = new Point(100,100);
        Point endPoint = new Point(500,500);
        Point controlPoint = new Point(100,500);

        Log.d("Point", "onAnimationUpdate1: x="+imageView1.getTranslationX()+"  y = "+imageView1.getTranslationY());


        ValueAnimator valueAnimator = ValueAnimator.ofObject(new BezierEvaluator2(controlPoint),startPoint,endPoint);
        valueAnimator.setDuration(1000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                imageView1.setX(point.x);
                imageView1.setY(point.y);
//                imageView1.invalidate();
                Log.d("Point", "onAnimationUpdate: x="+point.x+"  y = "+point.y);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                imageView1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewGroup decorView = (ViewGroup) ValueAnimatorActivity.this.getWindow().getDecorView();
                decorView.removeView(decorView);
            }
        });

        valueAnimator.start();

    }

    public void onGroupClick(View view){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(tipPoint,"scaleX",1f,2f,1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(tipPoint,"scaleY",1f,2f,1f);
        ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(tipPoint, "translationX", 0f, 200f);
        ObjectAnimator translationYAnimator = ObjectAnimator.ofFloat(tipPoint, "translationY", 0f, 500f);
        ObjectAnimator animator = ObjectAnimator.ofFloat(tipPoint, "alpha", 0.3f, 0.5f, 0f);
        animatorSet.play(translationXAnimator).with(translationYAnimator).with(animator).with(scaleXAnimator).with(scaleYAnimator);
        animatorSet.setDuration(3000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                tipPoint.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                tipPoint.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    public void onJumpClick(View view){
        startActivity(new Intent(ValueAnimatorActivity.this,ValueAnimListenerActivity.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(lineValueAnimator != null && lineValueAnimator.isRunning()){
            lineValueAnimator.cancel();
        }
    }
}
