package com.example.algo.new_aha;

/**
 * Desc:dfs算法
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/19
 * version:
 * update:
 */
public class DFS {

    public static void main(String[] args){
//        dfs(0);
        dfs1(0,begin[0],begin[1]);
    }

    /**
     * 放牌
     * @param step
     */

    static int a[]={0,0,0};
    static int mark[]={0,0,0};
    private static void dfs(int step){
        if(step == a.length){
            System.out.println(a[0]+" "+a[1]+" "+a[2]);
            return;
        }

        for(int i=0;i<a.length;i++){
            if(mark[i] == 0){
                a[step] = i;
                mark[i] = 1;
                dfs(step+1);
                mark[i] = 0;
            }
        }
    }

    /**
     * 迷宫 5*4
     * @param step
     */
    static int mi[][]={{0,0,1,0},{0,0,0,0},{0,0,1,0},{0,1,0,0},{0,0,0,1}};
    static int markMi[][] = {{1,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    static int path[][] = {{1,0},{0,1},{-1,0},{0,-1}};
    static int begin[] = {0,0};
    static int target[] = {3,2};
    private static void dfs1(int step,int x,int y){
        if(x == target[0] && y == target[1]){
            System.out.println(step);
            return;
        }

        for(int i=0;i<4;i++){
            int nx = x+path[i][0];
            int ny = y+path[i][1];

            if(nx < mi.length && nx >=0 && ny>=0 && ny < mi[0].length && markMi[nx][ny] == 0  && mi[nx][ny] != 1){
                markMi[nx][ny] = 1;
                dfs1(step+1,nx,ny);
                markMi[nx][ny] = 0;
            }
        }
    }
}
