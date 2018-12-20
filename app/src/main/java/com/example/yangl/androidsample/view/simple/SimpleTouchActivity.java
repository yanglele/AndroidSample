package com.example.yangl.androidsample.view.simple;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleTouchActivity extends AppCompatActivity {

    @BindView(R.id.text_view)
    TextView textView;
    @BindView(R.id.layout)
    ConstraintLayout layout;
    @BindView(R.id.simple_test)
    SimpleTextView simpleTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_touch);
        ButterKnife.bind(this);

//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SimpleTouchActivity.this,"viewGroup get click!",Toast.LENGTH_SHORT).show();
//            }
//        });

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(SimpleTouchActivity.this, "viewGroup get touch!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimpleTouchActivity.this, "textView get click!", Toast.LENGTH_SHORT).show();

            }
        });




    }
}