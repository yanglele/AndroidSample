package com.example.yangl.androidsample.touchEvent.outsideIntercept4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouchOutActivity4 extends AppCompatActivity {


    @BindView(R.id.view)
    InterceptView1 view;
    @BindView(R.id.view_group_2)
    InterceptViewGroup2 viewGroup2;
    @BindView(R.id.view_group)
    InterceptViewGroup1 viewGroup;
    private String TAG = "outsideIntercept4.InterceptActivity4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_out_4);
        ButterKnife.bind(this);
        viewGroupGet();
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    private void viewGroupGet() {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: down");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: move");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: down");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: move");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


}
