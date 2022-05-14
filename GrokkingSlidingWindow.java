package com.cunningdj.grokJava;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class GrokkingSlidingWindow {
	public static void main(String[] args) {
		Tester tester = new Tester();
		String testTitle="";

		// TEST CLASS METHODS HERE USING TESTER CLASS
		// WINDOW_AVERAGE
		testTitle = "WINDOW_AVERAGE";
		tester.doubleArrayEquals(new double[]{2,3,4,5}, slidingAverage(new int[]{1,2,3,4,5,6}, 3), testTitle);
		tester.doubleArrayEquals(new double[]{3.5}, slidingAverage(new int[]{1,2,3,4,5,6}, 6), testTitle);
		tester.doubleArrayEquals(new double[]{1.5,2.5,3.5,4.5,5.5}, slidingAverage(new int[]{1,2,3,4,5,6}, 2), testTitle);

		// FRUIT_BASKETS
		testTitle = "FRUIT_BASKETS";
		char A = 'A';
		char B = 'B';
		char C = 'C';
		// TESTING: left max
		tester.intEquals(5, maxFruitsTwoBaskets(new char[]{A,A,A,B,B,C,A}), testTitle);
		// TESTING: right max
		tester.intEquals(5, maxFruitsTwoBaskets(new char[]{A,C,A,A,A,B,B}), testTitle);
		// TESTING: middle max
		tester.intEquals(5, maxFruitsTwoBaskets(new char[]{A,C,A,A,A,B,B,C,A}), testTitle);

		// LONGEST_SUBSTRING_DISTINCT_CHARS
		testTitle = "LONGEST_SUBSTRING_DISTINCT_CHARS";
		// TESTING: middle
		tester.intEquals(4, longestSubstringDistinctCharacters("aaabcdaa"), testTitle);
		// TESTING: beginning
		tester.intEquals(4, longestSubstringDistinctCharacters("abcdaaa"), testTitle);
		// TESTING: end
		tester.intEquals(4, longestSubstringDistinctCharacters("aaabcda"), testTitle);
		// TESTING: all same
		tester.intEquals(1, longestSubstringDistinctCharacters("aaaa"), testTitle);
		// TESTING: all unique
		tester.intEquals(5, longestSubstringDistinctCharacters("abcde"), testTitle);
		// TESTING: only one
		tester.intEquals(1, longestSubstringDistinctCharacters("a"), testTitle);

		// REPEATING_ONES_WITH_REPLACEMENT
		testTitle = "REPEATING_ONES_WITH_REPLACEMENT";
		// TESTING: k=1 (right/left/middle zero)
		tester.intEquals(4, repeatingOnesWithReplacement(new int[]{0,1,1,1}, 1), testTitle);
		tester.intEquals(4, repeatingOnesWithReplacement(new int[]{1,0,1,1}, 1), testTitle);
		tester.intEquals(4, repeatingOnesWithReplacement(new int[]{1,1,1,0}, 1), testTitle);
		// TESTING: k=0, all zeroes
		tester.intEquals(0, repeatingOnesWithReplacement(new int[]{0,0}, 0), testTitle);
		// TESTING: k=0, (right/left/middle zero)
		tester.intEquals(3, repeatingOnesWithReplacement(new int[]{0,1,1,1}, 0), testTitle);
		tester.intEquals(2, repeatingOnesWithReplacement(new int[]{1,0,1,1}, 0), testTitle);
		tester.intEquals(3, repeatingOnesWithReplacement(new int[]{1,1,1,0}, 0), testTitle);
		// tester.intEquals(00, repeatingOnesWithReplacement(new int[]{}, 00), testTitle);
	}

	public static int longestSubstringDistinctCharacters(String str) {
		int n = str.length();
		if (n < 1) {
			return 0;
		}
		int start = 0;
		int end = 0;
		HashSet<Character> prevChars = new HashSet<Character>();
		int maxLength = 0;
		int currLength = 0;
		while (end < n) {
			char c = str.charAt(end);
			while (start < end && prevChars.contains(c)) {
				prevChars.remove(str.charAt(start));
				++start;
			}
			prevChars.add(c);
			maxLength = Math.max(maxLength, prevChars.size());
			++end;
		}
		return maxLength;
	}

	public static int maxFruitsTwoBaskets(char[] fruits) {
		final int MAX_BASKETS = 2;
		if (fruits.length < 1) {
			return 0;
		}
		HashMap<Character, Integer> fruitCounts = new HashMap<>();
		int maxFruits = 0;
		int start = 0;
		int end = 0;
		int currCount = 0;
		while (end < fruits.length) {
			if (!fruitCounts.containsKey(fruits[end])) {
				fruitCounts.put(fruits[end], 0);
			}
			fruitCounts.put(fruits[end], fruitCounts.get(fruits[end]) + 1);
			++currCount;
			while (start < end && fruitCounts.keySet().size() > MAX_BASKETS) {
				if (fruitCounts.get(fruits[start]) == 1) {
					fruitCounts.remove(fruits[start]);
				} else {
					fruitCounts.put(fruits[start], fruitCounts.get(fruits[start]) - 1);
				}
				--currCount;
				++ start;
			}
			maxFruits = Math.max(maxFruits, currCount);
			++end;
		}

		return maxFruits;
	}

	public static double[] slidingAverage(int[] values, int windowSize) {
		if (windowSize > values.length) {
			return null;
		}
		//  1 2 3 4 5 6
		double windowSizeDouble = windowSize;
		double[] averages = new double[values.length - windowSize + 1];
		int averagesIdx = 0;
		double currAvg = 0;
		int i=0;
		while (i < windowSize) {
			currAvg += values[i];
			++i;
		}
		currAvg = currAvg / windowSizeDouble;
		averages[averagesIdx] = currAvg;
		while (i < values.length) {
			averagesIdx += 1;
			currAvg += (values[i] / windowSizeDouble) - (values[i-windowSize] / windowSizeDouble);
			averages[averagesIdx] = currAvg;
			++i;
		}
		return averages;
	}

	public static int repeatingOnesWithReplacement(int[] zeroes_ones, int k) {
		// Because I want to use the generalized solution to demonstrate how the logic generalizes,
		//  I'll convert int[] to Integer[] (so it can match Object[]).  This wouldn't be as efficient
		//  as just non-generalizing this logic for int[], but we can extrapolate that solution
		//  pretty easily from the logic in repeatingValidValuesLengthWithReplacement
		Integer[] converted = Arrays.stream(zeroes_ones).boxed().toArray(Integer[]::new);
		return repeatingValidValuesLengthWithReplacement(converted, 1, k);
	}

	public static int repeatingValidValuesLengthWithReplacement(Object[] values, Object VALID_VAL, int k) {
		/*
		 * Given an array of values and a valid value V and a number K, find the longest subarray
		 *  with only these valid values if you can replace K values
		 */
		// Space: O(1)
		int maxLength = 0;
		int start = 0;
		int invalidValsCount = 0;

		// Time: O(n)
		for (int end=0; end < values.length; ++end) {
			if (values[end] != VALID_VAL) {
				invalidValsCount++;
			}
			if (invalidValsCount > k) {
				// Time: O(n-1) max *over the course of the algo.  start can only increment upward and reach a max value of n-1
				while (start < end && values[start] == VALID_VAL) {
					start++;
				}
				// Time: O(1)
				if (start < end) {
					start++;
					invalidValsCount--;
				}
			}
			// Time: O(1)
			if (invalidValsCount <= k) {
				maxLength = Math.max(maxLength, end-start+1);
			}
		}

		// Total Time: O(n) ( O(n) + O(n-1) + O(1) )
		// Total Space: O(1)
		return maxLength;
	}
}
