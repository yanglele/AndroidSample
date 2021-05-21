package com.example.testlib;

import java.util.BitSet;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/10/23
 * version:
 * update:
 */
public class ExtendsClass extends BaseClass {
    int a = 2;
//    int b = a;

    public static void main(String[] args){
        ExtendsClass extendsClass = new ExtendsClass();
        System.out.println(extendsClass.a);
        System.out.println(extendsClass.getA());

        extendsClass.a = 3;
        System.out.println(extendsClass.a);
        System.out.println(extendsClass.getA());
    }
}

class BaseClass{
    public int a = 1;

    public int getA(){
        return a;
    }

}
