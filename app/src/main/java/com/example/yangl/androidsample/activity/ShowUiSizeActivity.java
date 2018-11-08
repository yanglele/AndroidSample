package com.example.yangl.androidsample.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 该页面用于Android ui 尺寸 测量显示
 */

public class ShowUiSizeActivity extends AppCompatActivity {

    @BindView(R.id.show_status_height_text)
    TextView showStatusHeightText;
    @BindView(R.id.show_status_height)
    Button showStatusHeight;
    @BindView(R.id.translationY)
    Button translationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ui_size);
        ButterKnife.bind(this);
    }

    public void onViewClicked(View view) {
        int statusBarHeight = UISizeUtils.getStatusBarHeight(this);
        showStatusHeightText.setText(String.valueOf(statusBarHeight) + "dip");
    }

    public void translationYMove(View view){
        float translationY = showStatusHeight.getTranslationY();
        showStatusHeight.setTranslationY(270+translationY);
    }

    public void alphaChange(View view){
        ObjectAnimator animator = ObjectAnimator.ofFloat(showStatusHeight,"alpha",1f,0f);
        animator.setDuration(500);
        animator.start();
    }

}
