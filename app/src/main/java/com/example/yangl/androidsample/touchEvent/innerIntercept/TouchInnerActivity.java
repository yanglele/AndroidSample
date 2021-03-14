package com.example.yangl.androidsample.touchEvent.innerIntercept;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.touchEvent.utils.MyUtils;


import butterknife.BindView;
import butterknife.ButterKnife;

public class TouchInnerActivity extends AppCompatActivity {
    
    private final String TAG = "TouchInnerActivity";

    @BindView(R.id.inner_view)
    InterceptView innerView;
    @BindView(R.id.inner_layout)
    InterceptViewGroup innerLayout;

    private int pointId;
    private float downY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        pointId = ev.getPointerId(0);
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = MyUtils.getPointerY(ev,pointId);
                Log.d(TAG, "dispatchTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                float moveY = MyUtils.getPointerY(ev,pointId);
                float dy = Math.abs(moveY - downY);
                if(dy > 20){
                    innerView.setChildNeedEvent(true);
                }
                Log.d(TAG, "dispatchTouchEvent: move dy = "+dy);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: up");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "dispatchTouchEvent: cancel");
                break;
                default:break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_inner);
        ButterKnife.bind(this);


//        innerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(TouchInnerActivity.this,"view get touch",Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        innerLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Toast.makeText(TouchInnerActivity.this,"layout get touch",Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
    }
}
