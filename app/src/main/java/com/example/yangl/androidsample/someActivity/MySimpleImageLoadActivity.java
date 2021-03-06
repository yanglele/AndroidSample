package com.example.yangl.androidsample.someActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.example.yangl.androidsample.IRecyclerViewSample.irecyclerSimple.IDividerItemDecoration;
import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.tools.UrlImageView;
import com.example.yangl.androidsample.uiTools.UISizeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySimpleImageLoadActivity extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_simple_image_load);
        ButterKnife.bind(this);

        String[] imageUrls = {
                "http://b.hiphotos.baidu.com/zhidao/pic/item/a6efce1b9d16fdfafee0cfb5b68f8c5495ee7bd8.jpg",
                "http://pic47.nipic.com/20140830/7487939_180041822000_2.jpg",
                "http://pic41.nipic.com/20140518/4135003_102912523000_2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007587&di=06b64ddccae8352dc13e14f6b86db5e7&imgtype=0&src=http%3A%2F%2Fpic37.nipic.com%2F20140113%2F8800276_184927469000_2.png",
                "http://h.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c0f1f6e9efff2b21192138ac0.jpg",
                "http://pic42.nipic.com/20140618/9448607_210533564001_2.jpg",
                "http://pic10.nipic.com/20101027/3578782_201643041706_2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007586&di=1bdab0c944ec33e327438b08532013bc&imgtype=0&src=http%3A%2F%2Fpic25.nipic.com%2F20121205%2F10197997_003647426000_2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007586&di=217a35705942ad1fb99bb7c31eda1013&imgtype=0&src=http%3A%2F%2Fpic31.nipic.com%2F20130801%2F11604791_100539834000_2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007586&di=233f11f8f78b223364990a8407a66e0c&imgtype=0&src=http%3A%2F%2Fpic40.nipic.com%2F20140331%2F9469669_142840860000_2.jpg",
                "http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007586&di=8ffac389e9d8ed9b8913e367c807e219&imgtype=0&src=http%3A%2F%2Fpic25.nipic.com%2F20121112%2F9252150_150552938000_2.jpg",
                "http://pic41.nipic.com/20140518/4135003_102025858000_2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007586&di=7082c159417a89a92623986e70b99ee6&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheji%2F20141219%2Fzhongguofengdaodeliyizhanbanzhijing_3744115.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007585&di=51011cc303dc4cfa89fc687f450592af&imgtype=0&src=http%3A%2F%2Fpic44.nipic.com%2F20140727%2F18179070_152408117000_2.jpg",
                "http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=429e7b1b92ef76c6d087f32fa826d1cc/7acb0a46f21fbe09cc206a2e69600c338744ad8a.jpg",
                "http://pica.nipic.com/2007-12-21/2007122115114908_2.jpg",
                "http://photo.loveyd.com/uploads/allimg/080618/1110324.jpg",
                "http://img4.duitang.com/uploads/item/201404/17/20140417105820_GuEHe.thumb.700_0.jpeg",
                "http://img4.duitang.com/uploads/item/201404/17/20140417105856_LTayu.thumb.700_0.jpeg",
                "http://pic.dbw.cn/0/01/33/59/1335968_847719.jpg",
                "http://a.hiphotos.baidu.com/image/pic/item/a8773912b31bb051a862339c337adab44bede0c4.jpg",
                "http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg",
                "http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg",
                "http://pic25.nipic.com/20121210/7447430_172514301000_2.jpg",
                "http://img02.tooopen.com/images/20140320/sy_57121781945.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007585&di=9e95dd5ce7571b44ccb753b26db436c7&imgtype=0&src=http%3A%2F%2Fpic15.nipic.com%2F20110629%2F5078207_164705330000_2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007585&di=7f4020932e04b37282b30de3447909d3&imgtype=0&src=http%3A%2F%2Fgss0.baidu.com%2F9vo3dSag_xI4khGko9WTAnF6hhy%2Flvpics%2Fw%3D1000%2Fsign%3D9b38971c908fa0ec7fc7600d16a758ee%2Fc8ea15ce36d3d5394fe85aec3b87e950342ab0cc.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007585&di=c5e66835fececb152b8841095733354e&imgtype=0&src=http%3A%2F%2Fpic37.nipic.com%2F20140115%2F7430301_100825571157_2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007585&di=5c5d9a7837036378868d45cdfa011c39&imgtype=0&src=http%3A%2F%2Fpic13.nipic.com%2F20110409%2F7119492_114440620000_2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007584&di=c3925b874ea49b14b26b1c1c454a9140&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F93%2F09%2F83F58PICUiG_1024.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007584&di=b80a2e3f27312585026c881753737820&imgtype=0&src=http%3A%2F%2Fpic41.nipic.com%2F20140508%2F18609517_112216473140_2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007584&di=803ba3c5cdc8ecf1d54f1cb84183f88c&imgtype=0&src=http%3A%2F%2Fpic25.nipic.com%2F20121117%2F9252150_165726249000_2.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561802007583&di=431ff149637c02270bf03719b54f77ed&imgtype=0&src=http%3A%2F%2Fpic36.nipic.com%2F20131126%2F8821914_071759099000_2.jpg"
        };

        MyAdapter2 adapter= new MyAdapter2(this,Arrays.asList(imageUrls));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
    }

    class MyAdapter2 extends RecyclerView.Adapter<MyViewHolder>{

        private Context context;
        private List<String> list;

        public MyAdapter2(Context context,List<String> list){
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyViewHolder(getLayoutInflater().inflate(R.layout.item_my_image_load,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.imageView.setImageURL(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list == null ? 0:list.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private UrlImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            int weight = (UISizeUtils.getScreenWidth(MySimpleImageLoadActivity.this))/3;
            imageView.setLayoutParams(new ConstraintLayout.LayoutParams(weight,weight));
        }
    }

}
