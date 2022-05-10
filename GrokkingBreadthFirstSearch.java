package com.cunningdj.grokJava;

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Arrays;

class GrokkingBreadthFirstSearch {
	public static void main(String[] args) {
		Tester tester = new Tester();
		String testTitle="";

		// LEVEL_VALUES
		testTitle = "LEVEL_VALUES";
		TreeNode root = createTestTree();
		tester.intListofListsEquals(testTreeLevelValues(), levelValues(root), testTitle);
	}

	/*
	 * TEST UTILS
	 */
	private static TreeNode createTestTree() {
		// LEVEL 1
		TreeNode root = new TreeNode(0);
	
		// LEVEL 2
		root.left = new TreeNode(1);
		root.right = new TreeNode(2);

		// LEVEL 3
		root.left.left = new TreeNode(3);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);

		// LEVEL 4
		root.left.left.right = new TreeNode(8);
		root.right.left.left = new TreeNode(11);
		root.right.right.left = new TreeNode(13);
		
		// LEVEL 5
		root.right.right.left.right = new TreeNode(33);

		return root;
	}

	private static List<List<Integer>> testTreeLevelValues() {
		ArrayList<List<Integer>> allValues = new ArrayList<>();
		ArrayList<Integer> levelValues = new ArrayList<>();
		// LEVEL 1
		levelValues.add(0);
		allValues.add(levelValues);

		// LEVEL 2
		levelValues = new ArrayList<Integer>();
		levelValues.add(1);
		levelValues.add(2);
		allValues.add(levelValues);

		// LEVEL 3
		levelValues = new ArrayList<Integer>();
		levelValues.add(3);
		levelValues.add(5);
		levelValues.add(6);
		allValues.add(levelValues);

		// LEVEL 4
		levelValues = new ArrayList<Integer>();
		levelValues.add(8);
		levelValues.add(11);
		levelValues.add(13);
		allValues.add(levelValues);

		// LEVEL 5
		levelValues = new ArrayList<Integer>();
		levelValues.add(33);
		allValues.add(levelValues);

		return allValues;
	}

	/*
	 * ALGO PROBLEMS
	 */
	public static List<List<Integer>> levelValues(TreeNode root) {
		ArrayList<List<Integer>> allValues = new ArrayList<>();
		Deque<TreeNode> dq = new LinkedList<>(); 
		dq.push(root);
		int levelSize = 1;
		TreeNode currNode = null;
		while (levelSize > 0) {
			ArrayList<Integer> values = new ArrayList<>();
			for (int i=0; i < levelSize; ++i) {
				currNode = dq.removeLast();
				values.add(currNode.value);
				if (currNode.left != null) {
					dq.push(currNode.left);
				}
				if (currNode.right != null) {
					dq.push(currNode.right);
				}
			}
			allValues.add(values);
			levelSize = dq.size();
		}

		return allValues;
	}
}
