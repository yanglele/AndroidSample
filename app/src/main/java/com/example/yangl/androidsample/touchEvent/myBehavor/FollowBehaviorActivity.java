package com.example.yangl.androidsample.touchEvent.myBehavor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.yangl.androidsample.R;

public class FollowBehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_behavior);

        findViewById(R.id.btn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setX(event.getRawX()-v.getWidth()/2);
                    v.setY(event.getRawY()-v.getHeight()/2);
                }
                return true;
            }
        });

    }
}
