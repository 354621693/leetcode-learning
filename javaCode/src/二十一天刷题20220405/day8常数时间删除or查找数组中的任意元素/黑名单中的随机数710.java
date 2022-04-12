package 二十一天刷题20220405.day8常数时间删除or查找数组中的任意元素;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单 blacklist 的整数。任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
 *
 * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
 *
 * 实现 Solution 类:
 *
 * Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
 * int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
 *  
 *
 * 示例 1：
 *
 * 输入
 * ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
 * [[7, [2, 3, 5]], [], [], [], [], [], [], []]
 * 输出
 * [null, 0, 4, 1, 6, 1, 0, 4]
 *
 * 解释
 * Solution solution = new Solution(7, [2, 3, 5]);
 * solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
 *                  // 0、1、4和6的返回概率必须相等(即概率为1/4)。
 * solution.pick(); // 返回 4
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 6
 * solution.pick(); // 返回 1
 * solution.pick(); // 返回 0
 * solution.pick(); // 返回 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/random-pick-with-blacklist
 *
 * https://leetcode-cn.com/problems/random-pick-with-blacklist/solution/ha-by-leemanshow-j02c/
 */
public class 黑名单中的随机数710 {
    Map<Integer,Integer> map;
    int sz;
    public 黑名单中的随机数710(int n, int[] blacklist) {
        map = new HashMap<>();
        for(int black:blacklist){
            map.put(black,888);
        }
        //开区间
        sz = n - blacklist.length;
        int index = sz;
        for(int black:blacklist){
            if(black>=sz){
                continue;
            }
            while(map.containsKey(index)){
                index++;
            }
            map.put(black,index++);
        }
        // map.forEach((k,v)->{
        //     System.out.println(k+","+v);
        // });
    }

    public int pick() {
        Random random = new Random();
        int res = random.nextInt(sz);
        if(map.containsKey(res)){
            return map.get(res);
        }
        return res;
    }

    public static void main(String[] args) {
        黑名单中的随机数710 aa = new 黑名单中的随机数710(10,new int[]{3,5,8,9});
        int pick = aa.pick();
    }
}
