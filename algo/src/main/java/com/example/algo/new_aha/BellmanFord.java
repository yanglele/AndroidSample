package com.example.algo.new_aha;

/**
 * Desc:解决负边权的最小路径图论
 * 求所有点到某个点的最小距离
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/10/6
 * version:
 * update:
 */
public class BellmanFord {

    public static void main(String[] args) {
        int[][] srr = {{1, 2, 2}, {0, 1, -3}, {0, 4, 5}, {3, 4, 2}, {2, 3, 3}};
        int[] take = take(srr, 5, 5);
        for (int tmp : take) {
            System.out.println(tmp);
        }
    }

    public static int[] take(int[][] arr, int dot, int slide) {

        //赋予初始值
        int[] dis = new int[dot];
        for (int i = 0; i < dis.length; i++) {
            dis[i] = 999;
        }
        dis[1] = 0;

        //核心算法
        for (int i = 0; i < dot; i++) {
            for (int j = 0; j < slide; j++) {
                if (dis[arr[j][1]] > dis[arr[j][0]] + arr[j][2]) {
                    dis[arr[j][1]] = dis[arr[j][0]] + arr[j][2];
                }
            }
        }

        //检测负权回路
        int flag = 0;
        for (int i = 0; i < slide; i++) {
            if (dis[arr[i][1]] > dis[arr[i][0]] + arr[i][2])
                flag = 1;
        }
        if (flag == 1)
            System.out.println("有负边权回路！");

        return dis;
    }
}
