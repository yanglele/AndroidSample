package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.yangl.androidsample.IRecyclerViewSample.irecyclerCoordinatory.IRecyclerViewWithListenerActivity;
import com.example.yangl.androidsample.IRecyclerViewSample.irecyclerSimple.IrecyclerViewSimpleActivity;
import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.recyclerView.AnchorRecyclerViewActivity;
import com.example.yangl.androidsample.recyclerView.GridRecyclerViewActivity;
import com.example.yangl.androidsample.recyclerView.LinearRecyclerViewActivity;
import com.example.yangl.androidsample.recyclerView.RandomStaggeredRecyclerActivity;
import com.example.yangl.androidsample.recyclerView.StaggeredRecyclerViewActivity;
import com.example.yangl.androidsample.someActivity.BackGroundActivity;
import com.example.yangl.androidsample.someActivity.FrameCenterLayoutActivity;
import com.example.yangl.androidsample.someActivity.RelativeMarginLayoutActivity;
import com.example.yangl.androidsample.zhihuAd.ZhiHuAdActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowMyUIActivity extends AppCompatActivity {

    @BindView(R.id.show_zhihu_ad)
    Button showZhihuAd;
    @BindView(R.id.recycler_view_listener)
    Button recyclerViewListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_ui);
        ButterKnife.bind(this);
    }

    private void jump2targetActivity(Class<?> cls){
        Intent intent = new Intent(ShowMyUIActivity.this, cls);
        startActivity(intent);
    }

    @OnClick({R.id.frameLayoutCenter,R.id.show_zhihu_ad, R.id.recycler_view_simple, R.id.recycler_view_listener,R.id.back_padding, R.id.recycler_linear_layout, R.id.recycler_grid_layout, R.id.recycler_staggered_layout, R.id.recycler_random_staggered_layout ,R.id.recycler_anchor_layout,R.id.layout_margin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.show_zhihu_ad:
                jump2targetActivity(ZhiHuAdActivity.class);
                break;
            case R.id.recycler_view_simple:
                jump2targetActivity(IrecyclerViewSimpleActivity.class);
                break;
            case R.id.recycler_view_listener:
                jump2targetActivity(IRecyclerViewWithListenerActivity.class);
                break;
            case R.id.recycler_linear_layout:
                jump2targetActivity(LinearRecyclerViewActivity.class);
                break;
            case R.id.recycler_grid_layout:
                jump2targetActivity(GridRecyclerViewActivity.class);
                break;
            case R.id.recycler_staggered_layout:
                jump2targetActivity(StaggeredRecyclerViewActivity.class);
                break;
            case R.id.recycler_random_staggered_layout:
                jump2targetActivity(RandomStaggeredRecyclerActivity.class);
                break;
            case R.id.recycler_anchor_layout :
                jump2targetActivity(AnchorRecyclerViewActivity.class);
                break;
            case R.id.back_padding :
                jump2targetActivity(BackGroundActivity.class);
                break;
            case R.id.layout_margin:
                jump2targetActivity(RelativeMarginLayoutActivity.class);break;
            case R.id.frameLayoutCenter:
                jump2targetActivity(FrameCenterLayoutActivity.class);break;
            default:break;
        }
    }
}
