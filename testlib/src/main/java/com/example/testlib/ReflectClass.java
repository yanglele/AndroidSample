package com.example.testlib;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/10/22
 * version:
 * update:
 */
public class ReflectClass {


    public static void main(String[] args){
        ReflectTest test = new ReflectTest();
        test.setMap();

        Class<? extends ReflectTest> testClass = test.getClass();
        try {
            Field map = testClass.getDeclaredField("map");
            map.setAccessible(true);
            Object re = map.get(test);
            if(re instanceof HashMap){
                Object value = ((HashMap) re).get("1");
                System.out.println(value);
                ((HashMap) re).put("3","4");
            }
            System.out.println(re);
            map.set(test,new HashMap<>());
            System.out.println(test.getMap());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class ReflectTest{
    public ReflectTest(){}
    private Map<String,String> map = new HashMap<>();

    public void setMap(){
        map.put("1","2");
    }

    public Map getMap(){
        return map;
    }
}
