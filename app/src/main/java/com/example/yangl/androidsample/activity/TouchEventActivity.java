package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.touchEvent.OuterDemoActivity_1;
import com.example.yangl.androidsample.touchEvent.innerIntercept.TouchInnerActivity;
import com.example.yangl.androidsample.touchEvent.outsideIntercect.TouchOutActivity;
import com.example.yangl.androidsample.touchEvent.simple.SimpleTouchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TouchEventActivity extends AppCompatActivity {


    @BindView(R.id.touch_outside)
    Button touchOutside;
    @BindView(R.id.touch_innerside)
    Button touchInnerside;
    @BindView(R.id.view_group)
    LinearLayout viewGroup;
    @BindView(R.id.simple)
    Button button;
    @BindView(R.id.simple_out_eg)
    Button simpleOutEg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.touch_outside, R.id.touch_innerside, R.id.simple,R.id.simple_out_eg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.touch_outside:
                startActivity(new Intent(TouchEventActivity.this, TouchOutActivity.class));
                break;
            case R.id.touch_innerside:
                startActivity(new Intent(TouchEventActivity.this, TouchInnerActivity.class));
                break;
            case R.id.simple:
                startActivity(new Intent(TouchEventActivity.this, SimpleTouchActivity.class));
                break;
            case R.id.simple_out_eg:
                startActivity(new Intent(TouchEventActivity.this,OuterDemoActivity_1.class));break;
        }
    }
}
