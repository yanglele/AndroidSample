package com.example.algo.new_aha.model;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/29
 * version:
 * update:
 */
public class Node{
    public int a;
    public Node next;

    public Node(int a) {
        this.a = a;
    }

    public Node(int a, Node next) {
        this.a = a;
        this.next = next;
    }
}
