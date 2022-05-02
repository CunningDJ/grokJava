package com.cunningdj.grokJava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class GrokkingTwoPointers {
    public static void main(String[] args) {
        // Tests here
        Tester tester = new Tester();
        String testTitle = "";

        // HIGHEST_PROFIT
        testTitle = "HIGHEST_PROFIT";
        tester.intEquals(3, highestProfit(new int[] {1,4,3,2}), testTitle);
        tester.intEquals(0, highestProfit(new int[] {4,3,2,1}), testTitle);
        tester.intEquals(3, highestProfit(new int[] {1,2,3,4}), testTitle);
        
        // MAX VOLUME
        testTitle = "MAX_VOLUME";
        tester.intEquals(6, maxVolume(new int[] {1,1,1,4,1,1,1}), testTitle);
        tester.intEquals(9, maxVolume(new int[] {1,1,1,4,1,1,3}), testTitle);
        tester.intEquals(1, maxVolume(new int[] {1,1}), testTitle);
        tester.intEquals(0, maxVolume(new int[] {1,0}), testTitle);
        tester.intEquals(0, maxVolume(new int[] {0,1}), testTitle);
        // tester.intEquals(00, maxVolume(new int[] {xx}), testTitle);

    }

    public static int maxVolume(int[] barrierHeights) {
        int maxVolume = 0;
        int n = barrierHeights.length;
        if (n < 2) {
            return maxVolume;
        }

        int left = 0;
        int right = n-1;
        while (left < right) {
            int vol = Math.min(barrierHeights[right], barrierHeights[left]) * (right-left);
            maxVolume = Math.max(maxVolume, vol);
            if (barrierHeights[left] < barrierHeights[right]) {
                left++;
            } else if (barrierHeights[left] > barrierHeights[right]) {
                right--;
            } else {
                left++;
                right--;
            }
        }
        return maxVolume;
    }

    public static int highestProfit(int[] nums) {
        int maxProfit = Integer.MIN_VALUE;
        int n = nums.length;
        if (n < 2) {
            return maxProfit;
        }

        int left = 0;
        int right = 0;

        int currentProfit = 0;
        int prevProfit = 0;
        while (right < n-1) {
            right++;
            currentProfit = nums[right] - nums[left];
            while (left < right && currentProfit < prevProfit) {
                left += 1;
                currentProfit = nums[right] - nums[left];
            }
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        return maxProfit;
    }
}