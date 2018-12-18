package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.view.innerIntercept.TouchInnerActivity;
import com.example.yangl.androidsample.view.outsideIntercect.TouchOutActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.touch_outside, R.id.touch_innerside})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.touch_outside:
                startActivity(new Intent(TouchEventActivity.this, TouchOutActivity.class));
                break;
            case R.id.touch_innerside:
                startActivity(new Intent(TouchEventActivity.this, TouchInnerActivity.class));
                break;
        }
    }
}
