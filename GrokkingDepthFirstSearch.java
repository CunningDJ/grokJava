package com.cunningdj.grokJava;

import java.util.Stack;

class GrokkingDepthFirstSearch {
	public static void main(String[] args) {
		Tester tester = new Tester();
		String testTitle="";

		// TREE_HEIGHT
		testTitle = "TREE_HEIGHT";
		TreeNode root = buildTestTree();
		tester.intEquals(5, treeHeight(root), testTitle);
		root.right.left.left.right.right = new TreeNode(88);
		tester.intEquals(6, treeHeight(root), testTitle);
	}

	public static TreeNode buildTestTree() {
		// Builds a sample tree for testing.  Returns the tree root
		// LEVEL 1
		TreeNode root = new TreeNode(1);

		// LEVEL 2
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		// LEVEL 3
		root.left.left = new TreeNode(4);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		// LEVEL 4
		root.left.left.right = new TreeNode(9);
		root.right.left.left = new TreeNode(11);

		// LEVEL 5
		root.right.left.left.right = new TreeNode(20);

		return root;
	}

	public static int treeHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int maxHeight = 0;
		Stack<NodeHeightPair> st = new Stack<>();
		st.push(new NodeHeightPair(root, 1));
		NodeHeightPair nhp;
		while (st.size() > 0) {
			nhp = st.pop();
			if (nhp.node.left == null && nhp.node.right == null) {
				maxHeight = Math.max(maxHeight, nhp.height);
			} else {
				if (nhp.node.right != null) {
					st.push(new NodeHeightPair(nhp.node.right, nhp.height + 1));
				}
				if (nhp.node.left != null) {
					st.push(new NodeHeightPair(nhp.node.left, nhp.height + 1));
				}
			}
		}
		return maxHeight;
	}

	private static class NodeHeightPair {
		TreeNode node;
		Integer height;
		public NodeHeightPair(TreeNode node, Integer height) {
			this.node = node;
			this.height = height;
		}
	}
}
