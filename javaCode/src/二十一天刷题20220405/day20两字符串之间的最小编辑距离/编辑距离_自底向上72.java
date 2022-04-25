package 二十一天刷题20220405.day20两字符串之间的最小编辑距离;

import java.util.Arrays;

public class 编辑距离_自底向上72 {

    public int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        //填充base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
        //base case在0的位置，所以从1开始
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //因为dp的长宽都增加了1，那么开始的位置就是i-1，这是dp中常用的dp表偏移
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = mix(
                            dp[i - 1][j],
                            dp[i][j - 1],
                            dp[i - 1][j - 1]
                    ) + 1;
                }
            }
        }
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    private int mix(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }
}
