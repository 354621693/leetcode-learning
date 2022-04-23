package 二十一天刷题20220405.day19;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 */
public class LIS最长递增子序列300 {
    /**
     * 设计：dp[i]=在nums数组中，以nums[i]为结尾的[最长递增子序列]的长度。注意：这个定义中 nums[i] 必须被选取，且必须是这个子序列的最后一个元素；
     * 为什么不是设计dp[i]=nums数组中，0到i位置的最长递增子序列的长度(nums[i]不一定在这个序列中)？
     * 答：基于「动态规划」的状态设计需要满足「无后效性」的设计思想，「无后效性」的设计思想：让不确定的因素确定下来，以保证求解的过程形成一个逻辑上的有向无环图。
     * 这题不确定的因素是某个元素是否被选中，而我们设计状态的时候，让 nums[i] 必需被选中，这一点是「让不确定的因素确定下来」，也是我们这样设计状态的原因。
     */
    int[] dp;
    int res = 0;

    public int lengthOfLIS(int[] nums) {
        dp = new int[nums.length];
        //注意
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        for (int j : dp) {
            res = Math.max(res, j);
        }
        return res;
    }

}
