package com.example.eventbussource.model;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/8/12
 * version:
 * update:
 */
public class NameInfo extends Info implements SimpleInter{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NameInfo(String info, String name) {
        super(info);
        this.name = name;
    }

    public NameInfo(String info) {
        super(info);
    }
}
