package com.example.yangl.androidsample.recyclerView;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.pojo.Text;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseViewHolder;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/19
 * version:
 * update:
 */

public class TextViewHolder extends BaseViewHolder<Text> {

    private TextView textView;

    public TextViewHolder(View itemView) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.text_view);
    }

    @Override
    public void bindView(Context context, Text model) {
        textView.setText(model.getText());
    }

    @Override
    public void onItemClickListener(Context context, Text model, int position) {
        Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
    }


}
