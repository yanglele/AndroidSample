package com.example.yangl.androidsample.touchEvent.outsideInterceptMove;

import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouchOutMoveActivity extends AppCompatActivity {


    private String TAG = "InterceptActivity";

    @BindView(R.id.view)
    InterceptView view;
    @BindView(R.id.view_group)
    InterceptViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_out_move);
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

    private float downY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int pointerId = MotionEventCompat.getPointerId(ev,0);
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "dispatchTouchEvent: down");
                downY = getPointerY(ev,pointerId);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: up");break;
            case MotionEvent.ACTION_MOVE:
                // 获得当前手指的Y
                final float pointerY = getPointerY(ev, pointerId);
                // 计算出滑动的偏移量
                float deltaY = pointerY - downY;
                Log.d(TAG, "dispatchTouchEvent: move ,dy = "+deltaY);
                if(Math.abs(deltaY) > 30 && Math.abs(deltaY) < 60){
                    viewGroup.setCanIntercept(true);
                }else if(Math.abs(deltaY) > 60){
                    viewGroup.setCanIntercept(false);
                }
//                viewGroup.setCanIntercept(true);
                break;
            default:break;
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * 这个方法通过pointerId获取pointerIndex,然后获取Y
     */
    private float getPointerY(MotionEvent event, int pointerId) {
        final int pointerIndex = MotionEventCompat.findPointerIndex(event, pointerId);
        if (pointerIndex < 0) {
            return -1;
        }
        return MotionEventCompat.getY(event, pointerIndex);
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
