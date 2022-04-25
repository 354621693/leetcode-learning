package 二十一天刷题20220405.day20两字符串之间的最小编辑距离;

import java.util.Arrays;

public class 编辑距离72 {
    int[][] memo;

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        memo = new int[m][n];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        return dp(word1, m - 1, word2, n - 1);
    }

    //自顶向下
    private int dp(String s1, int i, String s2, int j) {
        //base case
        if (i == -1) {
            return j + 1;//在s1插入s2的末尾j位字符
        }
        if (j == -1) {
            return i + 1;//在s1中删除s1前i位字符
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp(s1, i - 1, s2, j - 1);//相等，啥都不做
        } else {
            memo[i][j] = Math.min(
                    Math.min(
                            dp(s1, i, s2, j - 1),//插入,直接在 s1[i] 插入一个和 s2[j] 一样的字符,那么 s2[j] 就被匹配了，前移 j，继续跟 i 对比, 别忘了操作数加一
                            dp(s1, i - 1, s2, j) //删除,直接把 s[i] 这个字符删掉， 前移 i，继续跟 j 对比， 操作数加一
                    ),
                    dp(s1, i - 1, s2, j - 1) //替换，直接把 s1[i] 替换成 s2[j]，这样它俩就匹配了，同时前移 i，j 继续对比，操作数加一
            ) + 1;
        }
        return memo[i][j];
    }
}
