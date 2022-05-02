package com.cunningdj.grokJava;

public class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public static ListNode createList(int[] values) {
        // Convenience method for creating a list from an int array.  Returns the list head
        ListNode head = new ListNode(values[0]);
        ListNode curr = head;
        for (int i=1; i < values.length; ++i) {
            curr.next = new ListNode(values[i]);
            curr = curr.next;
        }
        return head;
    }
}
