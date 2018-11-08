package com.example.yangl.androidsample.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;


import java.io.File;

/**
 * Created by baron on 15/1/9.
 *
 * 文件存储相关工具类
 *
 * @author 张磊
 */
public class StorageUtil {
    private static String DEBUG_TAG = StorageUtil.class.getSimpleName();

    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String SPLASH_AD_DIR_NAME ="splash_ad";

    /**
     * 获取Anjuke App的磁盘缓存目录
     * @param context Application context
     * @return 缓存目录 {@link File directory}
     */
    public static File getCacheDirectory(Context context) {
        File appCacheDir = null;
        String externalStorageState;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        }
        catch (IncompatibleClassChangeError e) {
            externalStorageState = "";
        }
        if (Environment.MEDIA_MOUNTED.equals(externalStorageState) && hasExternalStoragePermission(context)) {
            appCacheDir = context.getExternalCacheDir();
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }

        if (appCacheDir == null) {
            @SuppressLint("SdCardPath") String cacheDirPath = "/data/data/" + context.getPackageName() + "/cache/";
            appCacheDir = new File(cacheDirPath);
        }

        return appCacheDir;
    }

    public static String getCacheDirectoryPath(Context context){

        return getCacheDirectory(context).getAbsolutePath();
    }


    /**
     * 获取指定的个人缓存目录
     * @param context Application context
     * @param individualDirectoryName 指定的缓存目录名
     * @return 缓存目录 {@link File directory}
     */
    public static File getIndividualCacheDirectory(Context context, String individualDirectoryName){

        File appCacheDir = getCacheDirectory(context);
        File individualCacheDir = new File(appCacheDir, individualDirectoryName);
        if(!individualCacheDir.exists()){
            if(!individualCacheDir.mkdirs()){
                individualCacheDir = appCacheDir;
            }
        }
        return individualCacheDir;
    }

    /**
     * 获取闪屏广告缓存目录
     * @param context Application context
     * @return 缓存目录 {@link File directory}
     */
    public static File getSplashAdCacheDirectory(Context context){

        return StorageUtil.getIndividualCacheDirectory(context, SPLASH_AD_DIR_NAME);
    }

    /**
     * 判断外置存储卡是否有写入权限
     * @param context Application context
     * @return boolean
     */
    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 删除文件夹所有内容
     */
    public static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles(); // 声明目录下所有的文件 files[];
                for (File file1 : files) { // 遍历目录下所有的文件
                    deleteFile(file1); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        }
    }

    /**
     * 计算SD卡可用空间
     *
     * @return 单位KB
     */
    public static long getAvailableSize() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getPath();// 获取SD卡得根路径
            StatFs stat = new StatFs(path);
            long availableBlocks = stat.getAvailableBlocks();// 获取空闲BLOCK数量
            long blockSize = stat.getBlockSize();// 获取BLOCK的大小
            long availableSize = availableBlocks * blockSize;
            return availableSize;
        }
        return 0;
    }

    /**
     * 计算SD总空间大小
     *
     * @return 单位KB
     */
    public static long getAllSize() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getPath();
            StatFs stat = new StatFs(path);
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getBlockCount();
            return availableBlocks * blockSize / 1024;
        }
        return 0;
    }
}
