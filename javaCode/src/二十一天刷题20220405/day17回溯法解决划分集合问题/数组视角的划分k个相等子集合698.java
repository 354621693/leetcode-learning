package 二十一天刷题20220405.day17回溯法解决划分集合问题;

import java.util.Arrays;

public class 数组视角的划分k个相等子集合698 {
    /**
     * 数组中的元素视角，递归遍历数组中的元素，在递归方法中对一个一个桶k做选择，只要能放就放进去
     * 这种方式复杂度大，没办法通过所有用例，会超时。
     * @param nums
     * @param k
     * @return
     */
    int target = 0;

    boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int[] bucket = new int[k];
        Arrays.sort(nums);
        for(int i = 0,j=nums.length-1;i<j;i++,j--){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        target = sum / k;
        return traverse(nums, bucket, 0);
    }

    private boolean traverse(int[] nums, int[] bucket, int index) {
        if (index == nums.length) {
            for (int bucketSum : bucket) {
                if (bucketSum != target) {
                    return false;
                }
            }
            return true;
        }
        //做选择，选择放入哪一个桶
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] + nums[i] > target) {
                continue;
            }
            bucket[i] += nums[index];
            // 如果成功，透传下层的结果
            if (traverse(nums, bucket, index + 1)) {
                return true;
            }
            // 如果失败，撤销
            bucket[i] -= nums[index];
        }
        return false;
    }
}
