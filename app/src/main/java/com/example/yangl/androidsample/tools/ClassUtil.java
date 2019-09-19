package com.example.yangl.androidsample.tools;

import android.widget.Toast;

import com.example.yangl.androidsample.MyApplication;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/9
 * version:
 * update:
 */
public class ClassUtil {

    /**
     * @param c 接口
     * @return List<Class>    实现接口的所有类
     * @Description: 根据一个接口返回该接口的所有类
     */
    @SuppressWarnings("unchecked")
    public static List<Class> getAllClassByInterface(Class c) {
        List returnClassList = new ArrayList<Class>();
        //判断是不是接口,不是接口不作处理
//        if (c.isInterface()) {
            String packageName = c.getPackage().getName();  //获得当前包名
            try {
                List<Class> allClass = getClasses(packageName, c);//获得当前包以及子包下的所有类(android中用)

                //判断是否是一个接口
                for (int i = 0; i < allClass.size(); i++) {
                    if (c.isAssignableFrom(allClass.get(i))) {
                        if (!c.equals(allClass.get(i))) {
                            returnClassList.add(allClass.get(i));
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
//        }
        return returnClassList;
    }

    /**
     * @return List<Class>    包下所有类
     * @Description: 根据包名获得该包以及子包下的所有类不查找jar包中的
     * 获得当前包以及子包下的所有类(android中用)
     */
    private static List<Class> getClasses(String packageName, Class<?> clazz) throws ClassNotFoundException, IOException {

        ArrayList<Class> classes = new ArrayList<Class>();
        List<DexFile> dexFiles = new ArrayList<>();
        try {
            BaseDexClassLoader classLoader = ((BaseDexClassLoader) Thread.currentThread().getContextClassLoader());
            Field pathListField = classLoader.getClass().getSuperclass().getDeclaredField("pathList");
            pathListField.setAccessible(true);
            Object pathList = pathListField.get(classLoader);

            Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);
            Object dexElements = dexElementsField.get(pathList);
            int dexLength = Array.getLength(dexElements);
            Field dexFileField = null;

            for (int i = 0; i < dexLength; i++) {
                Object dexElement = Array.get(dexElements, i);
                if (dexFileField == null) {
                    dexFileField = dexElement.getClass().getDeclaredField("dexFile");
                    dexFileField.setAccessible(true);
                }
                DexFile dexFile = (DexFile) dexFileField.get(dexElement);
                if (dexFile != null) {
                    dexFiles.add(dexFile);
                }
            }

            for (DexFile file : dexFiles) {
                for (Enumeration<String> entries = file.entries(); entries.hasMoreElements(); ) {
                    final String s1 = entries.nextElement();
                    if (s1.contains(MyApplication.applicationContext.getPackageName())) {
                        if (clazz.isAssignableFrom(Class.forName(s1))) {
                            classes.add(Class.forName(s1));
                        }
                    }
                }
            }
            Toast.makeText(MyApplication.applicationContext, Integer.toString(classes.size()), Toast.LENGTH_LONG).show();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return classes;
    }

}
