package com.example.yangl.androidsample.uiTools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yangl.androidsample.R;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/25
 * version:
 * update:
 */

public class MRecyclerViewItemDecoration extends RecyclerView.ItemDecoration {

    private Context context;
    private int lineHeight;
    private int lineColor;
    private int oriention;
    private boolean lastShowLine = false;
    private Paint paint;

    /**
     *
     * @param context
     */
    public MRecyclerViewItemDecoration(Context context){
        this(context , (int)context.getResources().getDimension(R.dimen.recycler_item_height) , R.color.mLineColor ,LinearLayout.HORIZONTAL ,false);
    }

    /**
     *
     * @param context
     * @param lineHeightPix 分割线高度 单位为dp
     * @param lineColor 分割线颜色
     * @param orientation 分割线走势 水平 竖直
     * @param lastShowLine 最后一个item是否显示分割线
     */
    public MRecyclerViewItemDecoration(Context context , int lineHeightPix , @ColorRes int lineColor , int orientation , boolean lastShowLine) {
        this.context = context;
        this.lineHeight = lineHeightPix;
        this.lineColor = lineColor;
        this.oriention = orientation;
        this.lastShowLine = lastShowLine;
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context,R.color.mLineColor));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(oriention == LinearLayout.HORIZONTAL){
            drawHorizontalLine(c,parent);
        }else if(oriention == LinearLayout.VERTICAL){
            drawVerticalLine(c,parent);
        }else {
            throw new IllegalArgumentException("方向只能为Vertical或Horizontal");
        }
    }

    //画横线
    private void drawHorizontalLine(Canvas c , RecyclerView parent){
        int left = parent.getPaddingLeft();
        int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        final int childSize = layoutManager.getItemCount();
        for(int i = 0 ; i< childSize ;i++){
            if(!lastShowLine && i == childSize - 1){
                continue;
            }
            View childView = parent.getChildAt(i);
            Log.d("childView", "drawHorizontalLine: "+childView.getId());
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int top = childView.getBottom()+layoutParams.bottomMargin;
            int bottom = top+lineHeight;
            c.drawRect(left,top,right,bottom,paint);
        }

    }

    //画竖线
    private void drawVerticalLine(Canvas c , RecyclerView parent){
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        final int childSize = layoutManager.getItemCount();
        for (int i = 0; i < childSize; i++) {
            if(!lastShowLine && i == childSize - 1){
                continue;
            }
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + lineHeight;
            c.drawRect(left, top, right, bottom, paint);
        }
    }

    //设置每个item之间的距离，用于显示分割线
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int position = layoutManager.getPosition(view);
        if(!lastShowLine && position == layoutManager.getItemCount() - 1){
            outRect.set(0,0,0,0);
        }else {
            if(oriention == LinearLayout.HORIZONTAL){
                outRect.set(0,0,0,lineHeight);
            }else {
                outRect.set(0,0,lineHeight,0);
            }
        }

    }
}
