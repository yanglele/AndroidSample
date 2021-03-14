package com.example.testlib;

import java.util.BitSet;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/10/25
 * version:
 * update:
 */
public class BitSetTest {

    public static void main(String[] args){
        BitSet set = new BitSet();
        set.set(100000);
        System.out.println(set.length());
        for(int i=0;i<set.length();i++){
            System.out.print(set.get(i) + ",");
        }
    }
}
