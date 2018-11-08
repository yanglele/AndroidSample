package com.example.yangl.androidsample.recyclerView;

import android.graphics.Rect;
import android.support.annotation.Px;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/28
 * version:
 * update:
 */

public class DividerGridLayout extends RecyclerView.ItemDecoration {

    @Px
    private int dividerWidth;
    @Px
    private int dividerHeight;
    private int eachItemCount;

    /**
     *
     * @param eachItemCount 每个item所占空间（指每行多列的item）
     * @param dividerWidth item 列间间距
     * @param dividerHeight item 行间间距
     */
    public DividerGridLayout(int eachItemCount, @Px int dividerWidth , @Px int dividerHeight)
    {
        this.eachItemCount = eachItemCount;
        this.dividerWidth = dividerWidth;
        this.dividerHeight = dividerHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //若设置了每行显示个数与每个item宽度后，GridLayout默认靠左显示并且每个item右侧会有等宽的间距，因此此处需要移动item的位置
        //此处两端的item靠边显示，若为一行三个item，则第一个item不需移动，第二个item移动一个dividerWidth,第三个需要移动2个dividerWidth
        //ps：一共6个dividerWidth 画个图比较好理解
        if(eachItemCount <= 0)
            throw new IllegalArgumentException("eachItemCount cannot be negative or zero!");
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int spanSize = layoutParams.getSpanSize();
        if(spanSize == eachItemCount){
            int spanIndex = layoutParams.getSpanIndex();
            int itemPosition = spanIndex/eachItemCount;
            outRect.left = dividerWidth*itemPosition;
        }
        outRect.bottom = dividerHeight;
    }
}