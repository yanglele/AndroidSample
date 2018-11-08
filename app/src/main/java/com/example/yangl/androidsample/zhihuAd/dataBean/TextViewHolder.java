package com.example.yangl.androidsample.zhihuAd.dataBean;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseViewHolder;
import com.example.yangl.androidsample.zhihuAd.dataBean.TextInfo;


/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/20
 * version:
 * update:
 */

public class TextViewHolder extends BaseViewHolder<TextInfo> {

    private TextView title;
    private TextView content;

    public TextViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.text_title);
        content = (TextView) itemView.findViewById(R.id.text_content);
    }

    @Override
    public void bindView(Context context, TextInfo model) {
        title.setText(model.getTitle());
        content.setText(model.getContent());
    }

    @Override
    public void onItemClickListener(Context context, TextInfo model, int position) {

    }


}
