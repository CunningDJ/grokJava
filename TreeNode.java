package com.cunningdj.grokJava;

class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;

	// CONSTRUCTOR(S)
	public TreeNode(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public TreeNode(int value, TreeNode left, TreeNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
}
