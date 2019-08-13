package com.example.yangl.androidsample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/8/13
 * version:
 * update:
 */
public class Book implements Parcelable {
    private String name;

    public Book(String name) {
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

    public void readFromParcel(Parcel parcel){
        name=parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    protected Book(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
