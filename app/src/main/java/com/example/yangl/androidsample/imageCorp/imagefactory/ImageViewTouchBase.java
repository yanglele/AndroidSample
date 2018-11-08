package com.example.yangl.androidsample.imageCorp.imagefactory;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;

/**
 * Created by Litp on 2016/08/29.
 */
public class ImageViewTouchBase extends ImageView {


    //This matrix is recomputed when we go from the thumbnail image to the full size image
    protected Matrix mBaseMatrix = new Matrix();

    //This matrixx remains the same when we go from the thumbnail image to the full size image
    protected Matrix mSuppMatrix = new Matrix();

    //This is the final matrix which is computed as the concatentation of the base matrix and the supplementary matrix.
    private final Matrix mDisplayMatrix = new Matrix();

    private final float[] mMatrixValues = new float[9];

    final public RotateBitmap mBitmapDisplayed = new RotateBitmap(null);

    int mThisWidth = -1, mThisHeight = -1;

    float mMaxZoom;

    /**
     * 高亮状态
     * */
    public static final int STATE_HIGHLIGHT = 0x0;

    /**
     * 涂鸦
     * */
     public static final int STATE_DOODLE = STATE_HIGHLIGHT + 1;

    public static final int STATE_NONE = STATE_HIGHLIGHT + 2;

    protected  int mState = STATE_HIGHLIGHT;

    public interface Recycler {

        public void recycle(Bitmap b);

    }

    public void setRecycler(Recycler r) {
        mRecycler = r;
    }

    private Recycler mRecycler;


    protected Handler mHandler = new Handler();

    protected int mLastXTouchPos;
    protected int mLastYTouchPos;


    public ImageViewTouchBase(Context context) {
        super(context);
        init();
    }

    public ImageViewTouchBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageViewTouchBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private Runnable mOnLayoutRunnable = null;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mThisWidth = right - left;
        mThisHeight = bottom - top;

        Runnable r = mOnLayoutRunnable;
        if( r != null ) {
            mOnLayoutRunnable = null;
            r.run();
        }

        if(mBitmapDisplayed.getBitmap() != null) {

            getProperBaseMatrix(mBitmapDisplayed, mBaseMatrix);

            setImageMatrix(getImageViewMatrix());

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK && getScale() > 1.0f) {

            zoomTo(1.0f);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void init() {

        setScaleType(ScaleType.MATRIX);

    }

    public void clear() {

        setImageBitmapResetBase(null,true);
    }

    public void setImageBitmapResetBase(final Bitmap bitmap, final boolean resetSupp) {

        setImageRotateBitmapResetBase(new RotateBitmap(bitmap),resetSupp);

    }


    public void setImageRotateBitmapResetBase(final RotateBitmap bitmap,final boolean resetSupp) {

        final int viewWidth = getWidth(); // ImageView : width;

        if(viewWidth <= 0) {

            mOnLayoutRunnable = new Runnable() {
                @Override
                public void run() {
                    setImageRotateBitmapResetBase(bitmap,resetSupp);
                }
            };

            return;
        }

        if(bitmap.getBitmap() != null) {

                getProperBaseMatrix(bitmap,mBaseMatrix);
                setImageBitmap(bitmap.getBitmap(),bitmap.getRotation());

        }else {

            mBaseMatrix.reset();
            setImageBitmap(null); // ImageView.setImageBitmap

        }

        if(resetSupp) {
            mSuppMatrix.reset();
        }

        setImageMatrix(getImageViewMatrix()); // ImageView.setImageMatrix(~~);

        mMaxZoom = maxZoom();

    }

    protected Matrix getImageViewMatrix() {

        mDisplayMatrix.set(mBaseMatrix);
        mDisplayMatrix.postConcat(mSuppMatrix);

        return mDisplayMatrix;

    }

    protected float maxZoom() {

        if(mBitmapDisplayed.getBitmap() == null) {
            return 1f;
        }

        float fw = (float) mBitmapDisplayed.getWidth() / (float) mThisWidth;
        float fh = (float) mBitmapDisplayed.getHeight() / (float) mThisHeight;

        float max = Math.max(fw,fh) * 4;
        max = (max < 1.0f) ? 1.0f : max;

        return max;

    }

    private void getProperBaseMatrix(RotateBitmap bitmap, Matrix matrix) {

        float viewWidth = getWidth();  // ImageView width
        float viewHeight = getHeight(); // ImageView Height;

        float w = bitmap.getWidth();
        float h = bitmap.getHeight();

        matrix.reset(); // mBaseMatrix.reset() 重置

        float widthScale = Math.min( viewWidth / w, 2.0f); // 上限两倍
        float heightScale = Math.min(viewHeight / h , 2.0f);
        float scale = Math.min(widthScale, heightScale);

        matrix.postConcat(bitmap.getRotateMatrix()); // RotateBitmap.getRotateMatrix();
        matrix.postScale(scale,scale); //

        // Matrix 居中显示
        matrix.postTranslate( (viewWidth - w * scale) / 2F,(viewHeight - h * scale) / 2F);

    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {

        setImageBitmap(bitmap,0);

    }

    private void setImageBitmap(Bitmap bitmap ,int rotation) {

        super.setImageBitmap(bitmap);

        Drawable d = getDrawable();
        if(d != null) {
            d.setDither(true);
        }

        Bitmap old = mBitmapDisplayed.getBitmap();
        mBitmapDisplayed.setBitmap(bitmap);
        mBitmapDisplayed.setRotation(rotation); // 0

        if(old != null && old != bitmap && mRecycler != null) {

            mRecycler.recycle(old);

        }

    }

    public void center(boolean horizontal, boolean vertical) {

        if(mBitmapDisplayed.getBitmap() == null) {
            return;
        }

        Matrix m = getImageViewMatrix();

        RectF rect = new RectF(0, 0, mBitmapDisplayed.getBitmap().getWidth(), mBitmapDisplayed.getBitmap().getHeight());

        m.mapRect(rect);

        float height = rect.height();
        float width = rect.width();

        float deltaX = 0, deltaY = 0;

        if(vertical) {

            int viewHeight = getHeight();
            if(height < viewHeight) {
                deltaY = (viewHeight - height) / 2 -rect.top;
            }else if(rect.top > 0) {
                deltaY = -rect.top;
            }else if(rect.bottom < viewHeight) {
                deltaY = getHeight() - rect.bottom;
            }

        }

        if( horizontal ) {

            int viewWidth = getWidth();
            if(width < viewWidth) {
                deltaX = (viewWidth - width) / 2 -rect.left;
            }else if(rect.left > 0) {
                deltaX = -rect.left;
            }else if(rect.right < viewWidth) {
                deltaX = viewWidth - rect.right;
            }
        }

        postTranslate(deltaX, deltaY);

        setImageMatrix(getImageViewMatrix());
    }

    protected void postTranslate(float dx, float dy) {

        mSuppMatrix.postTranslate(dx,dy);

    }

    protected void panBy(float dx, float dy) {

        postTranslate(dx, dy);
        setImageMatrix(getImageViewMatrix());

    }

    protected float getValue(Matrix matrix, int whichValue) {

        matrix.getValues(mMatrixValues);
        return mMatrixValues[whichValue];

    }

    protected float getScale(Matrix matrix) {

        return getValue(matrix, Matrix.MSCALE_X);

    }

    public float getScale() {
        return getScale(mSuppMatrix);
    }

    protected void zoomTo(float scale, float centerX, float centerY) {

        Log.w("##", "zoomTo " + scale + "," + centerX + "," + centerY);

        if(scale > mMaxZoom) {
            scale = mMaxZoom;
        }

        float oldScale = getScale();
        float deltaScale = scale / oldScale;

        mSuppMatrix.postScale(deltaScale, deltaScale, centerX, centerY);
        setImageMatrix(getImageViewMatrix());
        center(true,true);

    }

    protected void zoomTo(final float scale, final float centerX, final float centerY,
                          final float durationMs) {

        final float incrementPerMs = (scale - getScale()) / durationMs;
        final float oldScale = getScale();
        final long startTime = System.currentTimeMillis();

        mHandler.post(new Runnable() {
            @Override
            public void run() {

                long now = System.currentTimeMillis();
                float currentMs = Math.min(durationMs, now - startTime);
                float target = oldScale + (incrementPerMs * currentMs);
                zoomTo(target, centerX, centerY);

                if(currentMs < durationMs) {
                    mHandler.post(this);
                }

            }
        });

    }

    protected void zoomTo(float scale) {

        float cx = getWidth() / 2F;
        float cy = getHeight() / 2F;

        zoomTo(scale, cx, cy);

    }

}