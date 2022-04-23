package 二十一天刷题20220405.day19;

import java.util.Arrays;
import java.util.Comparator;

public class 俄罗斯套娃信封问题354 {
    public int maxEnvelopes(int[][] envelopes) {
        //按宽度升序排列，如果宽度一样，按高度降序排列
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        //对高度数组寻找LIS
        int[] height = new int[envelopes.length];
        for (int i = 0; i < height.length; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLISWithBS(height);
    }

    private int lengthOfLISWithBS(int[] height) {
        int piles = 0, n = height.length;
        int[] top = new int[n];
        for (int poker : height) {
            // 要处理的扑克牌
            int left = 0, right = piles;
            // 二分查找插入位置
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] >= poker)
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶
            top[left] = poker;
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }

    private int lengthOfLIS2(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int[] dp = new int[height.length];
        int res = 1;
        Arrays.fill(dp, 1);
        for (int i = 1; i < height.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (height[j] < height[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        for (int j : dp) {
            if (res < j) {
                res = j;
            }
        }
        return res;
    }
}
