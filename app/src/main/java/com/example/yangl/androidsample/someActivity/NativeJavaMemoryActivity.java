package com.example.yangl.androidsample.someActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.tools.BitmapUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 测试加载bitmap方式
 * java内存消耗
 * native内存消耗
 * android 3-8 bitmap存储在虚拟机heap中
 * 大于8 bitmap存储在native heap
 *
 */
public class NativeJavaMemoryActivity extends AppCompatActivity {

    @BindView(R.id.android_memory_heap)
    Button nativeMemoryHeap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_java_memory);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.android_memory_heap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.android_memory_heap:
                Map<Integer,Bitmap> map = new HashMap<>();
                for(int i=0;i<500;i++){
                    map.put(i, BitmapFactory.decodeResource(getResources(),R.drawable.image2));
                }
                break;
        }
    }
}
