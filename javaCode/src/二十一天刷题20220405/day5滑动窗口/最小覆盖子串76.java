package 二十一天刷题20220405.day5滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * <p>
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 */
public class 最小覆盖子串76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> window = new HashMap<>(), needs =
                new HashMap<>();
        window.getOrDefault(s, 0);
        //用于统计达到要求的字符的数量，比如AABC，有3种、4个字符，当他们都满足时，valid=3
        int valid = 0;
        //定义：左闭右开，即窗口的范围是：[left,right)，这是常用搭配，这样可以做到当left,right都为0时，子串是空
        int left = 0, right = 0;
        int start = 0, minLength = Integer.MAX_VALUE;
        //构建needs表
        for (char c : t.toCharArray()) {
            if (needs.containsKey(c)) {
                needs.put(c, needs.get(c) + 1);
            } else {
                needs.put(c, 1);
            }
        }
        while (right < s.length()) {
            right++;
            char r = s.charAt(right - 1);
            if (needs.containsKey(r)) {
                if (window.containsKey(r)) {
                    window.put(r, window.get(r) + 1);
                }
                window.putIfAbsent(r, 1);
                if (needs.get(r).equals(window.get(r))) {
                    valid++;
                }
            }
            System.out.println(left+","+right);
            //当valid的大小等于t的字符种数，表示[left,right)已经满足条件，可以将left右移
            while (valid == needs.size()) {
                //即将被移出的char
                char d = s.charAt(left);
                if (needs.containsKey(d)) {
                    window.put(d, window.get(d) - 1);
                    if(window.get(d)<needs.get(d)) {
                        valid--;
                    }
                    if (right - left < minLength) {
                        start = left;
                        minLength = right - left;
                    }
                }
                left++;
            }
        }
        if (minLength != Integer.MAX_VALUE) {
            return s.substring(start, start + minLength);
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        最小覆盖子串76 aa = new 最小覆盖子串76();
        String s = aa.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
    }
}
