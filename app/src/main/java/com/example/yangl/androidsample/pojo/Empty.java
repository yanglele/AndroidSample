package com.example.yangl.androidsample.pojo;

import android.support.annotation.Dimension;
import android.support.annotation.Px;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/23
 * version:
 * update:
 */

public class Empty {

    @Px private int viewHeight;

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public Empty(@Px int viewHeight) {
        this.viewHeight = viewHeight;
    }
}
