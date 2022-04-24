package 二十一天刷题20220405.day19_1最长公共子序列LCS;

import java.util.Arrays;

/**
 * https://mp.weixin.qq.com/s/ZhPEchewfc03xWv9VP3msg
 * 最长公共子序列是典型的二维动态规划问题
 * 定义：递归方法：dp(str1,i,str2,j)返回的是str1[i..]和str2[j..]的最长公共子序列，那么我们想要的答案就是dp(s1, 0, s2, 0)
 * base case：当i=str1.len()或j=str2.len()时，返回0
 * 定义：memo[][] memo[i][j] = dp(str1,i,str2,j)，用于处理重叠子问题
 */
public class LCS最长公共子序列1143 {
    int[][] memo;

    /**
     * 自顶向下的解法
     */
    int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        memo = new int[m][n];
        for (int[] ints : memo) {
            //用-1表示没有记录
            Arrays.fill(ints, -1);
        }
        return dp(s1, 0, s2, 0);
    }

    private int dp(String s1, int i, String s2, int j) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = 1 + dp(s1, i + 1, s2, j + 1);
        } else {
            //不相等，所以LCS的长度不用+1
            memo[i][j] = Math.max(
                    //s1[i] 和 s2[j] 至少有一个不在 lcs 中
                    // dp(s1, i + 1, s2, j + 1), 舍弃了，因为s1[i]和s2[j]都不在LCS中的情况已经被包含在下面的情况中了 假设我想从dp(i, j)转移到dp(i+1, j+1)，有不止一种方式，可以直接走#1，也可以走#2 -> #3，也可以走#3 -> #2。
                    //这就是重叠子问题，如果我们不用memo备忘录消除子问题，那么dp(i+1, j+1)就会被多次计算，这是没有必要的。
                    dp(s1, i + 1, s2, j),
                    dp(s1, i, s2, j + 1));
        }
        return memo[i][j];
    }

    /**
     * 自顶向下的解法，共用memo
     */
    int longestCommonSubsequenceFromBottom(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 定义：s1[0..i-1] 和 s2[0..j-1] 的 lcs 长度为 dp[i][j]
        // 目标：s1[0..m-1] 和 s2[0..n-1] 的 lcs 长度，即 dp[m][n]
        // base case: dp[0][..] = dp[..][0] = 0

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

}
