package com.example.yangl.androidsample.view.innerIntercept;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TouchInnerActivity extends AppCompatActivity {

    @BindView(R.id.inner_view)
    InterceptView innerView;
    @BindView(R.id.inner_layout)
    InterceptViewGroup innerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_inner);
        ButterKnife.bind(this);

        innerView.setParentNeedEvent(true);
        innerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(TouchInnerActivity.this,"view get touch",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        innerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(TouchInnerActivity.this,"layout get touch",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
