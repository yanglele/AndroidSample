package com.example.yangl.androidsample.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.view.MyButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnConfigChangeActivity extends AppCompatActivity {

    @BindView(R.id.button)
    MyButton button;
    @BindView(R.id.change_layout)
    Button changeLayout;
    @BindView(R.id.root_view)
    ConstraintLayout rootView;
    //横竖屏全局变量数值保留
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_config_change);
        ButterKnife.bind(this);
        count++;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("111", "onConfigurationChanged:");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else {
            Log.d("111", "onConfigurationChanged: vertical = " + count);
        }
    }

    public void onButtonClick(View view) {
        Configuration cf = getResources().getConfiguration();
        if (Configuration.ORIENTATION_LANDSCAPE == cf.orientation) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    public void onLayoutChangeClick(View view) {
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        layoutParams.height = 800;
        rootView.setLayoutParams(layoutParams);
    }


}
