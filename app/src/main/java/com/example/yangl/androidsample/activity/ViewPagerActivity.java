package com.example.yangl.androidsample.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.adapter.MyViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ViewPagerActivity extends AppCompatActivity {


    @BindView(R.id.view_pager)
    ViewPager viewPager;

    int lastPos = 0;

    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_activity);
        unbinder = ButterKnife.bind(this);
        initViewPager();
        View view = new View(this);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    private void initViewPager(){
        List<Integer> lists = new ArrayList<>();
        lists.add(R.drawable.image);
        lists.add(R.drawable.image2);
        lists.add(R.drawable.image3);
        lists.add(R.drawable.image4);
        lists.add(R.drawable.image5);
        lists.add(R.drawable.image6);
        MyViewPagerFragmentAdapter fragmentAdapter = new MyViewPagerFragmentAdapter(getSupportFragmentManager(),lists,this);
        viewPager.setAdapter(fragmentAdapter);
//        viewPager.post(new Runnable() {
//            @Override
//            public void run() {
//                startMove();
//            }
//        });

//        viewPager.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                showToast();
//            }
//        },1000);
    }

    private void showToast(){
        Toast.makeText(this,"toast",Toast.LENGTH_SHORT).show();
    }

    private void startMove(){

        final ValueAnimator animator = ValueAnimator.ofInt(0,-300,0);
        animator.setDuration(10000);
        animator.setRepeatCount(1);
        animator.setInterpolator(new LinearInterpolator());
        animator.setEvaluator(new IntEvaluator());
        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                animator.start();
                Log.d("1111", "onGlobalLayout: ");
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewPager.endFakeDrag();
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if(!viewPager.isFakeDragging()){
                    viewPager.beginFakeDrag();
                }
                int newPos = (int) animation.getAnimatedValue();
                int dragBy = newPos - lastPos;
                lastPos = newPos;
                viewPager.fakeDragBy(dragBy);
                Log.d("11111", "onAnimationUpdate: "+newPos);
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
