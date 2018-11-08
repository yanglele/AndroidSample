package com.example.yangl.androidsample.imageCorp.imagefactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.imageCorp.PhotoUtils;


public class ImageFactoryActivity extends Activity implements View.OnClickListener {


    private ImageFactoryCrop mImageFactoryCrop;

    private String mPath;
    private String mNewPath;
    private int requestCode = -1;
    private String mTitle;

    private TextView tvHeaderLeft;
    private TextView tvHeaderMiddle;
    private TextView tvHeaderRight;

    private int mScreenWidth;
    private int mScreenHeight;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagefactory);


        //获取屏幕宽高
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        mScreenHeight = metric.heightPixels;

        initViews();

        init();
    }

    public void initViews() {

        tvHeaderLeft = (TextView) findViewById(R.id.tv_header_left);
        tvHeaderMiddle = (TextView) findViewById(R.id.tv_header_middle);
        tvHeaderRight = (TextView) findViewById(R.id.tv_header_right);


        tvHeaderLeft.setOnClickListener(this);
        tvHeaderRight.setOnClickListener(this);
    }


    private void init() {

        mPath = getIntent().getStringExtra("path");
        mNewPath = new String(mPath);
        requestCode = getIntent().getIntExtra("requestCode",requestCode);
        initImageFactory();
    }


    private void initImageFactory() {

        if(mImageFactoryCrop == null) {
            mImageFactoryCrop = new ImageFactoryCrop(this,findViewById(R.id.crop_imagefactory_crop));
        }

        mImageFactoryCrop.init(mPath,mScreenWidth, mScreenHeight);

    }


    @Override
    public void onClick(View view) {
        if(view == tvHeaderLeft){
            finish();
        }else if(view == tvHeaderRight){
            Intent intent = new Intent();
            mNewPath = PhotoUtils.savePhotoToSDCard(mImageFactoryCrop.cropAndSave(),requestCode);
            if(null == mNewPath) {

                //请检查SD卡...
                setResult(RESULT_CANCELED);

            } else {

                intent.putExtra("path",mNewPath);
                intent.putExtra("requestCode",requestCode);

            }
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}