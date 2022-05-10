package com.cunningdj.grokJava;

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
}
