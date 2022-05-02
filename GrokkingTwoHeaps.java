package com.cunningdj.grokJava;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GrokkingTwoHeaps {
    public static void main(String[] args) {
        Tester tester = new Tester();
        String testTitle = "";

        // KTH_LARGEST
        testTitle = "KTH_LARGEST";
        // Sorted ascending
        tester.intEquals(5, kthLargest(1, new int[]{1,2,3,4,5}), testTitle);
        tester.intEquals(1, kthLargest(5, new int[]{1,2,3,4,5}), testTitle);
        tester.intEquals(3, kthLargest(3, new int[]{1,2,3,4,5}), testTitle);
        // Sorted descending
        tester.intEquals(5, kthLargest(1, new int[]{5,4,3,2,1}), testTitle);
        tester.intEquals(1, kthLargest(5, new int[]{5,4,3,2,1}), testTitle);
        tester.intEquals(3, kthLargest(3, new int[]{5,4,3,2,1}), testTitle);
        // Unsorted
        tester.intEquals(5, kthLargest(1, new int[]{1,5,3,4,2}), testTitle);
        tester.intEquals(1, kthLargest(5, new int[]{1,5,3,4,2}), testTitle);
        tester.intEquals(3, kthLargest(3, new int[]{1,5,3,4,2}), testTitle);
    }

    public static int kthLargest(int k, int[] nums) {
        int n = nums.length;
        PriorityQueue<Integer> heap;
        boolean checkingLargest = k < n / 2;
        // This optimization will allow the complexity to always be O(log(n/2)) or less
        if (checkingLargest) {
            // Use Min Heap (Kth LARGEST)
            heap = new PriorityQueue<>(k);
        } else {
            // Use Max Heap (Kth SMALLEST - convert k to the "smallest" equivalent number)
            k = n - k + 1;
            // Comparator reversed will keep the LARGEST element at the top instead of the smallest
            heap = new PriorityQueue<>(k, Comparator.reverseOrder());
        }

        int i = 0;
        while (i < k) {
            heap.add(nums[i]);
            ++i;
        }


        // Could also separate the min/max into two separate while loops for further optimization, but
        //   putting both checks in one while loop here to demonstrate the shared code
        while (i < n) {
            if (// Min Heap: pushing LARGEST on
                (checkingLargest && nums[i] > heap.peek()) ||
                // Max Heap: Pushing SMALLEST on
                (!checkingLargest && nums[i] < heap.peek()))
            {
                heap.poll();
                heap.add(nums[i]);
            }
            ++i;
        }
        
        return heap.peek();
    }
}
