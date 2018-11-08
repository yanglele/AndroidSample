package com.example.yangl.androidsample.recyclerView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.pojo.Image;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseViewHolder;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/19
 * version:
 * update:
 */

public class ImageViewHolder extends BaseViewHolder<Image> {

    private ImageView imageView;



    public ImageViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.image_view);
    }

    @Override
    public void bindView(Context context, Image model) {
        imageView.setImageResource(model.getImageRes());
    }

    @Override
    public void onItemClickListener(Context context, Image model, int position) {
        Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
    }

}
