package com.example.yangl.androidsample.someActivity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yangl.androidsample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideDemoActivity extends AppCompatActivity {

    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo2);
        ButterKnife.bind(this);
        initImageView();
    }

    private void initImageView() {
        String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560273719736&di=023e517182e739037b3b007d315eb455&imgtype=0&src=http%3A%2F%2Fk.zol-img.com.cn%2Fsjbbs%2F7692%2Fa7691515_s.jpg";
        Glide.with(this).load(Uri.parse(imageUrl)).apply(new RequestOptions().error(R.drawable.icon1)).into(imageView);
    }
}
