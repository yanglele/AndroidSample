package com.example.yangl.androidsample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/8/27
 * version:
 * update:
 */
public class Info implements Parcelable {
    private String name;

    public Info(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    protected Info(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel source) {
            return new Info(source);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
}
