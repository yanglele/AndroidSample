package com.example.java;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2020/12/14
 * version:
 * update:
 */
public class JavaTest {
    public static void main(String[] args) {
//        String s = new String();
//        List list = new LinkedList();
//        list.size();
//
//        s.substring(1,2);
//        s.toCharArray();
//        Stack stack = new Stack();
        Integer[] a = new Integer[]{2,1,3};
//        Map<String,String> map = new HashMap();
//        map.containsKey("");
//        map.get("");
//        Arrays.sort(args);
//        char[] charArr = new char[]{'1','2'};
//        String charString = charArr.toString();
//        System.out.println(charString);
//        new String();
//        Boolean boo = new Boolean(false);
//        Boolean boo2 = boo;

        Arrays.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer t0, Integer t1) {
                return t1 - t0;
            }
        });
        for(int tmp : a){
            System.out.println(tmp);
        }

        List<int[]> list = new ArrayList<>();
        List<int[]> linkedList = new LinkedList<>();
        linkedList.get(1);
        list.toArray();
        list.size();
        int[][] arr = {
                {}
        };
    }
}
