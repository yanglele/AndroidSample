package com.example.yangl.androidsample.recyclerView;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.pojo.Empty;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/23
 * version:
 * update:
 */

public class EmptyViewHolder extends BaseViewHolder<Empty>{

    LinearLayout linearLayout;

    public EmptyViewHolder(View itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.item_empty_view);
    }

    @Override
    public void bindView(Context context, Empty model) {

        linearLayout.setPadding(0,model.getViewHeight(),0,0);
    }

    @Override
    public void onItemClickListener(Context context, Empty model, int position) {

    }

}
