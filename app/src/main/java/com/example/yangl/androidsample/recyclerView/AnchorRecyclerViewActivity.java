package com.example.yangl.androidsample.recyclerView;

import android.os.Bundle;
import android.support.annotation.Px;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.pojo.Empty;
import com.example.yangl.androidsample.pojo.Image;
import com.example.yangl.androidsample.pojo.MoreImage;
import com.example.yangl.androidsample.pojo.Text;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnchorRecyclerViewActivity extends AppCompatActivity implements AnchorRecyclerAdapter.TabMapListener {


    @BindView(R.id.recycler_linear_layout)
    LinearLayout linearLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private final int SPAN_COUNT = 3;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    private List<Object> list;
    private Map<String,Integer> map = new HashMap<>();
    private String[] titles = {"全景","视频","户型","样板间"};
    private  AnchorRecyclerAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private boolean shouldMove = false;
    private int shouldMovePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anchor_recycler_view);
        ButterKnife.bind(this);
        generateData();

        adapter = new AnchorRecyclerAdapter(list, this);
        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = adapter.getItemViewType(position);
                if (AnchorRecyclerAdapter.TEXT_VIEW == itemViewType) {
                    return SPAN_COUNT;
                } else if (AnchorRecyclerAdapter.IMAGE_VIEW == itemViewType || AnchorRecyclerAdapter.MORE_PIC == itemViewType) {
                    return SPAN_COUNT / 3;
                }
                return SPAN_COUNT;
            }
        });

        @Px int eachDividerWidth = (UISizeUtils.getScreenWidth(this)-UISizeUtils.dip2px(this,100)*SPAN_COUNT)/(SPAN_COUNT*(SPAN_COUNT-1));
        DividerGridLayout dividerGridLayout = new DividerGridLayout(1,eachDividerWidth,UISizeUtils.dip2px(this,10));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(dividerGridLayout);

        //监听recycler view滑动来选中相应标签
        recyclerView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                Log.d("1", "onScrollChanged: ");
                int firstVisiblePos = gridLayoutManager.findFirstVisibleItemPosition();
                selectTargetTag(firstVisiblePos);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //修正滑动距离，使其项目指定
                if(shouldMove){
                    shouldMove = false;
                    int n = shouldMovePosition - gridLayoutManager.findFirstVisibleItemPosition();
                    if(n >= 0 && n <= recyclerView.getChildCount()){
                        int top = recyclerView.getChildAt(n).getTop();
                        recyclerView.scrollBy(0,top);
                    }
                }

            }
        });
    }



    //根据页面首个可见项目来选中顶部标签
    private void selectTargetTag(int firstVisiblePos){
        for(int i = 0 ; i< map.size();i++){
            Integer index = map.get(titles[i]);
            //使用该判断条件可以解决滑动过快时标签选中落后的问题
            if(firstVisiblePos >= index){
                View view = linearLayout.getChildAt(i);
                view.setSelected(true);
                for(int j = 0;j<linearLayout.getChildCount();j++){
                    if(i != j){
                        view = linearLayout.getChildAt(j);
                        view.setSelected(false);
                    }
                }
            }
        }
    }

    private void generateData() {
        Image image = new Image(R.drawable.icon1);

        list = new ArrayList<>();

        Text text1 = new Text("全景1");
        Text text2 = new Text("视频1");
        Text text5 = new Text("视频2");
        Text text3 = new Text("户型1");
        Text text4 = new Text("样板间1");

        //全景
        map.put(titles[0],0);
        list.add(text1);
        for (int i = 0; i < 5; i++) {
            list.add(image);
        }

        //更多图片
        List<Image> moreImageList0 = new ArrayList<>();
        for(int i=0 ;i<9;i++){
            moreImageList0.add(image);
        }
        MoreImage moreImage0 = new MoreImage(moreImageList0);
        list.add(moreImage0);

        //视频1
        map.put(titles[1],list.size());
        list.add(text2);
        for (int i = 0; i < 6; i++) {
            list.add(image);
        }

        //更多图片
        List<Image> moreImageList = new ArrayList<>();
        for(int i=0 ;i<9;i++){
            moreImageList.add(image);
        }
        MoreImage moreImage = new MoreImage(moreImageList);
        list.add(moreImage);

        //视频2
        list.add(text5);
        for (int i = 0; i < 12; i++) {
            list.add(image);
        }

        //户型
        map.put(titles[2],list.size());
        list.add(text3);
        for (int i = 0; i < 2; i++) {
            list.add(image);
        }


        //样板间
        map.put(titles[3],list.size());
        list.add(text4);
        for (int i = 0; i < 8; i++) {
            list.add(image);
        }


        //空白
        int height = UISizeUtils.getScreenHeight(this)
                - UISizeUtils.getStatusBarHeight(this) //status bar height
                - UISizeUtils.getActionBarHeight(this)
                - UISizeUtils.dip2px(this,30) // anchor bar height
                - UISizeUtils.dip2px(this,100);// 100 : pic height
        Empty emptyView = new Empty(height);
        list.add(emptyView);
    }

    private void moveToPosition(String title){
        int pos = map.get(title);
        int firstItem = gridLayoutManager.findFirstVisibleItemPosition();
        int lastItem = gridLayoutManager.findLastVisibleItemPosition();

        //不需修正
        if(pos <= firstItem){
            recyclerView.scrollToPosition(pos);
        }else if(pos <= lastItem){
            //此处修正
            int top = recyclerView.getChildAt(pos - firstItem).getTop();
            recyclerView.scrollBy(0,top);
        }else {
            //需要修正
            recyclerView.scrollToPosition(pos);
            shouldMove = true;
            shouldMovePosition = pos;
        }
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text1:
                text1.setSelected(true);
                text2.setSelected(false);
                text3.setSelected(false);
                text4.setSelected(false);
                moveToPosition(titles[0]);
                break;
            case R.id.text2:
                text1.setSelected(false);
                text2.setSelected(true);
                text3.setSelected(false);
                text4.setSelected(false);
                moveToPosition(titles[1]);
                break;
            case R.id.text3:
                text1.setSelected(false);
                text2.setSelected(false);
                text3.setSelected(true);
                text4.setSelected(false);
                moveToPosition(titles[2]);
                break;
            case R.id.text4:
                text1.setSelected(false);
                text2.setSelected(false);
                text3.setSelected(false);
                text4.setSelected(true);
                moveToPosition(titles[3]);
                break;
            default:break;
        }
    }


    @Override
    public void updateTabMap(int pos, int count) {
        for(int i=map.size()-1;i>=0;i--){
            if((int)map.get(titles[i]) < pos){
                if(i < map.size() - 1){
                    for(int j=i+1;j<map.size();j++){
                        map.put(titles[j],(int)map.get(titles[j])+count-1); // should minus removed image view
                    }
                    break;
                }
            }
        }
    }
}
