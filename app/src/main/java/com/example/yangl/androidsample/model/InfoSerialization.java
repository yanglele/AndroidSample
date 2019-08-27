package com.example.yangl.androidsample.model;

import java.io.Serializable;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/8/17
 * version:
 * update:
 */
public class InfoSerialization implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public InfoSerialization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
