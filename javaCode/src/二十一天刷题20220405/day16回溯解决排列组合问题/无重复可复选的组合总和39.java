package 二十一天刷题20220405.day16回溯解决排列组合问题;

import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
public class 无重复可复选的组合总和39 {
    /**
     * 这里不适用动态规划来解决，因为动态规划是解决最值问题的，而这里的题目要求所有的组合，那么就要暴力遍历了。
     * 如果题目要求最少数量的组合，就可以用动态规划解决，就和零钱问题一样了。
     */
    LinkedList<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> list = new LinkedList<>();
    int targetNum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        targetNum = target;
        traverse(candidates, 0,0);
        return result;
    }

    public void traverse(int[] candidates, int index, int sumNow) {
        if (sumNow == targetNum) {
            result.add(new LinkedList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (targetNum < sumNow + candidates[i]) {
                continue;
            }
            sumNow += candidates[i];
            list.add(candidates[i]);
            //在不可复选的组合问题里，这里的值是i+1，此问题中用i，就达到了数组中的数字可重复使用，又达到了子集的效果（集中的元素不看顺序）
            traverse(candidates, i, sumNow);
            list.removeLast();
            sumNow -= candidates[i];

        }
    }
}
