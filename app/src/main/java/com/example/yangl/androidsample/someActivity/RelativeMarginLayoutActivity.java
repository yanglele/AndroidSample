package com.example.yangl.androidsample.someActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RelativeMarginLayoutActivity extends AppCompatActivity {

    @BindView(R.id.root_view)
    RelativeLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_margin_layout);
        ButterKnife.bind(this);

//        rootView.setGravity(Gravity.BOTTOM | Gravity.RIGHT);

//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(textView1.getLayoutParams());
//        layoutParams.setMargins(10, 10, 10, 10);
//        TextView textView = new TextView(this);
//        textView.setText("hello");
//        textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//        rootView.addView(textView,layoutParams);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        View view = LayoutInflater.from(this).inflate(R.layout.houseajk_view_guide_view_next, null);
        rootView.addView(view,layoutParams);
        rootView.setOnClickListener(v -> {
            int a= 1;
        });
    }
}
