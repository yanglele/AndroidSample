package com.example.yangl.androidsample.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.tools.AjkImageLoaderUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FrescoTestActivity extends AppCompatActivity {

    @BindView(R.id.big_image)
    SimpleDraweeView bigImage;
    @BindView(R.id.gone)
    Button gone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_test);
        ButterKnife.bind(this);
        AjkImageLoaderUtil.getInstance().loadBitmap("http://pic1.ajkimg.com/m/b9f233e13a6b199e037f916d603df971/210x210c.jpg", new AjkImageLoaderUtil.LoadBitmapListener() {
            @Override
            public void onSuccess(String url, Bitmap bitmap) {
                bigImage.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(String url) {

            }
        });
    }

    @OnClick(R.id.gone)
    public void onViewClicked() {
    }
}
