package 二十一天刷题20220405.day3差分数组;

/**
 * https://labuladong.gitee.io/algo/images/%e5%b7%ae%e5%88%86%e6%95%b0%e7%bb%84/title1.png
 */
public class 区间加法370 {
    public int[] operation(int length,int[][] updates){
        Difference difference = new Difference(new int[length]);
        for (int[] update : updates){
            difference.increment(update[0],update[1],update[2]);
        }
        return difference.getNums();
    }
    public static void main(String a[]){
        区间加法370 ss = new 区间加法370();
        int[] operation = ss.operation(5, new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}});
        return;
    }
}
