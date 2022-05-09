package com.cunningdj.grokJava;

class GrokkingSlidingWindow {
	public static void main(String[] args) {
		Tester tester = new Tester();
		String testTitle="";

		// TEST CLASS METHODS HERE USING TESTER CLASS
		// WINDOW_AVERAGE
		testTitle = "WINDOW_AVERAGE";
		tester.testDoubleArrayEquals(new double[]{2,3,4,5}, slidingAverage(new int[]{1,2,3,4,5,6}, 3), testTitle);
		tester.testDoubleArrayEquals(new double[]{3.5}, slidingAverage(new int[]{1,2,3,4,5,6}, 6), testTitle);
		tester.testDoubleArrayEquals(new double[]{1.5,2.5,3.5,4.5,5.5}, slidingAverage(new int[]{1,2,3,4,5,6}, 2), testTitle);
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
