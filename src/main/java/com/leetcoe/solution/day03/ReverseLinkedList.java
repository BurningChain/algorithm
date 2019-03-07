package com.leetcoe.solution.day03;

import java.util.Stack;

public class ReverseLinkedList {

    public ListNode reverse(ListNode head)
    {
        if (null == head) {
            return null;
        }

        ListNode temp = new ListNode(head.getData());
        return doReverse(head.getNext(), temp);
    }

    private ListNode doReverse(ListNode next, ListNode curHead) {
        if (null == next) {
            return curHead;
        }
        ListNode nextToReverse = next.getNext();
        next.setNext(curHead);
        return doReverse(nextToReverse, next);
    }

    public static void main(String[] args){
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);

        ListNode reverse = new ReverseLinkedList().reverse(one);
        while (reverse != null) {
            System.out.println(reverse.getData());
            reverse = reverse.getNext();
        }
    }
}

class ListNode {
    private int data;
    private ListNode next;

    public ListNode(int data) {
        this.data = data;
    }


    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
