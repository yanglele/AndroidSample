package com.example.yangl.androidsample.touchEvent.outsideIntercept2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.touchEvent.outsideIntercect.InterceptView;
import com.example.yangl.androidsample.touchEvent.outsideIntercect.InterceptViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouchOutActivity2 extends AppCompatActivity {


    private String TAG = "outsideIntercept2.InterceptActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_out_2);
        viewGroupGet();

    }

    private void viewGroupGet() {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent: down");break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: up");break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent: move");break;
            default:break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: down");break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: up");break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "dispatchTouchEvent: move");break;
            default:break;
        }
        return super.dispatchTouchEvent(ev);
    }





}
