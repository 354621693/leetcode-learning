package 字节接雨水;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class 接雨水42 {

    /**
     * 暴力解法
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0;
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }
            int rightMax = 0;
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            int shuiValue = Math.min(leftMax, rightMax) - height[i];
            if (shuiValue > 0) {
                ans += shuiValue;
            }

        }
        return ans;
    }

    public static void main(String[] d) {
        long start = System.currentTimeMillis();
        int trap = trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(trap + " " + (System.currentTimeMillis() - start) + "ms");
    }

}
