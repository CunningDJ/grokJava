package com.cunningdj.grokJava;

class GrokkingCyclicSort {
	public static void main(String[] args) throws Exception {
		Tester tester = new Tester();
		String testTitle="";

		// FIND_DUPLICATE
		testTitle = "FIND_DUPLICATE";
		// Testing: beginning dupe
		tester.intEquals(1, findDuplicate(new int[]{1,1,2,3}), testTitle);
		// Testing: middle dupe
		tester.intEquals(2, findDuplicate(new int[]{1,2,2,3}), testTitle);
		// Testing: end dupe
		tester.intEquals(3, findDuplicate(new int[]{1,2,3,3}), testTitle);
		// Testing: only dupe
		tester.intEquals(1, findDuplicate(new int[]{1,1}), testTitle);

		// FIND_MISSING
		testTitle = "FIND_MISSING";
		// TESTING: middle missing
		tester.intEquals(3, findMissing(new int[]{1,2,4}), testTitle);
		// Testing: first missing
		tester.intEquals(1, findMissing(new int[]{2,3,4}), testTitle);
		// TESTING: tail missing
		tester.intEquals(4, findMissing(new int[]{1,2,3}), testTitle);
		// TESTING: One element
		tester.intEquals(2, findMissing(new int[]{1}), testTitle);
	}

	public static int findDuplicate(int[] nums) throws Exception{
		// Given an array from 1 to n with n+1 entries, find the duplicate entry in O(n) time
		int n = nums.length;
		int maxIdx = n-1;
		int i = 0;
		int respectiveIdx = 0;
		while (i < n) {
			respectiveIdx = nums[i]-1;
			while (respectiveIdx != i) {
				if (nums[respectiveIdx] == nums[i]) {
					return nums[i];
				}
				int tmp = nums[respectiveIdx];
				nums[respectiveIdx] = nums[i];
				nums[i] = tmp;
				respectiveIdx = nums[i] - 1;
			}
			++i;
		}
		throw new Exception("No duplicate found");
	}

	public static int findMissing(int[] nums) {
		// For an array 1 to n with n-1 integers, find the missing integer
		int n = nums.length;
		int maxIdx = n-1;
		int respectiveIdx = 0;
		for (int i=0; i<n; ++i) {
			respectiveIdx = nums[i] - 1;
			// Cyclically sort
			while (respectiveIdx != i && respectiveIdx <= maxIdx) {
				// Swapping values
				int tmp = nums[respectiveIdx];
				nums[respectiveIdx] = nums[i];
				nums[i] = tmp;
				respectiveIdx = nums[i] - 1;
			}
		}

		int respectiveVal = 0;
		for (int i=0; i < n; ++i) {
			respectiveVal = i + 1;
			if (nums[i] != respectiveVal) {
				// Found the missing number post-cyclic sort
				return respectiveVal;
			}
		}
		// With n-1 integers, if none are found missing when iterating, then the tail (n+1) is missing
		return n+1;
	}
}
