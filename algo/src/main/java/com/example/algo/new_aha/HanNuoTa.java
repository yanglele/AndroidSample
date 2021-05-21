package com.example.algo.new_aha;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:汉诺塔
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/10/25
 * version:
 * update:
 */
public class HanNuoTa {

    private static List<String> result = new ArrayList<>();

    public static void main(String[] args){
        get(3,"A","B","C");
        for(String s:result){
            System.out.println(s);
        }
    }

    public static void get(int n,String from,String buffer,String to){
        if( n == 1){
            result.add(from + " to "+to);
        }else {
            get(n-1,from,to,buffer);
            result.add(from + " to "+to);
            get(n-1,buffer,from,to);
        }
    }
}
