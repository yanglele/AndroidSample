package com.example.yangl.androidsample.recyclerView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Desc:可以设置recyclerview禁止滑动
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/8/20
 * version:
 * update:
 */
public class DisableLinearLayoutManager extends LinearLayoutManager {

    private boolean scrollEnable = true;

    public DisableLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnable(boolean scrollEnable){
        this.scrollEnable = scrollEnable;
    }

    @Override
    public boolean canScrollVertically() {
        return scrollEnable && super.canScrollVertically();
    }
}
