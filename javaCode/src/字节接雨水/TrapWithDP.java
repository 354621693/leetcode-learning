package 字节接雨水;

public class TrapWithDP {
    /**
     * 暴力解法的思路是找到第i个点的左右两边的最高柱子，再找出两者的最小值，再用这个最小值减去height[i]的高度，就是第i个点的雨水量。
     * 那么有没有办法可以减少时间复杂度呢，暴力解法中的for循环，每次都是从第i点出发，向两边逐个查找，显然每次for循环中有重复的地方。
     * 有没有办法只遍历一次就能得到所有的i点左右两边的最高柱子呢
     * 假设第i点左右最高柱子的高度是l_max、r_max，显然当i=0时，l_max就是0，当i=length-1时，r_max就是0
     * 第i+1个点的l_max,只需要用第i个点的l_max和height[i+1]比较就能得出，r_max同理
     * 因此，我们需要两次遍历，一次从左往右遍历，得到所有i点的l_max，记录为array_l_max（数组），显然array_l_max[0]=0
     * 一次从右往左遍历，得到所有i点的r_max，记录为array_r_max（数组），显然array_r_max[length-1]=0
     * 上述两次遍历之后，得到的两个数组，分别记录了i点的左、右最高柱子的高度，接下来再用一个for循环找两者的最小值，再减去i点本身的高度，再逐个累加即可得到答案
     * <p>
     * 时间复杂度O(n)，空间复杂度O(n)
     * <p>
     * 用记录表的形式来优化暴力算法的每次从头遍历，这种思路很像动态规划
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int l_max = 0;
        int[] array_l_max = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            l_max = Math.max(height[i], l_max);
            array_l_max[i] = l_max;
        }

        int r_max = 0;
        int[] array_r_max = new int[height.length];
        for (int j = height.length - 1; j > -1; j--) {
            r_max = Math.max(height[j], r_max);
            array_r_max[j] = r_max;
        }
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            ans += Math.min(array_l_max[i], array_r_max[i]) - height[i];
        }
        return ans;

    }
}
