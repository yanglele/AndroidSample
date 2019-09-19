package com.example.algo.new_aha;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/19
 * version:
 * update:
 */
public class BFS {

    public static void main(String[] args){
        bfs();
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
    static Queue<Step> queue = new LinkedList<>();
    static int curNum;
    static int nextNum;
    static class Step{
        int x;
        int y;
        int step;

        public Step(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
    private static void bfs(){
        queue.add(new Step(0,0,0));
        while (!queue.isEmpty()){
            Step peek = queue.poll();
            for(int i=0;i<4;i++){
                Step newStep = new Step(peek.x+path[i][0],peek.y+path[i][1],peek.step+1);
                if(newStep.x <0 || newStep.y < 0 || newStep.x >= mi.length || newStep.y >= mi[0].length){
                    continue;
                }
                if(mi[newStep.x][newStep.y] == 1 || markMi[newStep.x][newStep.y] == 1){
                    continue;
                }
                if(newStep.x == target[0] && newStep.y == target[1]){
                    System.out.println(newStep.step);
                    return;
                }
                queue.add(newStep);
                markMi[newStep.x][newStep.y] = 1;
            }
        }
    }
}
