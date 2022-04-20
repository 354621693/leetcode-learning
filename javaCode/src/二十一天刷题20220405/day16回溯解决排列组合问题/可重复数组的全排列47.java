package 二十一天刷题20220405.day16回溯解决排列组合问题;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 可重复数组的全排列47 {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        visited = new boolean[nums.length];
        traverse(nums);
        return res;
    }

    public void traverse(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            //&& !visited[i]是为了过滤同一层里相同的元素，在决策树中不同一层但是相同的数字放行
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i]) {
                continue;
            }
            path.add(nums[i]);
            visited[i] = true;
            traverse(nums);
            path.removeLast();
            visited[i] = false;
        }
    }

    /**
     * 更容易理解的版本
     * @param nums
     */
    public void traverse2(int[] nums) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }
        //记录上一个元素的值，用来剪枝
        int previouse = -10086;
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            //如果和上一个元素相同，则跳过，确保相同元素只有一种排列
            if(nums[i]==previouse){
                continue;
            }
            previouse = nums[i];
            path.add(nums[i]);
            visited[i] = true;
            traverse(nums);
            path.removeLast();
            visited[i] = false;
        }
    }
}
