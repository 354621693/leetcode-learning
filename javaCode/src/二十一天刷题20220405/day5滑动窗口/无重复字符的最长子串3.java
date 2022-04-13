package 二十一天刷题20220405.day5滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leewencan
 * @date 2022/4/11 10:44
 */
public class 无重复字符的最长子串3 {
    //滑动窗口
    public int lengthOfLongestSubstring(String s) {
        //左闭右开
        int left=0,right =0;
        Map<Character,Integer> map = new HashMap<>();
        int max = 0;
        while(right<s.length()){
            //即将加入窗口的字符
            char ch = s.charAt(right);
            right++;
            int value = map.getOrDefault(ch,0);
            map.put(ch,++value);
            if(value>1){
                while(map.get(ch)!=1){
                    //即将被移出窗口的字符
                    char temp = s.charAt(left);
                    left++;
                    map.put(temp,map.get(temp)-1);
                }
            }
            max=Math.max(max,right-left);
        }
        return max;
    }

    public static void main(String a[]){
        System.out.println(new 无重复字符的最长子串3().lengthOfLongestSubstring("abcabcbb"));
    }
}
