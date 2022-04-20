package 二十一天刷题20220405.day16回溯解决排列组合问题;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/subsets/solution/hui-su-by-leemanshow-zd0q/
 */
public class 无重复数组的子集78 {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {

        traverse(path,nums,0);
        return res;
    }

    public void traverse(List<Integer> path,int[] nums,int index){
        res.add(new LinkedList<>(path));
        for(;index<nums.length;index++){
            path.add(nums[index]);
            traverse(path,nums,index+1);
            path.remove(path.size()-1);
        }
    }
}
