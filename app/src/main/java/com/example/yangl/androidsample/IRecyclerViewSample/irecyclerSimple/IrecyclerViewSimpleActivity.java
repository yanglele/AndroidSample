package com.example.yangl.androidsample.IRecyclerViewSample.irecyclerSimple;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.example.yangl.androidsample.IRecyclerViewSample.irecyclerCoordinatory.LoadMoreFooterView;
import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.recyclerView.DisableLinearLayoutManager;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IrecyclerViewSimpleActivity extends AppCompatActivity implements OnRefreshListener, OnLoadMoreListener {

    private String TAG = IrecyclerViewSimpleActivity.class.getSimpleName();

    @BindView(R.id.recycler_view)
    IRecyclerView iRecyclerView;
    @BindView(R.id.setFreshHeadHeight)
    Button setFreshHeadHeight;
    @BindView(R.id.setFreshContainerHeight)
    Button setFreshContainerHeight;

    private LoadMoreFooterView loadMoreFooterView;
    private SimpleRecyclerViewAdapter adapter;
    private List<String> list = new ArrayList<>();
    private int count = 0;

    public void onFreshHead(View view){
        ViewGroup.LayoutParams layoutParams = iRecyclerView.getRefreshHeaderView().getLayoutParams();
        layoutParams.height = 100;
        iRecyclerView.getRefreshHeaderView().setLayoutParams(layoutParams);
    }

    public void onFreshContainer(View view){
        View parent = (View) iRecyclerView.getRefreshHeaderView().getParent();
        ViewGroup.LayoutParams layoutParams = parent.getLayoutParams();
        layoutParams.height = 200;
        parent.setLayoutParams(layoutParams);
    }

    public void notifyRangeChanged(View view){
        adapter.notifyItemRangeChanged(0,1,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irecycler_view_simple);
        ButterKnife.bind(this);
        addData();

        DisableLinearLayoutManager linearLayoutManager = new DisableLinearLayoutManager(this);
        iRecyclerView.setLayoutManager(linearLayoutManager);
        loadMoreFooterView = (LoadMoreFooterView) iRecyclerView.getLoadMoreFooterView();
        adapter = new SimpleRecyclerViewAdapter(this, list);
        iRecyclerView.setIAdapter(adapter);
        iRecyclerView.setOnRefreshListener(this);
        iRecyclerView.setOnLoadMoreListener(this);
        IDividerItemDecoration divider = new IDividerItemDecoration(this, LinearLayoutManager.VERTICAL, UISizeUtils.dip2px(this, 3), R.color.mNewGreenColor, false);
        iRecyclerView.addItemDecoration(divider);

        View headView = LayoutInflater.from(this).inflate(R.layout.layout_irecyclerview_header, null, false);
        iRecyclerView.addHeaderView(headView);

        View footView = LayoutInflater.from(this).inflate(R.layout.layout_irecyclerview_header, null, false);
        iRecyclerView.addFooterView(footView);
        iRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) iRecyclerView.getLayoutManager();
                if (linearLayoutManager != null) {
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    int firstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    Log.d(TAG, "onScrolled: firstVisibleItemPosition = " + firstVisibleItemPosition);
                    Log.d(TAG, "onScrolled: lastVisibleItemPosition = " + lastVisibleItemPosition);
                    Log.d(TAG, "onScrolled: firstCompletelyVisibleItemPosition = " + firstCompletelyVisibleItemPosition);

                    Log.d(TAG, "onScrolled: recyclerView.getChildAt = " + recyclerView.getChildAt(1));
                    Log.d(TAG, "onScrolled: linearLayoutManager.getChildAt = " + linearLayoutManager.getChildAt(1));
                }
            }
        });
    }

    private void addData() {
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        count++;
    }

    @Override
    public void onRefresh() {
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
        refresh();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                iRecyclerView.setRefreshing(false);
            }
        }
    };

    private void refresh() {
        Toast.makeText(this, "fresh", Toast.LENGTH_SHORT).show();
        list.clear();
        count = 0;
        addData();
        adapter.notifyDataSetChanged();
        iRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                iRecyclerView.setRefreshing(false);
            }
        });
//        iRecyclerView.setRefreshing(false);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
////                    Thread.sleep(0);
//                    Message message = new Message();
//                    message.what = 1;
//                    handler.sendMessage(message);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    private void loadMore() {
        if (count == 3) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);
        } else {
            loadMoreFooterView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                    addData();
                }
            }, 1000);
        }
    }

    @Override
    public void onLoadMore() {
        if (loadMoreFooterView.canLoadMore() && adapter.getItemCount() > 0) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
            loadMore();
        }
    }
}
