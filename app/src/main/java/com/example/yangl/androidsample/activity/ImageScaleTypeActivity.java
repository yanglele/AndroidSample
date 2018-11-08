package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.someActivity.FrescoImageTypeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageScaleTypeActivity extends AppCompatActivity {

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.image_text)
    TextView imageText;
    @BindView(R.id.image_view2)
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scale_type);
        ButterKnife.bind(this);
    }

    public void centerInsideClick(View view) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageText.setText("CENTER_INSIDE， 将图片的内容完整居中显示，图片小于view宽高不拉伸，大于缩放");
    }

    public void centerCorpClick(View view) {
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageText.setText("CENTER_CROP，等比例缩放后裁剪，使得图片填充整个view");
    }

    public void centerClick(View view) {
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView2.setScaleType(ImageView.ScaleType.CENTER);
        imageText.setText("CENTER，图片原尺寸截取中间图片,不缩放");
    }

    public void fitxyClick(View view) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageText.setText("FIT_XY，拉伸图片，使得填充布局");
    }

    public void fitCenterClick(View view) {
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageText.setText("FIT_CENTER,即默认值， 把图片按比例扩大/缩小到View的宽度，居中显示");
    }

    public void frescoClick(View view) {
        startActivity(new Intent(ImageScaleTypeActivity.this,FrescoImageTypeActivity.class));
    }
}
