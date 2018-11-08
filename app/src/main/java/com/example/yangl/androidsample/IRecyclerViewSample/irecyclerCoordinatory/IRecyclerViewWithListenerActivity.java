package com.example.yangl.androidsample.IRecyclerViewSample.irecyclerCoordinatory;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.irecyclerlib.IRecyclerView;
import com.example.irecyclerlib.OnLoadMoreListener;
import com.example.yangl.androidsample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IRecyclerViewWithListenerActivity extends AppCompatActivity implements OnLoadMoreListener {

    @BindView(R.id.recycler_view)
    IRecyclerView recyclerView;

    LoadMoreFooterView loadMoreFooterView;
    MRecyclerViewAdapter adapter;
    List list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irecycler_view_with_listener);
        ButterKnife.bind(this);

        for(int i=0 ;i <10 ;i++){
            list.add(i+"");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnLoadMoreListener(this);
        recyclerView.setLoadMoreEnabled(true);
        loadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();
        adapter = new MRecyclerViewAdapter(this,list);
        recyclerView.setIAdapter(adapter);
    }



    private void loadMore(){
        for(int i = 0;i<10;i++){
            list.add(i+"");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        if(loadMoreFooterView.canLoadMore() && adapter.getItemCount() > 0){
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
            loadMore();
        }
    }
}
