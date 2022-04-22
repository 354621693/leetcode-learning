package 二十一天刷题20220405.day17回溯法解决划分集合问题;

import java.util.HashMap;
import java.util.Map;

public class 桶视角的划分k个相等子集合698 {
    /**
     * https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/solution/hui-su-by-leemanshow-exui/
     * 桶的视角，用递归函数遍历桶，在递归函数中再遍历数组，对数字做选择（取该数放入痛中或者不放）
     * 这种方式复杂度大，没办法通过所有用例，会超时。
     */
    int target = 0;
    //    boolean[] visited;
    Map<Integer, Boolean> map = new HashMap<>();

    boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        // visited = new boolean[nums.length];
        int used = 0; // 使用位图技巧,存储数组中元素的使用情况，int有32位。
        // 从低位开始0为未使用1为已使用，如要查询第i位：used>>i&1==1?true:fals（并）；要设置第i位为1：used = used|1<<i (因为i是从低位开始，所以要左移)；要设置第i位为0：used = used^1<<i（异或）
        target = sum / k;
        return traverse(k, 0, nums, 0, used);
    }

    /**
     * 现在 k 号桶正在思考是否应该把 nums[numsIndex] 这个元素装进来；目前 k 号桶里面已经装的数字之和为 bucketSum；
     * used 标志某一个元素是否已经被装到桶中；target 是每个桶需要达成的目标和。
     * @param index 桶的序号
     * @param bucketSum 目前 k 号桶里面已经装的数字之和
     * @param nums nums数组
     * @param numsIndex 这个桶遍历中的nums数组下表，这里的作用是为了快速剪枝，
     *                  因为对用同一个桶，下标numsIndex之前的数组肯定是验证过是false或者已经装入桶中的，所以对于同一个桶，直接从numsIndex开始变量就可以了，大大减少了进入递归方法的次数。
     * @param used used 标志某一个元素是否已经被装到桶中
     * @return 这个桶是否能按要求装填
     */
    private boolean traverse(int index, int bucketSum, int[] nums, int numsIndex, int used) {
        if (index == 0) {
            return true;
        }
        if (bucketSum == target) {
            boolean res = traverse(index - 1, 0, nums, 0, used);
            map.put(used, res);
        }

        if (map.containsKey(used)) {
            return map.get(used);
        }

        for (int i = numsIndex; i < nums.length; i++) {
            if (((used >> i) & 1) != 1 && bucketSum + nums[i] <= target) {
                bucketSum += nums[i];
                used = used | 1 << i;
                if (traverse(index, bucketSum, nums, i + 1, used)) {
                    return true;
                }
                bucketSum -= nums[i];
                used = used ^ 1 << i;
            }
        }
        return false;
    }
}
