package com.example.yangl.androidsample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.view.outsideIntercect.InterceptViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouchEventActivity extends AppCompatActivity {

    @BindView(R.id.view)
    TextView view;
    @BindView(R.id.view_group)
    InterceptViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        ButterKnife.bind(this);
        viewGroupGet();

    }

    private void viewGroupGet(){
        view.getParent().requestDisallowInterceptTouchEvent(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TouchEventActivity.this,"view  get touch!",Toast.LENGTH_SHORT).show();
            }
        });
        viewGroup.setCanIntercept(true);
        viewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(TouchEventActivity.this,"viewGroup  get touch!",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
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
