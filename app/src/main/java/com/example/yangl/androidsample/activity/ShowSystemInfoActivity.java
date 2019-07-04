package com.example.yangl.androidsample.activity;

import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangl.androidsample.ConstantStirng;
import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 该页面用于Android ui 尺寸 测量显示
 */

public class ShowSystemInfoActivity extends AppCompatActivity {

    @BindView(R.id.show_status_height_text)
    TextView showStatusHeightText;
    @BindView(R.id.show_status_height)
    Button showStatusHeight;
    @BindView(R.id.translationY)
    Button translationY;
    @BindView(R.id.alpha)
    Button alpha;
    @BindView(R.id.heap_info)
    TextView heapInfo;

    private List<String> testMaxtHeap = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ui_size);
        ButterKnife.bind(this);
        showHeapInfo();
    }

    public void onViewClicked(View view) {
        int statusBarHeight = UISizeUtils.getStatusBarHeight(this);
        showStatusHeightText.setText(String.valueOf(statusBarHeight) + "dip");
    }

    public void translationYMove(View view) {
        float translationY = showStatusHeight.getTranslationY();
        showStatusHeight.setTranslationY(270 + translationY);
    }

    public void alphaChange(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(showStatusHeight, "alpha", 1f, 0f);
        animator.setDuration(500);
        animator.start();
    }

    public void addHeap(View view){
        for(int i=0;i<Integer.MAX_VALUE;i++){
            testMaxtHeap.add(new String("1"));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showHeapInfo(){
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);

        Runtime runtime = Runtime.getRuntime();

        StringBuilder builder = new StringBuilder();
        builder.append("1.ActivityManager方法获取：").append("\n")
                .append("   totalMem = ").append(info.totalMem/1024/1024).append("MB").append("\n")
                .append("   availMem  = ").append(info.availMem/1024/1024).append("MB").append("\n")
                .append("   threshold  = ").append(info.threshold/1024/1024).append("MB").append("\n")
                .append("   activityManager.getMemoryClass  = ").append(activityManager.getMemoryClass()/1024/1024).append("MB").append("\n")
                .append("   activityManager.getLargeMemoryClass  = ").append(activityManager.getLargeMemoryClass()/1024/1024).append("MB").append("\n")
                .append("2.Runtime方法获取：").append("\n")
                .append("   maxMemory = ").append(runtime.maxMemory()/1024/1024).append("MB").append("\n");
        heapInfo.setText(builder.toString());
    }

}
