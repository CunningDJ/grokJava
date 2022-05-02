package com.cunningdj.grokJava;

public class GrokkingFastSlowPointers {
    public static void main(String[] args) {
        // Tests here
        Tester tester = new Tester();
        String testTitle = "";

        // IS_CYCLIC
        testTitle = "IS_CYCLIC";
        ListNode head = ListNode.createList(new int[] {1,2,3,4});
        tester.booleanEquals(false, isCyclic(head), testTitle);
        head.next.next.next.next = head.next;
        tester.booleanEquals(true, isCyclic(head), testTitle);
        head.next = head;
        tester.booleanEquals(true, isCyclic(head), testTitle);

        // CYCLE_LENGTH
        testTitle = "CYCLE_LENGTH";
        head = ListNode.createList(new int[]{1,2,3,4});
        tester.intEquals(0, cycleLength(head), testTitle);
        head.next.next.next.next = head;
        tester.intEquals(4, cycleLength(head), testTitle);
        head.next.next.next.next = head.next;
        tester.intEquals(3, cycleLength(head), testTitle);
        head.next.next.next.next = head.next.next.next;
        tester.intEquals(1, cycleLength(head), testTitle);

        // FIRST_NODE_IN_CYCLE
        testTitle = "FIRST_NODE_IN_CYCLE";
        head = ListNode.createList(new int[]{1,2,3,4});
        // No cycle
        tester.listNodeEquals(null, firstNodeInCycle(head), testTitle);
        // a midway node
        head.next.next.next.next = head.next;
        tester.listNodeEquals(head.next, firstNodeInCycle(head), testTitle);
        // length 1
        head.next.next.next.next = head.next.next.next;
        tester.listNodeEquals(head.next.next.next, firstNodeInCycle(head), testTitle);
        // length n
        head.next.next.next.next = head;
        tester.listNodeEquals(head, firstNodeInCycle(head), testTitle);
    }

    public static boolean isCyclic(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static int cycleLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                int length = 1;
                slow = slow.next;
                while (fast != slow) {
                    slow = slow.next;
                    length++;
                }
                return length;
            }
        }
        return 0;
    }

    public static ListNode firstNodeInCycle(ListNode head) {
        if (head == null) {
            return head;
        }

        int length = cycleLength(head);
        if (length == 0) {
            return null;
        }

        ListNode lead = head;
        ListNode follow = head;
        for (int i=0; i < length; ++i) {
            lead = lead.next;
        }
        while (lead != follow) {
            lead = lead.next;
            follow = follow.next;
        }
        return lead;
    }
}