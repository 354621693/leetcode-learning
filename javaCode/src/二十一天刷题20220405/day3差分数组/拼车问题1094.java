package 二十一天刷题20220405.day3差分数组;

/**
 *  链接：https://leetcode-cn.com/problems/car-pooling/solution/by-leemanshow-nb89/
 *
 *  车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 * 示例 1：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 * 示例 2：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 * 提示：
 *
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 105
 */
public class 拼车问题1094 {
    int[] diff = new int[1002];
    int max = 0;
    public boolean carPooling(int[][] trips, int capacity) {
        for(int i = 0;i<=trips.length-1;i++){
            diff[trips[i][1]+1] += trips[i][0];
            diff[trips[i][2]+1] -= trips[i][0];
            if(trips[i][2]+1>max){
                max = trips[i][2]+1;
            }
        }
        for(int i = 1;i<max+1;i++){
            diff[i] +=diff[i-1];
            if(diff[i]>capacity){
                return false;
            }
        }
        return true;
    }

}
