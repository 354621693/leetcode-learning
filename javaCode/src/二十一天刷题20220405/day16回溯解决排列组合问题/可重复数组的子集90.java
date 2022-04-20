package 二十一天刷题20220405.day16回溯解决排列组合问题;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class 可重复数组的子集90 {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        traverse(path, nums, 0);
        return res;
    }

    public void traverse(List<Integer> path, int[] nums, int index) {
        //先排序，让重复的元素靠在一起
        Arrays.sort(nums);
        res.add(new LinkedList<>(path));
        for (; index < nums.length; index++) {
            if (nums[index] == nums[index + 1]) {
                index++;
            }
            path.add(nums[index]);
            traverse(path, nums, index + 1);
            path.remove(path.size() - 1);
        }
    }
}
