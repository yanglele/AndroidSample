package com.example.algo.new_aha.model;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/29
 * version:
 * update:
 */
public class NodeUtil {

    public static void print(Node head){
        while (head.next != null){
            System.out.print(head.a + "->");
            head = head.next;
        }
    }
}
