package com.example.yangl.androidsample.tools;

import java.lang.reflect.Field;

/**
 * desc:获取主项目中BuildConfig文件信息
 * modify in 10.0
 * Created by junxu on 2016/10/17.
 */

public class BuildConfigUtil {
    private static String DEBUG_TAG = BuildConfigUtil.class.getSimpleName();
    public static boolean DEBUG = getBuildConfigValue("DEBUG");
    public static boolean ERROR = getBuildConfigValue("ERROR");
    public static boolean getBuildConfigValue(String fieldName) {
        Object obj;
        try {
            Class<?> clazz = Class.forName("com.anjuke.android.BuildConfig");
            Field field = clazz.getField(fieldName);
            obj=field.get(null);
            return (boolean)obj;
        } catch (ClassNotFoundException e) {
            DebugUtil.e(DEBUG_TAG, e.getMessage());
        } catch (NoSuchFieldException e) {
            DebugUtil.e(DEBUG_TAG, e.getMessage());
        } catch (IllegalAccessException e) {
            DebugUtil.e(DEBUG_TAG, e.getMessage());
        }
        catch (Exception e){
            DebugUtil.e(DEBUG_TAG, e.getMessage());
        }

        return false;
    }
}
