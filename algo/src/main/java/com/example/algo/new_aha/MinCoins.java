package com.example.algo.new_aha;

/**
 * Desc:最小硬币数量问题
 * 动态规划，当求解总面值为 i 的找零最少硬币数 dp[i] 时，将其分解成求解 dp[i – cents] 和一个面值为 cents 元的硬币，
 * 由于 i – cents < i ， 其解 dp[ i – cents] 已经在之前的循环中被算出，如果面值为 cents 的硬币满足题意，
 * 那么最终解 dp[i] 则等于 dp[i – cents]再加上 1（即面值为 cents）的这一个硬币。
 * https://blog.csdn.net/huanghanqian/article/details/79838326
 * 设这一堆面值分别为 1、2、5、21、25 元，需要找出总值 T 为 63 元的零钱。
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/10/27
 * version:
 * update:
 */
public class MinCoins {

    public static void main(String[] args) {
        int[] corns = {1,2,5,7,10};
        int tarNum = 63;
        int[] dp = new int[tarNum+1];
        test(corns,dp);
    }

    public static void test(int[] corns,int[] dp){
        //遍历填充每一个dp数组
        for(int i=1;i<dp.length;i++){
            int minCornsNum = i;

            //遍历每个硬币，尝试使用每个面值的硬币来减少组成硬币数量
            for(int j=0;j<corns.length;j++){
                if(corns[j] <= i){
                    if(dp[i-corns[j]]+1 < minCornsNum){
                        minCornsNum = dp[i-corns[j]]+1;
                    }
                }
            }
            //赋值最小硬币数量
            dp[i] = minCornsNum;
            System.out.println("money "+i+" min num:"+dp[i]);
        }
    }
}
