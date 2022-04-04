package dynamicProgramming;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 泰波那契序列Tn定义如下：
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数n，请返回第 n 个泰波那契数Tn 的值。
 * 示例 1：
 *
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * 示例 2：
 *
 * 输入：n = 25
 * 输出：1389537
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-th-tribonacci-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * dp()
 */
public class 第N个泰波那契数 {
    public static HashMap<Integer,Integer> map = new HashMap(32);
    public int tribonacci(int n) {
        if(n<=1){
            return n;
        }
        if(n==2){
            return 1;
        }
        if(map.containsKey(n)){
            return map.get(n);
        }
        int res = tribonacci(n-1)+tribonacci(n-2)+tribonacci(n-3);
        map.put(n,res);
        return res;

    }

}
