package com.example.yangl.androidsample.imageCorp.imagefactory;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;


import java.util.concurrent.CountDownLatch;

/**
 * Created by Litp on 2016/08/29.
 */
public class CropImage {


    public boolean mSaving;
    public HighlightView mCrop;

    private Context mContext;
    private Handler mHandler;
    private CropImageView mImageView;
    private Bitmap mBitmap;

    public CropImage(Context context, CropImageView imageView, Handler handler) {

        mContext = context;  // mImageView
        mImageView = imageView;  // mCivDisplay
        mImageView.setCropImage(this);
        mHandler = handler;

    }

    public Bitmap cropAndSave() {

        final Bitmap bmp = onSaveClicked(mBitmap);
        mImageView.mHighlightViews.clear();
        return bmp;

    }

    private Bitmap onSaveClicked(Bitmap bm) {

        if(mSaving) {
            return bm;
        }

        if(mCrop == null)
            return bm;

        mSaving = true;

        Rect r = mCrop.getCropRect();

        int width = r.width();
        int height = r.height();

        Log.w("immomo", "width==" + width + ", height==" + height);
        Bitmap croppedImage = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        {
            Canvas canvas = new Canvas(croppedImage);
            Rect dstRect = new Rect(0,0,width, height);
            canvas.drawBitmap(bm, r, dstRect, null);
        }
        return croppedImage;

    }

    public void crop(Bitmap bm) {  // 裁剪显示

        mBitmap = bm;
        startFaceDetection();

    }

    public void startRotate(float d) {

        if(((Activity)mContext).isFinishing())
            return;

        final float degrees = d;

        showProgressDialog("请稍等...", new Runnable() {
            @Override
            public void run() {

                final CountDownLatch latch = new CountDownLatch(1);

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try{


                        Matrix m = new Matrix();
                        m.setRotate(degrees);
                        Bitmap tb = Bitmap.createBitmap(mBitmap, 0 ,0,
                          mBitmap.getWidth(), mBitmap.getHeight(), m,false);

                        mBitmap = tb;
                        mImageView.resetView(tb);

                        if(mImageView.mHighlightViews.size()> 0) {
                            mCrop = mImageView.mHighlightViews.get(0);
                            mCrop.setFocus(true);
                        }
                        }catch(Exception e) {

                        }
                    }
                });

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        },mHandler);

    }


    private void startFaceDetection() {

        if(((Activity)mContext).isFinishing())
            return;

        showProgressDialog("请稍等...", new Runnable() {
            @Override
            public void run() {

                final CountDownLatch latch = new CountDownLatch(1);
                final Bitmap b = mBitmap;

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        if(b != mBitmap && b!= null) {
                            mImageView.setImageBitmapResetBase(b,true);
                            mBitmap.recycle();
                            mBitmap = b;
                        }


                        if(mImageView.getScale() == 1.0F) {
                            mImageView.center(true,true);
                        }

                        latch.countDown();

                    }
                });

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mRunFaceDetection.run();

            }
        },mHandler);

    }

    private void showProgressDialog(String msg, Runnable job, Handler handler) {

        new Thread( new BackgroundJob(msg, job, handler)).start();

    }

    Runnable mRunFaceDetection = new Runnable() {

        float mScale = 1F;
        Matrix mImageMatrix;

        private void makeDefault() {

            HighlightView hv = new HighlightView(mImageView, false);

            int width = mBitmap.getWidth();
            int height = mBitmap.getHeight();

            Rect imageRect = new Rect(0, 0, width, height);

            int cropWidth = Math.min(width, height) * 4 / 5;
            int cropHeight = cropWidth;

            int x = (width - cropWidth) / 2;
            int y = (height - cropHeight) / 2;

            RectF cropRect = new RectF(x, y, x+cropWidth, y+cropHeight);
            hv.setup(mImageMatrix, imageRect, cropRect, false, true);

            mImageView.add(hv);

        }

        @Override
        public void run() {

            mImageMatrix = mImageView.getImageMatrix();

            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    makeDefault();


                    if(mImageView.mHighlightViews.size() > 0) {

                        mCrop = mImageView.mHighlightViews.get(0);
                        mCrop.setFocus(true);
                    }

                    mImageView.invalidate();
                }
            });
        }
    };

    class BackgroundJob implements Runnable {

        private String message;
        private Runnable mJob;
        private Handler mHandler;

        public BackgroundJob(String m , Runnable job , Handler handler) {

            message = m;
            mJob = job;
            mHandler = handler;

        }

        @Override
        public void run() {

            final CountDownLatch latch = new CountDownLatch(1);

            mHandler.post(new Runnable() {
                @Override
                public void run() {

                    mHandler.sendMessage(mHandler.obtainMessage(ImageFactoryCrop.SHOW_PROGRESS));

                    latch.countDown();
                }
            });

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                mJob.run();
            }finally {

                mHandler.sendMessage(mHandler.obtainMessage(ImageFactoryCrop.REMOVE_PROGRESS));

            }

        }
    }


}
