package 二十一天刷题20220405.day4二维数组的花式遍历技巧;

import java.util.ArrayList;
import java.util.List;

public class 螺旋数组54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int rowL = matrix[0].length;
        int colL = matrix.length;
        //定义j的合法区间为(leftLimit,rightLimit)，j的合法范围为(upLimit,downLimit)，均为开区间，请养成先定义好区间再开始编程的习惯，可以避免很多边界问题
        //因为从第一行开始向右遍历，所以下一次的upLimit = 0
        int rightLimit = matrix[0].length, downLimit = matrix.length, leftLimit = -1, upLimit = 0;
        int i = 0, j = 0;
        //右下上左
        int[][] vent = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int direction = 0;
        while (res.size() < rowL * colL) {
            System.out.println(direction + "," + i + "," + j);
            res.add(matrix[i][j]);
            int tempI = i + vent[direction][0];
            int tempJ = j + vent[direction][1];
            if (direction == 0 && tempJ >= rightLimit) {
                //向下
                direction = 1;
                rightLimit--;
            }
            if (direction == 1 && tempI >= downLimit) {
                //向左
                direction = 3;
                downLimit--;
            }
            if (direction == 3 && tempJ <= leftLimit) {
                //向上
                direction = 2;
                leftLimit++;
            }
            if (direction == 2 && tempI <= upLimit) {
                //向右
                direction = 0;
                upLimit++;
            }
            i += vent[direction][0];
            j += vent[direction][1];
        }
        return res;
    }

    public static void main(String[] args) {
        螺旋数组54 ss = new 螺旋数组54();
        List<Integer> integers = ss.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
}
