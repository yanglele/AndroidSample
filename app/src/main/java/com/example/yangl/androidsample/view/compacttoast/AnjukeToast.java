package com.example.yangl.androidsample.view.compacttoast;

import android.content.Context;

/**
 * Desc:安居客統一的toast  兼容了sdk25的問題，后期不需要是可以将其继承删除
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/7/23
 * version:
 * update:
 */
public class AnjukeToast extends ToastCompat{

    public AnjukeToast(Context context){
        super(context);
    }
}
