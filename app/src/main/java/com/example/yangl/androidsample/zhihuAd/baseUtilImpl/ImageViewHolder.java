package com.example.yangl.androidsample.zhihuAd.baseUtilImpl;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseViewHolder;
import com.example.yangl.androidsample.zhihuAd.dataBean.ImageInfo;


/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/20
 * version:
 * update:
 */

public class ImageViewHolder extends BaseViewHolder<ImageInfo> {

    private ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);
    }

    @Override
    public void bindView(Context context, ImageInfo model) {
        imageView.setImageResource(model.getPicId());
    }

    @Override
    public void onItemClickListener(Context context, ImageInfo model, int position) {

    }


}
