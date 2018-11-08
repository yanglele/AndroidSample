package com.example.yangl.androidsample.imageCorp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Litp on 2016/08/29.
 */
public class PhotoUtils {


    // 图片在SD卡中的缓存路径
    public static final String IMAGE_PATH = Environment
            .getExternalStorageDirectory().toString() + File.separator
            + "ImageCropDemo" + File.separator;

    public static final String PHOTO_TYPE = ".jpg";

    public static Bitmap createBitmap(String path, int w, int h) {

        try {

            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, opts);

            int srcWidth = opts.outWidth;
            int srcHeight = opts.outHeight;

            int destWidth = 0;
            int destHeight = 0;

            double ratio = 0.0;

            if (srcWidth < w || srcHeight < h) { //任意一边小于屏幕 不压缩比例

                ratio = 0.0;
                destWidth = srcWidth;
                destHeight = srcHeight;

            } else if (srcWidth > srcHeight) {

                ratio = (double) srcWidth / w;
                destWidth = w;
                destHeight = (int) (srcHeight / ratio);

            } else {

                ratio = (double) srcHeight / h;
                destHeight = h;
                destWidth = (int) (srcWidth / ratio);

            }


            BitmapFactory.Options newOpts = new BitmapFactory.Options();

            newOpts.inSampleSize = (int) ratio + 1;
            newOpts.inJustDecodeBounds = false;
            newOpts.outHeight = destHeight;
            newOpts.outWidth = destWidth;

            return BitmapFactory.decodeFile(path, newOpts);

        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 保存图片到sdCard
     * @param bitmap
     * @param requestCode
     * @return
     */
    public static String savePhotoToSDCard(Bitmap bitmap, int requestCode) {

        if (!isSdcardExist()) {
            return null;
        }

        createDirFile(IMAGE_PATH);

        String newFilePath = getPicPath(requestCode);
        File file = createNewFile(newFilePath);

        if (file == null) {
            return null;
        }

        FileOutputStream fileOutputStream = null;
        int quality = 100;
        try {
            fileOutputStream = new FileOutputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            while (quality > 10 && baos.toByteArray().length > 1024*1024) {
                quality -= 10;
                baos.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            }
            baos.writeTo(fileOutputStream);
            baos.flush();
            baos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return newFilePath;
    }


    /**
     * 创建目录
     *
     * @param path 目录路径
     */
    public static boolean createDirFile(String path) {
        File dir = new File(path);

        if (!dir.exists()) {
            return dir.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * 判断sd卡是否存在
     *
     * @return
     */
    public static boolean isSdcardExist() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }


    public static File createNewFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return null;
            }
        }
        return file;
    }


    public static String getPicPath( int requestCode) {

        StringBuffer Path = new StringBuffer(IMAGE_PATH);

        Path.append(UUID.randomUUID().toString().replaceAll("-", ""));


        return Path.append(PHOTO_TYPE).toString();

    }
}
