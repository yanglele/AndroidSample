package com.example.yangl.androidsample.someActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextViewScrollToActivity extends AppCompatActivity {

    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_scroll_to);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.scrollBy(0,-10);
            }
        });
    }
}
