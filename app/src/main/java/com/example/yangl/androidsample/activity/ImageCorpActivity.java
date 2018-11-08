package com.example.yangl.androidsample.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yangl.androidsample.R;
import com.example.yangl.androidsample.imageCorp.imagefactory.ImageFactoryActivity;

public class ImageCorpActivity extends AppCompatActivity implements View.OnClickListener{


    private Button tvChosePic;
    private ImageView ivShowPic;

    private final int REQUEST_CODE_CHOSE_PIC = 1;
    private final int REQUEST_CODE_CROP_PIC = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_corp);


        tvChosePic = (Button) findViewById(R.id.tv_chose_pic);
        ivShowPic = (ImageView) findViewById(R.id.iv_show_pic);

        tvChosePic.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view == tvChosePic){
            //打开图库选择图片
            selectPhoto(REQUEST_CODE_CHOSE_PIC);

        }
    }



    /**
     * ͨ通过手机相册获取图片
     */
    public void selectPhoto(int requestCode) {

        Intent intent = new Intent(Intent.ACTION_PICK, null);
        //打开文件
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, requestCode);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_CHOSE_PIC){
            Uri uri = data.getData();
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(uri, proj, null, null, null);
            if (cursor != null) {

                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                if (cursor.getCount() > 0 && cursor.moveToFirst()) {

                    String path = cursor.getString(column_index);
                    cropPhoto(path, requestCode);
                }
            }
        }else if(requestCode == REQUEST_CODE_CROP_PIC){
            if (resultCode == RESULT_OK) {
                if (data == null)
                    return;

                String path = data.getStringExtra("path");  //获取保存路径

                if (path == null)
                    return;

                BitmapFactory.Options options = new BitmapFactory.Options();
                //压缩编码
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inPurgeable = true;
                options.inInputShareable = true;
                Bitmap bmpDefaultPic = BitmapFactory.decodeFile(path,options);
                ivShowPic.setImageBitmap(bmpDefaultPic);
            }
        }

    }

    /**
     * 裁剪图片
     * @param path 要裁剪的图片路径
     * @param requestCode 请求码
     */
    public void cropPhoto(String path, int requestCode) {

        Intent intent = new Intent(this,ImageFactoryActivity.class);

        if (path != null) {
            intent.putExtra("path", path);
            intent.putExtra("requestCode", requestCode);
        }

        startActivityForResult(intent, REQUEST_CODE_CROP_PIC);
    }


    public void onTClick(View view) {

    }
}
