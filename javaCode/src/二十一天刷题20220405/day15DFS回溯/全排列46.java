package 二十一天刷题20220405.day15DFS回溯;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * 链接：https://leetcode-cn.com/problems/permutations
 *
 * 深度优先搜索，时间复杂度计算，我们设置n为输入值nums的长度：
 * 决策树的最大宽度，也就是traverse函数的调用次数是n*(n-1)*……21，也就是n!。
 * 另外在traverse函数中我们还用了new LinkedList<>(list)来复制链表，所以时间复杂度为O(n)，总的时间复杂度为O(n*n!)。
 * 空间复杂度方面，决策树的最大深度为n，也就是说递归方法的方法栈的最大深度为n，所以空间复杂度为O(n)。
 *
 * 作者：Leemanshow
 * 链接：https://leetcode-cn.com/problems/permutations/solution/dfs-by-leemanshow-ztd9/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 全排列46 {
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        //路径
        LinkedList<Integer> list = new LinkedList<>();
        // 路径中的元素标记为已使用，避免重复使用
        boolean[] visited = new boolean[nums.length];
        traverse(nums, visited, list);
        return res;
    }

    private void traverse(int[] nums, boolean[] visited, LinkedList<Integer> list) {
        //结束条件
        if (list.size() == nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }
        int choice = 0;
        //遍历来找到下一个选择
        for (; choice < nums.length; choice++) {
            if (!visited[choice]) {
                //做选择
                list.add(nums[choice]);
                visited[choice] = true;
                traverse(nums, visited, list);
                //撤销选择（回溯）
                list.removeLast();
                visited[choice] = false;
            }
        }
    }
}
