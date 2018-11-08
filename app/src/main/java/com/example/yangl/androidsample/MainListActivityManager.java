package com.example.yangl.androidsample;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;


/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2017/12/22
 * version:
 * update:
 */

public class MainListActivityManager {

    //用于存储recyclerVIew item的文案 与 跳转的activity
    //每个list中存储两个string 分别为上述
    private static volatile List<String> activityList;

    private MainListActivityManager(){}

    public static List getInstance(){
        if(activityList == null){
            synchronized (MainListActivityManager.class){
                if(activityList == null){
                    activityList = new ArrayList<>();
                }
            }
        }
        return activityList;
    }

    /**
     * @param context
     * @param className 点击该item 跳转相应的activity
     */
    public static void addItemInfo(Context context , String className){
        String packageName = context.getPackageName()+".activity.";
        List<String> list = new ArrayList<>(1);
        list.add(packageName+className);
        getInstance().add(list);
    }

    //加载包 activity下的方法到listview里面
    public static void initListData(Context context){
        String path=context.getPackageName()+".activity";
        try {
            DexFile df = new DexFile(context.getPackageCodePath());
            Enumeration<String> enumeration = df.entries();
            while (enumeration.hasMoreElements()){
                String className = (String) enumeration.nextElement();
                if(className.contains(path)){
                    getInstance().add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取包下所有activity
     * @param context
     * @param targetPath 目标包地址
     */
    public static void getPackageActivities(Context context,String targetPath){
        PackageManager manager = context.getPackageManager();
        String packageName = context.getApplicationContext().getPackageName();

        try {
            ActivityInfo[] activities = manager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).activities;
            getInstance().clear();
            for(ActivityInfo activityInfo : activities){
                if(activityInfo.name.contains(targetPath)){
                    getInstance().add(activityInfo.name);
                }
                Log.d("  ", "getAllActivities: "+activityInfo);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

}
