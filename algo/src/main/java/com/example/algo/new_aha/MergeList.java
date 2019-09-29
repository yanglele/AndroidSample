package com.example.algo.new_aha;

import com.example.algo.new_aha.model.Node;
import com.example.algo.new_aha.model.NodeUtil;

/**
 * Desc:有序链表合并
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/29
 * version:
 * update:
 */
public class MergeList {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(5);
        Node node2 = new Node(11);
        Node node3 = new Node(15);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        Node head2 = new Node(4);
        Node node4 = new Node(8);
        Node node5 = new Node(12);
        Node node6 = new Node(16);

        head2.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;

        Node merge = merge(head, head2);
        NodeUtil.print(merge);
    }

    public static Node merge(Node n1, Node n2) {
        Node newHead = new Node(-1);
        Node returnHead = newHead;


        while (n1 != null && n2 != null){
            if(n1.a < n2.a){
                newHead.next = n1;
                n1 = n1.next;
            }else {
                newHead.next = n2;
                n2 = n2.next;
            }
            newHead = newHead.next;
        }

        if(n1 == null){
            newHead.next = n2;
        }else {
            newHead.next = n1;
        }

        return returnHead.next;
    }


}
