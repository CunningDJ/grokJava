package com.cunningdj.grokJava;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

class GrokkingTopSort {
	public static void main(String[] args) {
		Tester tester = new Tester();
		String testTitle="";

		// CLASSES_ORDER
		testTitle = "CLASSES_ORDER";
		ClassDependencyPair pair1 = new ClassDependencyPair(1, 2);
		ClassDependencyPair pair2 = new ClassDependencyPair(2,3);
		ClassDependencyPair pair3 = new ClassDependencyPair(3,4);
		ClassDependencyPair pair4 = new ClassDependencyPair(1,4);
		ClassDependencyPair circularPair = new ClassDependencyPair(4,2);
		ClassDependencyPair[] pairs = {pair1, pair2, pair3, pair4};
		tester.testIntArrayEquals(new int[]{1,2,3,4}, classesOrder(4, pairs), testTitle);
		ClassDependencyPair[] pairs2 = {pair1, pair2, pair3, pair4, circularPair};
		tester.testIntArrayEquals(null, classesOrder(5, pairs2), testTitle);
		
	}

	public static int[] classesOrder(int numberOfClasses, ClassDependencyPair[] pairs) {
		HashMap<Integer, List<Integer>> parentChildrenMap = new HashMap<>();
		HashMap<Integer, Integer> inDegrees = new HashMap<>();
		for (ClassDependencyPair pair : pairs) {
			// Updating parent's children
			if (!parentChildrenMap.containsKey(pair.parent)) {
				parentChildrenMap.put(pair.parent, new ArrayList<Integer>());
			}
			parentChildrenMap.get(pair.parent).add(pair.child);

			// Updating child's in-degrees
			if (!inDegrees.containsKey(pair.child)) {
				inDegrees.put(pair.child, 0);
			}
			inDegrees.put(pair.child, inDegrees.get(pair.child) + 1);
		}

		Stack<Integer> noDependencies = new Stack<>();
		for (Integer node : parentChildrenMap.keySet()) {
			if (!inDegrees.containsKey(node)) {
				noDependencies.push(node);
			}
		}

		int[] classesOrder = new int[numberOfClasses];
		int addi = 0;

		Integer node = null;
		while (noDependencies.size() > 0) {
			node = noDependencies.pop();
			classesOrder[addi] = node;
			addi++;
			if (parentChildrenMap.containsKey(node)) {
				for (Integer child : parentChildrenMap.get(node)) {
					if (inDegrees.get(child) > 1) {
						inDegrees.put(child, inDegrees.get(child) - 1);
					} else {
						inDegrees.remove(child);
					}
					if (!inDegrees.containsKey(child)) {
						noDependencies.push(child);
					}
				}
			}
		}

		if (addi < numberOfClasses-1) {
			return null;
		} else {
			return classesOrder;
		}
	}

	static class ClassDependencyPair {
		public int parent;
		public int child;

		public ClassDependencyPair(int parent, int child) {
			this.parent = parent;
			this.child = child;
		}

		public String toString() {
			return String.valueOf(this.parent) + "-" + String.valueOf(this.child);
		}
	}
}
