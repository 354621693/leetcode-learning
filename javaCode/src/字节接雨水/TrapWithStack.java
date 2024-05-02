package 字节接雨水;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrapWithStack {
    /**
     * 使用单调栈，我们在之前的解题中，思路都是寻找i点的左右两边最近的比自身高度高的元素。
     * 这种场景，应该要联想到可以使用单调栈，单调栈的作用就是以O(n)的复杂度寻找每个元素的最近的更小/更大元素
     * 寻找更大元素，应该使用单调递减栈
     * 先写出单调栈的基础模板，我们应该意识到，在单调栈中，处理逻辑应该在元素出栈的时候
     * 元素出栈的时候，计算这个元素i头顶的高度的水量，但是这里要注意，跟前面的方法不同，这里计算的是i的高度，到他的左右最近的比他高的“两边”之间的水量。
     * 前面的方法是纵向的，单调栈的方法是横向的。
     *
     * https://leetcode.cn/problems/trapping-rain-water/solutions/692342/jie-yu-shui-by-leetcode-solution-tuvc/
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int right = i;
                Integer pop = stack.pop();
                // 取出栈顶后，如果栈已经空了，表示前面所有的元素都比i的高度低，没有必要再算了（有效雨水在前面的出栈已经计算出来了）
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int curHeight = Math.min(height[right], height[left]) - height[pop];
                ans += curHeight * (right - left - 1);
            }
            stack.push(i);
        }
        return ans;
    }
}
