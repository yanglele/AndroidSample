package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.customimageload.CustomImageLoadActivity;
import com.example.yangl.androidsample.someActivity.GlideDemoActivity;
import com.example.yangl.androidsample.someActivity.MySimpleImageLoadActivity;
import com.example.yangl.androidsample.someActivity.NativeJavaMemoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ImageLoadActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.my_simple_image_load)
    Button mySimpleImageLoad;
    @BindView(R.id.my_image_load)
    Button myImageLoad;
    @BindView(R.id.glide_test)
    Button glideTest;
    @BindView(R.id.bitmap_load_native_java)
    Button bitmapLoadNativeJava;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        unbinder = ButterKnife.bind(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.my_simple_image_load, R.id.my_image_load, R.id.glide_test})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_simple_image_load:
                startActivity(new Intent(ImageLoadActivity.this, MySimpleImageLoadActivity.class));
                break;
            case R.id.my_image_load:
                startActivity(new Intent(ImageLoadActivity.this, CustomImageLoadActivity.class));
                break;
            case R.id.glide_test:
                startActivity(new Intent(ImageLoadActivity.this, GlideDemoActivity.class));
                break;
        }
    }

    @OnClick(R.id.bitmap_load_native_java)
    public void onViewClicked() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.image);
        startActivity(new Intent(ImageLoadActivity.this, NativeJavaMemoryActivity.class));
    }
}
