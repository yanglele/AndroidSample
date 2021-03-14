package com.example.algo.new_aha;

/**
 * Desc:把字符串“i am a student.”，翻转为“i ma a .tneduts”
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/10/26
 * version:
 * update:
 */
public class revertStringWord {

    public static void main(String[] args) {
        String s = "i am a student.";
        char[] arr = s.toCharArray();
        revert(arr,0,0);
        for(char tmp : arr){
            System.out.print(tmp);
        }
    }

    public static void revert(char[] arr ,int i,int j){
        if(i >= arr.length-1){
            return;
        }
        while (j<arr.length && arr[j] != ' ') j++;
        j--;
        for(;i<j;i++,j--){
           arr[j] ^= arr[i];
           arr[i] ^= arr[j];
           arr[j] ^= arr[i];
        }
        j+=2;
        revert(arr,j,j);
    }
}
