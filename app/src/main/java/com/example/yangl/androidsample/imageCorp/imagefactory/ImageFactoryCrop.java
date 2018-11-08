package com.example.yangl.androidsample.imageCorp.imagefactory;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.imageCorp.PhotoUtils;


/**
 * Created by Litp on 2016/08/29.
 */
public class ImageFactoryCrop extends ImageFactory {

    public static final int SHOW_PROGRESS = 1;
    public static final int REMOVE_PROGRESS = 2;

    private CropImageView mCivDisplay;
    private ProgressBar mPbBar;

    private String mPath;
    private Bitmap mBitmap;
    private CropImage mCropImage;


    public ImageFactoryCrop(ImageFactoryActivity activity, View root) {
        super(activity, root);
    }

    @Override
    protected void initViews() {

        mCivDisplay = (CropImageView) findViewById(R.id.imagefactory_crop_civ_display);
        mPbBar = (ProgressBar) findViewById(R.id.imagefactory_crop_pb_progressbar);

    }

    @Override
    protected void initEvents() {

    }


    public void init(String path, int w, int h) {

        mPath = path;
        mBitmap = PhotoUtils.createBitmap(mPath, w, h);

        if(mBitmap != null) {
            resetImageView(mBitmap);
        }
    }

    private void resetImageView(Bitmap b) {

        mCivDisplay.clear();  // ImageView
        mCivDisplay.setImageBitmap(b);
        mCivDisplay.setImageBitmapResetBase(b,true);

        mCropImage = new CropImage(mContext,mCivDisplay, handler);
        mCropImage.crop(b);

    }

    public Bitmap cropAndSave() {

        return mCropImage.cropAndSave();

    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_PROGRESS:
                    mPbBar.setVisibility(View.VISIBLE);
                    break;
                case REMOVE_PROGRESS:
                    handler.removeMessages(SHOW_PROGRESS);
                    mPbBar.setVisibility(View.INVISIBLE);
                    break;
            }
        }

    };
}
