package 二十一天刷题20220405.day3差分数组;

/**
 * 差分数组工具类
 */
public class Difference {
    // 差分数组
    int[] diff;

    /* 输入一个初始数组，区间操作将在这个数组上进行 */
    public Difference(int[] nums) {
        assert nums.length > 0;
        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    /* 给闭区间 [i,j] 增加 val（可以是负数）*/
    public void increment(int i, int j, int num) {
        diff[i] += num;
        if (j < diff.length - 1) {
            diff[j + 1] -= num;
        }
    }

    /* 返回结果数组 */
    public int[] getNums() {
        int[] res = new int[diff.length];
        // 根据差分数组构造结果数组
        res[0] = diff[0];
        for (int i = 1; i < res.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }
}
