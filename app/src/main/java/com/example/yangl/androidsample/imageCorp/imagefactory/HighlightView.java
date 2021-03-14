package com.example.yangl.androidsample.imageCorp.imagefactory;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.yangl.androidsample.R;


/**
 * Created by Litp on 2016/08/29.
 */
public class HighlightView {

    private static final String TAG = "HighlightView";

    View mContext;
    public boolean rectange;
    public static final int GROW_NONE = (1 << 0);
    public static final int GROW_LEFT_EDGE = (1<<1);
    public static final int GROW_RIGHT_EDGE = (1 << 2);
    public static final int GROW_TOP_EDGE = (1 << 3);
    public static final int GROW_BOTTOM_EDGE = (1 << 4);
    public static final int MOVE = (1 << 5);


    public HighlightView(View ctx) {

        mContext = ctx;

    }

    /**
     *

     * @param ctx View
     * @param rectange  裁剪是否方形
     */
    public HighlightView(View ctx, boolean rectange) {

        mContext = ctx;
        this.rectange = rectange;

    }

    private void init() {

        android.content.res.Resources resources = mContext.getResources();
        mResizeDrawableDiagonal = resources.getDrawable(R.drawable.picture_cut_button_normal);
        mResizeDrawableDiagonal2 = resources.getDrawable(R.drawable.picture_cut_button_normal);


    }

    public boolean mIsFocused;
    boolean mHidden;

    public boolean hasFocus() {
        return mIsFocused;
    }

    public void setFocus(boolean focus) {
        mIsFocused = focus;
    }

    public void setHidden(boolean hidden) {
        mHidden = hidden;
    }

    public void draw(Canvas canvas) {

        if( mHidden )
            return;

        canvas.save();
        Path path = new Path();

        if(! hasFocus()) {
            mOutlinePaint.setColor(0xFF000000);
            canvas.drawRect(mDrawRect,mOutlinePaint);
        }else {

            Rect viewDrawingRect = new Rect();
            mContext.getDrawingRect(viewDrawingRect);

            path.addRect(new RectF(mDrawRect), Path.Direction.CW);
            mOutlinePaint.setColor(0xFFFFFFFF);

            Region region = new Region();
            region.set(viewDrawingRect);
            region.op(mDrawRect, Region.Op.DIFFERENCE);
            RegionIterator iter = new RegionIterator(region);
            Rect r = new Rect();
            while( iter.next(r)) {
                canvas.drawRect(r, mFocusPaint);
            }

            canvas.restore();
            canvas.drawPath(path, mOutlinePaint);

            if(mMode == ModifyMode.Grow) {

            }

            int left = mDrawRect.left + 1;
            int right = mDrawRect.right + 1;
            int top = mDrawRect.top + 4;
            int bottom = mDrawRect.bottom + 2;

            int widthWidth = mResizeDrawableDiagonal.getIntrinsicWidth() / 2;
            int widthHeight = mResizeDrawableDiagonal.getIntrinsicHeight() / 2;

            mResizeDrawableDiagonal2.setBounds(left-widthWidth,top-widthHeight,
                    left+widthWidth, top+widthHeight);
            mResizeDrawableDiagonal2.draw(canvas);

            mResizeDrawableDiagonal.setBounds(right - widthWidth, top - widthHeight,
                    right+widthWidth,top+widthHeight);
            mResizeDrawableDiagonal.draw(canvas);

            mResizeDrawableDiagonal.setBounds(left - widthWidth,bottom-widthHeight,
                    left+widthWidth,bottom+widthHeight);
            mResizeDrawableDiagonal.draw(canvas);

            mResizeDrawableDiagonal2.setBounds(right - widthWidth,bottom-widthHeight,
                    right+widthWidth,bottom+widthHeight);
            mResizeDrawableDiagonal2.draw(canvas);

        }

    }

    public void setMode(ModifyMode mode) {
        if(mode != mMode) {

            mMode = mode;
            mContext.invalidate();

        }
    }

    public int getHit(float x ,float y) {

        Rect r = computeLayout();
        final float hysteresis = 20F;
        int retval = GROW_NONE;

        boolean verticalCheck = (y >= r.top - hysteresis) && (y < r.bottom +hysteresis);

        boolean horizCheck = (x >= r.left - hysteresis) && (x < r.right + hysteresis);

        if( (Math.abs(r.left - x) < hysteresis ) && verticalCheck ) // 左边
            retval |= GROW_LEFT_EDGE;

        if( (Math.abs(r.right - x) < hysteresis ) && verticalCheck ) //右边
            retval |= GROW_RIGHT_EDGE;

        if( (Math.abs(r.top - y) < hysteresis) && horizCheck ) //上边
            retval |= GROW_TOP_EDGE;

        if( (Math.abs(r.bottom - y) < hysteresis ) && horizCheck ) //下边
            retval |= GROW_BOTTOM_EDGE;

        if(retval == GROW_NONE && r.contains((int)x, (int)y))
            retval = MOVE;

        return retval;

    }

    public void handleMotion(int edge , float dx, float dy) {

        Rect r = computeLayout();

        if(edge == GROW_NONE) {
            return;
        }else if( edge == MOVE ) {

            moveBy(dx * (mCropRect.width() / r.width()),
                    dy * (mCropRect.height() / r.height()));

        }else {

            if(((GROW_LEFT_EDGE | GROW_RIGHT_EDGE) & edge) == 0) {
                dx = 0;
            }

            if(((GROW_TOP_EDGE | GROW_BOTTOM_EDGE) & edge) == 0) {
                dy = 0;
            }

            float xDelta = dx * (mCropRect.width() / r.width());
            float yDelta = dy * (mCropRect.height() / r.height());

            growBy( ((( edge & GROW_LEFT_EDGE) != 0) ? -1 : 1)* xDelta,
                    ((( edge & GROW_TOP_EDGE) != 0) ? -1 : 1)* yDelta);

        }

    }


    void moveBy(float dx, float dy) {

        Rect invalRect = new Rect(mDrawRect);

        mCropRect.offset(dx,dy);

        mCropRect.offset(Math.max(0, mImageRect.left - mCropRect.left),
                Math.max(0, mImageRect.top - mCropRect.top));

        mCropRect.offset(Math.min(0, mImageRect.right - mCropRect.right),
                Math.min(0, mImageRect.bottom - mCropRect.bottom));

        mDrawRect = computeLayout();

        invalRect.union(mDrawRect);
        invalRect.inset(-10,-10);

        mContext.invalidate();

    }

//    void growBy(float dx, float dy) {
//
//        if(mMaintainAspectRatio) {
//            if(dx != 0) {
//                dy = dx / mInitialAspectRatio;
//            }else if (dy != 0) {
//                dx = dy * mInitialAspectRatio;
//            }
//        }
//
//        RectF r = new RectF(mCropRect);
//
//        if(dx > 0F && r.width() + 2*dx > mImageRect.width() ) {
//
//            float adjustment = (mImageRect.width() - r.width()) / 2F;
//            dx = adjustment;
//            if(mMaintainAspectRatio)
//                dy = dx / mInitialAspectRatio;
//        }
//
//        if(dy > 0F && r.height() + 2*dy > mImageRect.height() ) {
//            float adjustment = (mImageRect.height()-r.height()) / 2F;
//            dy = adjustment;
//            if(mMaintainAspectRatio)
//                dx = dy * mInitialAspectRatio;
//        }
//
//        r.inset(-dx, -dy);
//
//        final float widthCap = 25F;
//        if(r.width() < widthCap)
//            return ;
//
//        float heightCap = mMaintainAspectRatio ? (widthCap / mInitialAspectRatio) : widthCap;
//        if( r.height() < heightCap)
//            return;
//
//        if(r.left < mImageRect.left) {
//            r.offset(mImageRect.left - r.left , 0F);
//        }else if (r.right > mImageRect.right ) {
//            r.offset(-(r.right - mImageRect.right), 0F);
//        }
//
//        if(r.top < mImageRect.top) {
//            r.offset(0F, mImageRect.top - r.top);
//        }else if( r.bottom> mImageRect.bottom) {
//            r.offset(0F, -(r.bottom - mImageRect.bottom));
//        }
//
//        mCropRect.set(r);
//        mDrawRect = computeLayout();
//        mContext.invalidate();
//
//    }


    // Grows the cropping rectange by (dx, dy) in image1 space.
    void growBy(float dx, float dy) {

        if(!rectange) {
            if (mMaintainAspectRatio) {
                if (dx != 0) {
                    dy = dx / mInitialAspectRatio;

                } else if (dy != 0) {
                    dx = dy * mInitialAspectRatio;
                }
            }
        }

        // Don't let the cropping rectangle grow too fast.
        // Grow at most half of the difference between the image1 rectangle and
        // the cropping rectangle.
        RectF r = new RectF(mCropRect);

        if (dx > 0F && r.width() + 3 * dx > mImageRect.width()) {

            float adjustment = (mImageRect.width() - r.width()) / 2F;
            dx = adjustment;
            if (mMaintainAspectRatio) {
                dy = dx / mInitialAspectRatio;
            }
        }

        if (dy > 0F && r.height() + 3 * dy > mImageRect.height()) {
            float adjustment = (mImageRect.height() - r.height()) / 2F;
            dy = adjustment;
            if (mMaintainAspectRatio) {
                dx = dy * mInitialAspectRatio;
            }
        }

        r.inset(-dx, -dy);

        // Don't let the cropping rectangle shrink too fast.
        final float widthCap = 25F;
        if (r.width() < widthCap) {  //  小于25
            return;
            // r.inset(-(widthCap - r.width()) / 2F, 0F);
        }

        float heightCap = mMaintainAspectRatio ? (widthCap / mInitialAspectRatio)
                : widthCap;
        if (r.height() < heightCap) {
            return;
            // r.inset(0F, -(heightCap - r.height()) / 2F);
        }

        // Put the cropping rectangle inside the image1 rectangle.
        if (r.left < mImageRect.left) {
            r.offset(mImageRect.left - r.left, 0F);
        } else if (r.right > mImageRect.right) {

            r.offset(-(r.right - mImageRect.right), 0);

        }

        if (r.top < mImageRect.top) {

            r.offset(0F, mImageRect.top - r.top);

        } else if (r.bottom > mImageRect.bottom) {

            r.offset(0F, -(r.bottom - mImageRect.bottom));

        }

        mCropRect.set(r);
        mDrawRect = computeLayout();
        mContext.invalidate();

    }

    public Rect getCropRect() {

        return new Rect((int)mCropRect.left, (int)mCropRect.top,
                (int)mCropRect.right, (int)mCropRect.bottom);

    }

    public void invalidate() {

        mDrawRect = computeLayout();

    }

    public void setup(Matrix m,Rect imageRect, RectF cropRect, boolean circle,
                      boolean maintainAspectRatio) {

        mMatrix = new Matrix(m);

        mCropRect = cropRect;
        mImageRect = new RectF(imageRect);
        mMaintainAspectRatio = maintainAspectRatio; // true;
        mCircle = circle;

        mInitialAspectRatio = mCropRect.width() / mCropRect.height();

        mDrawRect = computeLayout();

        mFocusPaint.setARGB(125,50,50,50);
        mNoFocusPaint.setARGB(125,50,50,50);

        mOutlinePaint.setStrokeWidth(3F);
        mOutlinePaint.setStyle(Paint.Style.STROKE);
        mOutlinePaint.setAntiAlias(true);

        mMode = ModifyMode.None;

        init();
    }

    private Rect computeLayout() {

        RectF r = new RectF(mCropRect.left, mCropRect.top, mCropRect.right, mCropRect.bottom);

        mMatrix.mapRect(r); //

        return new Rect(Math.round(r.left), Math.round(r.top), Math.round(r.right), Math.round(r.bottom));

    }

    public enum ModifyMode {
        None, Move, Grow
    }

    private ModifyMode mMode = ModifyMode.None;

    public Rect mDrawRect;
    public RectF mImageRect;
    public RectF mCropRect;
    public Matrix mMatrix;

    private boolean mMaintainAspectRatio = false;
    private float mInitialAspectRatio ;
    private boolean mCircle = false;

    private Drawable mResizeDrawableDiagonal;
    private Drawable mResizeDrawableDiagonal2;

    private final Paint mFocusPaint = new Paint();
    private final Paint mNoFocusPaint = new Paint();
    private final Paint mOutlinePaint = new Paint();

}
