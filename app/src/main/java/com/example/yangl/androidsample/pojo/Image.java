package com.example.yangl.androidsample.pojo;

import android.support.annotation.DrawableRes;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/19
 * version:
 * update:
 */

public class Image {
    private String name;
    @DrawableRes private int imageRes;

    public Image(@DrawableRes int imageRes) {
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(String uri) {
        this.imageRes = imageRes;
    }
}
