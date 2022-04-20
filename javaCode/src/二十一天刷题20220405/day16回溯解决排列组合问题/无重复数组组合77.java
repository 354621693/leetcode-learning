package 二十一天刷题20220405.day16回溯解决排列组合问题;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 * 链接：https://leetcode-cn.com/problems/combinations
 */
public class 无重复数组组合77 {
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    int end = 0;
    public List<List<Integer>> combine(int n, int k) {
        end = n;
        traverse(k,1);
        return res;
    }

    public void traverse(int k,int step){
        if(path.size()==k){
            res.add(new LinkedList<>(path));
            return;
        }
        for(int i = step ; i<=end;i++){
            path.add(i);
            traverse(k,i+1);
            path.remove(path.size()-1);
        }
    }
}
