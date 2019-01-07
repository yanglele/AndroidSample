package com.example.yangl.androidsample.touchEvent.outsideIntercect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouchOutActivity extends AppCompatActivity {


    private String TAG = "InterceptActivity";

    @BindView(R.id.view)
    InterceptView view;
    @BindView(R.id.view_group)
    InterceptViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_out);
        ButterKnife.bind(this);
        viewGroupGet();

    }

    private void viewGroupGet() {
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(TouchOutActivity.this, "view  get touch!", Toast.LENGTH_SHORT).show();
//            }
//        });
        viewGroup.setCanIntercept(true);
//        viewGroup.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(TouchOutActivity.this, "viewGroup  get touch!", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(TouchOutActivity.this, "view  get touch!", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        viewGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Toast.makeText(TouchOutActivity.this, "viewGroup  get click!", Toast.LENGTH_SHORT).show();
//
//            }
//        });
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





    private void touchSource() {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view.getParent().requestDisallowInterceptTouchEvent(true);
    }
}
