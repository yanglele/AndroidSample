package com.example.yangl.androidsample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/4/19
 * version:
 * update:
 */

public class PhotoFragment extends Fragment {

    @BindView(R.id.image_view)
    ImageView imageView;
    Unbinder unbinder;
    private int imageId;
    private Context context;

    public PhotoFragment() {
    }

    public PhotoFragment(@IdRes int imageId, Context context) {
        this.imageId = imageId;
        this.context = context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        unbinder = ButterKnife.bind(this, view);
        initFragment();
        Log.d("PhotoFragment", "onCreateView: "+imageId);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("PhotoFragment", "onPause: "+imageId);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("PhotoFragment", "onStop: "+imageId);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("PhotoFragment", "onResume: "+imageId);
    }

    private void initFragment() {
        imageView.setImageResource(imageId);
    }

    @Override
    public void onDestroyView() {
        Log.d("PhotoFragment", "onDestroyView: "+imageId);
        super.onDestroyView();
        unbinder.unbind();
    }
}
