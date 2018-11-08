package com.example.yangl.androidsample.pojo;

import java.util.List;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/24
 * version:
 * update:
 */

public class MoreImage {
    private List<Image> imageList;

    public MoreImage(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
