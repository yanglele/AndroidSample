package com.example.yangl.androidsample.zhihuAd.baseUtil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/20
 * version:
 * update:
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindView(Context context , T model);
    public abstract void onItemClickListener(Context context,T model , int position);
}
