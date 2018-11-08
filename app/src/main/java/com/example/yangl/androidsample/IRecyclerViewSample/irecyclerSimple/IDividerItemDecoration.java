package com.example.yangl.androidsample.IRecyclerViewSample.irecyclerSimple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yangl.androidsample.R;


/**
 * desc:IRecyclerView分割线
 * author: jeney
 * email: jeneylu@anjuke.com
 * date: 16/9/5
 */
public class IDividerItemDecoration extends RecyclerView.ItemDecoration {
    /**
     * RecyclerView的布局方向，默认先赋值为纵向布局
     */
    private int mOrientation = LinearLayoutManager.VERTICAL;

    /**
     * item之间分割线的size，默认为1px
     */
    private int mItemSize = 1;

    /**
     * 绘制item分割线的画笔
     */
    private Paint mPaint;

    /**
     * 最后一条item是否显示分割线
     */
    private boolean lastItemShowDivider = true;

    public IDividerItemDecoration(Context context) {
        this(context, LinearLayoutManager.VERTICAL);
    }

    public IDividerItemDecoration(Context context, int orientation) {
        this(context, orientation, 1, R.color.mLineColor, true);
    }

    public IDividerItemDecoration(Context context, int orientation, int mItemSize, int colorRes, boolean lastItemShowDivider) {
        this.mItemSize = mItemSize;
        this.mOrientation = orientation;
        this.lastItemShowDivider = lastItemShowDivider;
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请传入正确的参数");
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(ContextCompat.getColor(context, colorRes));
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /**
     * 绘制纵向 item 分割线
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            if (!lastItemShowDivider) {
                int viewAdapterPos = layoutManager.getPosition(child);
                if (viewAdapterPos == layoutManager.getItemCount() - 1) {
                    continue;
                }
            }
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + mItemSize;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }

    /**
     * 绘制横向 item 分割线
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mItemSize;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }

    /**
     * 设置item分割线的size
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int viewAdapterPos = layoutManager.getPosition(view);
        if (viewAdapterPos == layoutManager.getItemCount() - 1 && !lastItemShowDivider) {
            outRect.set(0, 0, 0, 0);
        } else {
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                outRect.set(0, 0, 0, mItemSize);
            } else {
                outRect.set(0, 0, mItemSize, 0);
            }
        }
    }

    public void setLastItemShowDivider(boolean lastItemShowDivider) {
        this.lastItemShowDivider = lastItemShowDivider;
    }
}
