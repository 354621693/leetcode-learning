package 二十一天刷题20220405.day5滑动窗口;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 *
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 */
public class 串联所有单词的字串30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList();
        Map<String, Integer> wordsMap = new HashMap<>();

        int left = 0, right = 0;
        int wLength = words[0].length();
        //窗口[left,right)
        for (String str : words) {
            if (wordsMap.containsKey(str)) {

                wordsMap.put(str, wordsMap.get(str) + 1);
            }
            wordsMap.putIfAbsent(str, 1);
            right += str.length();
        }

        while (right <= s.length()) {
            if (legal(s, left, right, wordsMap, wLength)) {
                res.add(left);
            }
            left++;
            ;
            right++;
        }
        return res;
    }

    public boolean legal(String str, int left, int right, Map<String, Integer> wordsMap, int wLength) {
        Map<String, Integer> window = new HashMap<>();
        int valid = 0;
        while (left < right) {
            String sub = str.substring(left, left + wLength);
            if (wordsMap.containsKey(sub)) {
                int value = window.getOrDefault(sub, 0);
                window.put(sub, value + 1);
                if (window.get(sub).equals(wordsMap.get(sub))) {
                    valid++;
                }
            } else {
                return false;
            }
            left += wLength;
        }
        return valid == wordsMap.size();
    }

    public static void main(String a[]) {
        串联所有单词的字串30 aa = new 串联所有单词的字串30();
        List<Integer> wordgoodgoodgoodbestword = aa.findSubstring("wordgoodgoodgoodbestword"
                , new String[]{"word", "good", "best", "good"});
        System.out.println(wordgoodgoodgoodbestword);

    }
}
