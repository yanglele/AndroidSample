package com.example.yangl.androidsample.someActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BackGroundActivity extends AppCompatActivity {

    @BindView(R.id.change_back_bounds)
    Button changeBackBounds;
    @BindView(R.id.root_view)
    ConstraintLayout rootView;

    private int unit = 50;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_ground);
        ButterKnife.bind(this);

        changeBackBounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                count++;
//                rootView.getBackground().setBounds(0,0,
//                        UISizeUtils.getScreenWidth( BackGroundActivity.this) - unit*count,
//                        UISizeUtils.getScreenHeight( BackGroundActivity.this) - unit*count);
                changeBackBounds.setTranslationY(100);
                Toast.makeText(BackGroundActivity.this,"hello",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
