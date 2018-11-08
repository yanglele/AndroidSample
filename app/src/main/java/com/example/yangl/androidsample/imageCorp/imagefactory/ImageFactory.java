package com.example.yangl.androidsample.imageCorp.imagefactory;

import android.content.Context;
import android.view.View;

/**
 * Created by Litp on 2016/08/29.
 */
public abstract class ImageFactory {

    protected ImageFactoryActivity mActivity;
    protected Context mContext;
    private View mRootView;


    public ImageFactory(ImageFactoryActivity activity,View root) {

        mActivity = activity;
        mContext = (Context) activity;
        mRootView = root;

        initViews();

        initEvents();
    }


    protected View findViewById(int resId) {

        return mRootView.findViewById(resId);

    }

    protected abstract void initViews();

    protected abstract void initEvents();

}
