package 字节接雨水;

public class TrapWithDoublePointer {

    /**
     * 在上一个解法中，我们使用两个数组作为记录表，记录了i点左右两边的max，最后再对比两个数组得出每个i点的水量
     * 我们的空间复杂度是O(n)，现在再仔细想想，我们的目的是计算总水量，为此我们要算出i左右的最高高度，l_max和r_max再进行比对
     * 有没有可能我们可以一边遍历一边比对呢，如果可以这样，我们就可以一边遍历一边计算最终结果，而无需保存中间值
     * 回到上一种解法，我们先从左到右遍历然后从右到左，目的是为了什么？是为了能准确地找到l_max和r_max的值
     * 这里我们再想想，我们真的需要l_max和r_max的准确值吗？其实不是，我们只需要知道两者比较中较小的那个，回想一下我们计算结果的过程：ans+=Math.min(r_max,l_max)-height[i]，显然min（）中较大那个值具体是什么值并不重要
     * 现在再回忆一个重点，已知一个i点的l_max，怎么求i+1这个点的l_max？答案是max（l_max，height[i+1]）,r_max同理
     * 现在设想一下，我们把上一种解法中两次遍历组合在一起，这就是类似双指针的思路了！
     * 首先我们有两个指针left、right，分别从两边开始遍历，初始状态，left的l_max是确定的，right的r_max是确定的。
     * 开始遍历，如果left的l_max比right的r_max还要小，我们上面已经说过，我们并不需要一个点的l_max和r_max的准确值，只需要知道两者的最小值，只要知道left的l_max比right的r_max还要小，就代表对于left，Math.min(r_max,l_max)已经确定了。
     * 换成right数组也是同理。
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int l_max = 0, r_max = 0;
        int ans = 0;
        while (left < right) {
            l_max = Math.max(height[left], l_max);
            r_max = Math.max(height[right], r_max);
            if (l_max < r_max) {
                ans += l_max - height[left++];
            } else {
                ans += r_max - height[right--];
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
