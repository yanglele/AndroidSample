package com.example.yangl.androidsample.someActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LayoutMarginParamActivity extends AppCompatActivity {

    @BindView(R.id.set_layout_param)
    Button setLayoutParam;
    @BindView(R.id.set_margin_param)
    Button setMarginParam;
    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.tips)
    TextView tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_param2);
        ButterKnife.bind(this);

    }

    public void onLayoutParamClick(View view){
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
    }
}
