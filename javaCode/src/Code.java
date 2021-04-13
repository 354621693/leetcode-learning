package javaCode.src;

import java.util.*;

/**
 * @author Leemanshow
 * @description https://leetcode-cn.com/problems/queens-that-can-attack-the-king/
 * @date 2020-06-12-15:08
 */
public class Code {
    public static List<Integer> poke(Deque<Integer> deque) {
        List<Integer> res = new ArrayList<> ();
        while (!deque.isEmpty ()) {
            Integer first = deque.pollFirst ();
            res.add (first);
            Integer second = deque.pollFirst ();
            if (second != null)
                deque.addLast (second);
        }
        return res;
    }

    public static List<Integer> repoke(List<Integer> list) {
        Deque<Integer> deque = new ArrayDeque<> ();
        List<Integer> res = new ArrayList<> ();
        int size = list.size ();
        int i = size - 1;
        while (i >= 0) {
            deque.addFirst (list.get (i));
            if (i - 1 < 0)
                break;
            deque.addFirst (deque.pollLast ());
//            deque.addFirst (list.get (i - 2));
//            Integer first = deque.getFirst ();
            i -= 1;
        }
        while (!deque.isEmpty ()) {
            res.add (deque.pollFirst ());
        }
        return res;
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<> ();
        deque.addLast (1);
        deque.addLast (2);
        deque.addLast (3);
        deque.addLast (4);
        deque.addLast (5);
        List<Integer> poke = poke (deque);
        List<Integer> repoke = repoke (poke);
        titleToNumber ("ZY");
        return;
    }

    /**
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/53/math/114/
     * Excel表列序号
     * 给定一个Excel表格中的列名称，返回其相应的列序号。
     * 例如，
     * A -> 1
     * B -> 2
     * C -> 3
     * ...
     * Z -> 26
     * AA -> 27
     * AB -> 28
     * ...
     *
     * @param s
     * @return
     */
    public static int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length (); i++) {
            res = res + (int) ((s.charAt (i) - 64) * Math.pow (26, s.length () - i - 1));
        }
        System.out.println (res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/queens-that-can-attack-the-king/
     *
     * @param queens
     * @param king
     * @return
     */
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> res = new ArrayList<> ();
        boolean[][] flag = new boolean[8][8];
        for (int[] queen : queens) {
            flag[queen[0]][queen[1]] = true;
        }
        int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] aDirection : direction) {
            for (int x = king[0], y = king[1]; x >= 0 && x < 8 && y >= 0 && y < 8; x = x + aDirection[0], y = y + aDirection[1]) {
                if (flag[x][y]) {
                    res.add (Arrays.asList (x, y));
                    break;
                }
            }
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/explore/featured/card/top-interview-questions-easy/1/array/21/
     * 删除排序数组中的重复项
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * 示例 1:
     * 给定数组 nums = [1,1,2],
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     * 你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<> ();
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains (nums[i])) {
                set.add (nums[i]);
                nums[j] = nums[i];
                j++;
            }
        }
        return j;

    }





}
