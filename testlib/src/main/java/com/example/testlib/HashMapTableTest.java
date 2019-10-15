package com.example.testlib;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/24
 * version:
 * update:
 */
public class HashMapTableTest {

    public static void main(String[] args){

        System.out.println(addStrings("98","8"));
    }

    public static String addStrings(String num1, String num2) {
        StringBuilder ss = new StringBuilder();
        int upNum = 0;
        for(int i=0;i<Math.max(num1.length(),num2.length());i++){
            char oneChar = '0',twoChar = '0';
            if(i < num1.length()){
                oneChar = num1.charAt(i);
            }

            if(i<num2.length()){
                twoChar = num2.charAt(i);
            }

            int re = oneChar - '0' + twoChar - '0' + upNum;
            upNum = re/10;
            ss.append(re%10);
        }
        if(upNum > 0){
            ss.append(upNum);
        }
        return ss.reverse().toString();
    }

}
