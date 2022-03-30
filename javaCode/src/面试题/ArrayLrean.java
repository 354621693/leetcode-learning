package 面试题;

import java.util.TreeSet;

public class ArrayLrean {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet treeSet = new TreeSet<Long>();
        for (int i = 0; i < nums.length; i++) {
            Long floor = (Long) treeSet.floor(nums[i] + t);
            if (floor != null && floor >= nums[i] - t) {
                return true;
            }
            treeSet.add(nums[i]);
            if (i >= k) {
                treeSet.remove(nums[i - k]);
            }
        }
        return false;
    }


    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     * <p>
     * 示例 1:
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     * 输入: "race a car"
     * 输出: false
     * <p>
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xah8k6/
     * 双指针
     */
    public static boolean isPalindromes(String s) {
        int l = 0, r = s.length() - 1;
        while (l <= r) {
            if (!isLetterOrDigit(s.charAt(l))) {
                l++;
                continue;
            }
            if (!isLetterOrDigit(s.charAt(r))) {
                r--;
                continue;
            }
            if (Character.toUpperCase(s.charAt(l++)) != Character.toUpperCase(s.charAt(r--))) {
                return false;
            }

        }
        return true;
    }

    public static boolean isLetterOrDigit(char c) {
        int index = (int) c;
        return index >= 48 && index <= 57 || index >= 65 && index <= 90 || index >= 97 && index <= 122;
    }

    public static void main(String[] args) {
        boolean palindromes = isPalindromes("A ma, a plan, a canal: Panama");
        System.out.println(palindromes);
    }
}
