package com.example.yangl.androidsample.zhihuAd;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.zhihuAd.baseUtilImpl.MyRecyclerViewAdapter;
import com.example.yangl.androidsample.zhihuAd.dataBean.ImageInfo;
import com.example.yangl.androidsample.zhihuAd.dataBean.TextInfo;
import com.example.yangl.androidsample.zhihuAd.myImageView.AdImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仿知乎广告页面
 */
public class ZhiHuAdActivity extends AppCompatActivity {

    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;

    List list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_ad);
        ButterKnife.bind(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager = new LinearLayoutManager(this));
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, getData());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int beginPos = linearLayoutManager.findFirstVisibleItemPosition();
                int endPos = linearLayoutManager.findLastVisibleItemPosition();
                for (int i = beginPos; i <= endPos; i++) {
                    View view = linearLayoutManager.findViewByPosition(i);
                    AdImageView imageView = (AdImageView) view.findViewById(R.id.image_view);
                    if (imageView != null) {
                        imageView.setDy(linearLayoutManager.getHeight() - view.getTop());
                    }
                }
            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

            }
        });
    }


    private List getData() {
        for (int i = 0; i < 5; i++) {
            list.add(new TextInfo("hello", "The RecyclerView widget is a more advanced and flexible version of ListView. This widget is a container for displaying large data sets that can be scrolled very efficiently by maintaining a limited number of views"));
        }
        list.add(new ImageInfo(R.drawable.image1));
        for (int i = 0; i < 5; i++) {
            list.add(new TextInfo("hello", "The RecyclerView widget is a more advanced and flexible version of ListView. This widget is a container for displaying large data sets that can be scrolled very efficiently by maintaining a limited number of views"));
        }
        return list;
    }
}
