package 二十一天刷题20220405.day18动态规划基础;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回-1 。
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 * <p>
 * 链接：https://leetcode-cn.com/problems/coin-change
 */
public class 零钱兑换322 {
    Map<Integer, Integer> map = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        return dp(amount, coins);
    }

    private int dp(int amount, int[] coins) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (map.containsKey(amount)) {
            return map.get(amount);
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subRes = dp(amount - coin, coins);
            if (subRes < 0) {
                continue;
            }
            res = Math.min(res, subRes + 1);
        }
        map.put(amount, res == Integer.MAX_VALUE ? -1 : res);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 自底向上
     */
    public int coinChange2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        //Integer.MAX_VALUE+1 会溢出，为-2147483648，所以不能直接填充这个Integer.MAX_VALUE
        Arrays.fill(dp, amount);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[amount - coin] + 1, dp[i]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE + 1);
        零钱兑换322 aa = new 零钱兑换322();
        int i = aa.coinChange2(new int[]{2}, 3);
        return;
    }
}
