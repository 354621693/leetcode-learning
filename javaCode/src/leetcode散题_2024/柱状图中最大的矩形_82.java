package leetcode散题_2024;

import java.util.*;

/**
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/description/
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class 柱状图中最大的矩形_82 {
    /**
     * 暴力解法
     * 已知柱子的高度，那么就是寻找以这个柱子i为原点，向左右两边寻找第一个比i还要矮的柱子，然后左右的距离就是长，i的高度就是宽，即可得到面积
     * 遍历每一个柱子，就可以比对出最大面积。
     */
    public int largestRectangleArea_baoli(int[] heights) {
        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = 0, right = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] < heights[i]) {
                    break;
                }
                left++;
            }
            for (int j = i + 1; j < heights.length; j++) {
                if (heights[j] < heights[i]) {
                    break;
                }
                right++;
            }
            int temp = (left + right + 1) * heights[i];
            result = Math.max(result, temp);
        }
        return result;

    }

    /**
     * 单调栈
     * 在暴力解法中，关键点就是寻找i点柱子左右两边离自己最近的更小元素。
     * 刚好单调栈就适合做这个事情。
     * 单调栈的特性，我们使用单调递增栈（栈底到栈顶，元素增大），
     * 当i 和栈顶元素对比，需要栈顶元素出栈时，i是栈顶元素右边的第一个更小元素，记这个出栈元素为e，出栈的一瞬间（i尚未入栈），新的栈顶元素就是e的左右的第一个更小元素
     */
    public static int largestRectangleArea_MonotonicStack(int[] heights) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                //需要栈顶元素出栈时，i是栈顶元素右边的第一个更小元素
                int right = i;
                Integer pop = stack.pop();
                //出栈的一瞬间（i尚未入栈），新的栈顶元素就是e的左右的第一个更小元素
                int width;
                if (stack.isEmpty()) {
                    width = right;
                } else {
                    width = right - stack.peek() - 1;
                }
                //注意，我们上面讨论的是出栈的元素，也就是pop，而非遍历到的i
                ans = Math.max(ans, heights[pop] * width);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            //遍历到最后都还没出栈的元素，右边肯定是最后一个元素了（或边界元素本身）
            int right = heights.length;
            Integer pop = stack.pop();
            //出栈的一瞬间（i尚未入栈），新的栈顶元素就是e的左右的第一个更小元素
            int left = stack.isEmpty() ? -1 : stack.peek();
            //注意，我们上面讨论的是出栈的元素，也就是pop，而非遍历到的i
            ans = Math.max(ans, heights[pop] * (right - left - 1));
        }
        return ans;
    }


    /**
     * 哨兵优化（在数组左右各加上高度为0的元素，可以避免考虑边界情况
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea_StackWithSentinel(int[] heights) {
        int len = heights.length;
        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        //在数组左右各加上高度为0的元素
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len+1] = 0;
        len += 2;
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {

                Integer pop = stack.pop();
                int width;
                // 因为数组的末尾一定是0，那么前面的非0高度一定都会出栈
                ans = Math.max(ans, newHeights[pop] * (i - stack.peek() - 1));

            }
            stack.push(i);
        }

        return ans;
    }

    public static void main(String[] a) {
        int[] array = new int[]{9, 0};
        largestRectangleArea_MonotonicStack(array);
    }

    private static void extracted() {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int[] array = new int[]{1, 3, 2, 8, 5, 1, 4, 9, 3};
        for (int i = 0; i < array.length; i++) {
            List<Integer> list = new ArrayList<>();
            while (!stack.isEmpty() && stack.peek() > array[i]) {
                // 当栈顶元素要出栈时，这个array[i]是栈顶元素向后最近的更小元素
                Integer pop = stack.pop();
                // 当元素出栈后，新的栈顶元素（未出栈时的top-1）
                list.add(pop);
            }
            stack.push(array[i]);
            printStack(stack, list);
        }
    }

    private static void printStack(Deque<Integer> stack, List<Integer> list) {
        System.out.print("出栈元素：");
        list.forEach(System.out::print);
        System.out.print("\t 栈：");
        for (Integer integer : stack) {
            System.out.print(integer);
        }
        System.out.println("");
    }
}
