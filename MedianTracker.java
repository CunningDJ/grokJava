package com.cunningdj.grokJava;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
	Median Tracker is a practice case for the TWO HEAPS pattern
*/

class MedianTracker {
	private PriorityQueue<Integer> topMinHeap;
	private PriorityQueue<Integer> bottomMaxHeap;

	// MAIN
	public static void main(String[] args) {
		Tester tester = new Tester();
		String testTitle="MEDIAN_TRACKER";

		MedianTracker mt = new MedianTracker();
		tester.isNull(mt.findMedian(), testTitle);
		mt.add(1);
		tester.doubleEquals(1, mt.findMedian(), testTitle);
		mt.add(3);
		tester.doubleEquals(2, mt.findMedian(), testTitle);
		mt.add(2);
		tester.doubleEquals(2, mt.findMedian(), testTitle);
		mt.add(4);
		tester.doubleEquals(2.5, mt.findMedian(), testTitle);
	}

	// CONSTRUCTOR(S)
	public MedianTracker() {
		this.topMinHeap = new PriorityQueue<>();
		this.bottomMaxHeap = new PriorityQueue<>(Comparator.reverseOrder());
	}

	// PUBLIC
	public void add(int num) {
		// We will design this such that the bottom max heap will have 0-1 more elements than the top min heap
		//  (if 1 more, then that is the median)
		if (bottomMaxHeap.size() == 0 || num <= bottomMaxHeap.peek()) {
			bottomMaxHeap.add(num);
		} else {
			topMinHeap.add(num);
		}

		rebalanceHeaps();
	}

	public Double findMedian() {
		if (bottomMaxHeap.size() == 0) {
			// Return null if there is no median (both heaps empty)
			return null;
		} else if (bottomMaxHeap.size() > topMinHeap.size()) {
			return bottomMaxHeap.peek() / 1.0;
		} else {
			// Divide each by 2.0 individually to avoid hitting Integer.MAX_VALUE when adding
			return (bottomMaxHeap.peek() / 2.0) + (topMinHeap.peek() / 2.0);
		}
	}

	// PRIVATE
	private void rebalanceHeaps() {
		if (bottomMaxHeap.size() < topMinHeap.size()) {
			bottomMaxHeap.add(topMinHeap.poll());
		} else if (bottomMaxHeap.size() > topMinHeap.size() + 1) {
			topMinHeap.add(bottomMaxHeap.poll());
		}
	}
}
