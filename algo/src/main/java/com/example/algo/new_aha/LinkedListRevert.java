package com.example.algo.new_aha;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/20
 * version:
 * update:
 */
public class LinkedListRevert {

    public static void main(String[] args){
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        Node newHead = revert(head);
        while (newHead != null){
            System.out.println(newHead.a);
            newHead = newHead.next;
        }

    }

    private static Node revert(Node head){
        if(head == null || head.next == null){
            return head;
        }

       Node pre = null;
       Node cur = head;
       while (cur != null){
           Node tmp = cur.next;
           cur.next = pre;
           pre = cur;
           cur = tmp;
       }
       return pre;
    }
}

class Node{
    int a;
    Node next;

    public Node(int a) {
        this.a = a;
    }

    public Node(int a, Node next) {
        this.a = a;
        this.next = next;
    }
}
