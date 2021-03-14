package com.example.yangl.androidsample.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.pojo.Empty;
import com.example.yangl.androidsample.pojo.Image;
import com.example.yangl.androidsample.pojo.MoreImage;
import com.example.yangl.androidsample.pojo.Text;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseAdapter;
import com.example.yangl.androidsample.zhihuAd.baseUtil.BaseViewHolder;

import java.util.List;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/1/19
 * version:
 * update:
 */

public class AnchorRecyclerAdapter extends BaseAdapter<Object,BaseViewHolder> {

    public static final int TEXT_VIEW = R.layout.view_text_view;
    public static final int IMAGE_VIEW = R.layout.simple_image_view_layout;
    public static final int EMPTY_VIEW = R.layout.item_empty_view;
    public static final int MORE_PIC = R.layout.image_view_text;

    private TabMapListener tabMapListener;

    public AnchorRecyclerAdapter(List<Object> list , Context context){
        mList = list;
        mContext = context;
        if(context instanceof TabMapListener){
            tabMapListener = (TabMapListener) context;
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        View view = LayoutInflater.from(mContext).inflate(viewType,parent,false);
        switch (viewType){
            case TEXT_VIEW :
                viewHolder = new TextViewHolder(view);
                break;
            case IMAGE_VIEW :
                viewHolder = new ImageViewHolder(view);
                break;
            case EMPTY_VIEW :
                viewHolder = new EmptyViewHolder(view);
                break;
            case MORE_PIC :
                viewHolder = new MoreImageViewHolder(view);
                break;
            default:break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        if(mList == null || mList.size() <= 0)
            return;
        holder.bindView(mContext,mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder instanceof MoreImageViewHolder){
                    insertMoreItem(position);
                }else {
                    holder.onItemClickListener(mContext,mList.get(position),position);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        Object object = mList.get(position);
        if(object instanceof Text){
            return TEXT_VIEW;
        }else if(object instanceof Image){
            return IMAGE_VIEW;
        }else if(object instanceof Empty){
            return EMPTY_VIEW;
        }else if(object instanceof MoreImage){
            return MORE_PIC;
        }
        throw new IllegalArgumentException("getItemViewType object type do not match!");
    }

    //插入更多图片
    private void insertMoreItem(int position){
        MoreImage moreImage = (MoreImage) mList.get(position);
        List<Image> imageList = moreImage.getImageList();
        mList.remove(position);
        AnchorRecyclerAdapter.this.notifyItemRemoved(position);// you must use itemRemoved before itemInsert if you delete item
        mList.addAll(position,imageList);
        AnchorRecyclerAdapter.this.notifyItemRangeInserted(position,imageList.size());
        if(tabMapListener != null){
            tabMapListener.updateTabMap(position,imageList.size());
        }
    }


    public interface TabMapListener {
        //更新title标签map值
        void updateTabMap(int pos,int count);
    }

}
