package com.example.yangl.androidsample.someActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollToActivity extends AppCompatActivity {

    @BindView(R.id.text_view1)
    TextView textView1;
    @BindView(R.id.text_view2)
    TextView textView2;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_to);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.scrollBy(UISizeUtils.dip2px(getApplicationContext(),10),UISizeUtils.dip2px(getApplicationContext(),10));
            }
        });
    }
}
