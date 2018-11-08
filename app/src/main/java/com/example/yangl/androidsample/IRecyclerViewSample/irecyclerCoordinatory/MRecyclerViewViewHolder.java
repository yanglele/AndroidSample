package com.example.yangl.androidsample.IRecyclerViewSample.irecyclerCoordinatory;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseViewHolder;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/26
 * version:
 * update:
 */

public class MRecyclerViewViewHolder extends BaseViewHolder<String> {

    private TextView textView;


    public MRecyclerViewViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.simple_text_view);
    }

    @Override
    public void bindView(Context context, String model) {
        textView.setText(model);
    }

    @Override
    public void onItemClickListener(Context context, String model, int position) {

    }

}
