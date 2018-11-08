package com.example.yangl.androidsample.imageCorp.imagefactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by Litp on 2016/08/29.
 */
public class CropImageView extends ImageViewTouchBase {


    public ArrayList<HighlightView> mHighlightViews = new ArrayList<HighlightView>();
    HighlightView mMotionHighlightView = null;
    float mLastX ,mLastY;
    int mMotionEdge;

    private CropImage mCropImage;

    public CropImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(mBitmapDisplayed.getBitmap() != null) {

            for(HighlightView hv : mHighlightViews) {
                hv.mMatrix.set(getImageMatrix());
                hv.invalidate();
                if( hv.mIsFocused) {
                    centerBasedOnHighlightView(hv);
                }
            }

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        CropImage cropImage = mCropImage;

        if(null != cropImage && cropImage.mSaving) {
            return false;
        }

        switch(event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                for( int i=0; i<mHighlightViews.size(); i++) {

                    HighlightView hv = mHighlightViews.get(i);
                    int edge = hv.getHit(event.getX(),event.getY());

                    if(edge != HighlightView.GROW_NONE) {
                        mMotionEdge = edge;
                        mMotionHighlightView = hv;
                        mLastX = event.getX();
                        mLastY = event.getY();

                        mMotionHighlightView.setMode((edge == HighlightView.MOVE) ?
                             HighlightView.ModifyMode.Move : HighlightView.ModifyMode.Grow);
                        break;
                    }

                }
            break;
            case MotionEvent.ACTION_MOVE:

                if(mMotionHighlightView != null) {
                    mMotionHighlightView.handleMotion(mMotionEdge ,event.getX()-mLastX, event.getY()-mLastY);

                    mLastX = event.getX();
                    mLastY = event.getY();

                    ensureVisible(mMotionHighlightView);
                }

//                center(true,true);

                break;
            case MotionEvent.ACTION_UP:

                if(mMotionHighlightView != null) {
                    centerBasedOnHighlightView(mMotionHighlightView);
                    mMotionHighlightView.setMode(HighlightView.ModifyMode.None);
                }
                mMotionHighlightView = null;
                center(true,true);

                break;
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        for(int i = 0; i < mHighlightViews.size(); i++) {
            mHighlightViews.get(i).draw(canvas);
        }
    }

    public void setCropImage(CropImage cropImage) {

        mCropImage = cropImage;

    }

    public void add(HighlightView hv) {

        mHighlightViews.clear();
        mHighlightViews.add(hv);
        invalidate();

    }

    public void resetView(Bitmap b) {

        setImageBitmap(b);
        setImageBitmapResetBase(b,true);
        setImageMatrix(getImageViewMatrix());

        int width = mBitmapDisplayed.getWidth();
        int height = mBitmapDisplayed.getHeight();
        Rect imageRect = new Rect(0, 0, width, height);
        int cropWidth = Math.min(width, height) * 4 / 5;
        int cropHeight = cropWidth;
        int x = (width - cropWidth) / 2;
        int y = (height - cropHeight) / 2;

        RectF cropRect = new RectF(x, y, x+cropWidth, y+cropHeight);
        HighlightView hv = new HighlightView(this);
        hv.setup(getImageViewMatrix(), imageRect, cropRect, false, true);
        hv.setFocus(true);
        add(hv);
        centerBasedOnHighlightView(hv);
        hv.setMode(HighlightView.ModifyMode.None);
        center(true, true);
        invalidate();

    }

    private void ensureVisible(HighlightView hv) {

        Rect r = hv.mDrawRect;

        int panDeltaX1 = Math.max(0, getLeft() - r.left);
        int panDeltaX2 = Math.min(0, getRight() - r.right);

        int panDeltaY1 = Math.max(0, getTop() - r.top);
        int panDeltaY2 = Math.min(0, getBottom() - r.bottom);

        int panDeltaX = panDeltaX1 != 0 ? panDeltaX1 : panDeltaX2;
        int panDeltaY = panDeltaY1 != 0 ? panDeltaY1 : panDeltaY2;

        if(panDeltaX != 0 || panDeltaY != 0) {
            panBy(panDeltaX, panDeltaY);
        }

    }

    private void centerBasedOnHighlightView(HighlightView hv) {

        Rect drawRect = hv.mDrawRect;

        float width = drawRect.width();
        float height = drawRect.height();

        float thisWidth = getWidth();
        float thisHeight = getHeight();

        float z1 = thisWidth / width * 0.6F;
        float z2 = thisHeight / height * 0.6F;

        float zoom = Math.min(z1, z2);
        zoom = zoom * this.getScale();

        zoom = Math.max(1F, zoom);
        if((Math.abs(zoom - getScale()) / zoom) > 0.1) {

            float[] coordinates = new float[] { hv.mCropRect.centerX(),
            hv.mCropRect.centerY()};
            getImageMatrix().mapPoints(coordinates);
            zoomTo(zoom, coordinates[0], coordinates[1], 300F);

        }

        ensureVisible(hv);
    }

    @Override
    protected void zoomTo(float scale, float centerX, float centerY) {

        super.zoomTo(scale, centerX, centerY);

        for(HighlightView hv : mHighlightViews) {
            hv.mMatrix.set(getImageMatrix());
            hv.invalidate();
        }
    }

    @Override
    protected void postTranslate(float deltaX, float deltaY) {
        super.postTranslate(deltaX, deltaY);
        for (int i = 0; i < mHighlightViews.size(); i++) {
            HighlightView hv = mHighlightViews.get(i);
            hv.mMatrix.postTranslate(deltaX, deltaY);
            hv.invalidate();
        }
    }
}
