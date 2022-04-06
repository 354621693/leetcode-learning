package 二十一天刷题20220405.day2前缀和数组;

/**
 * @author leewencan
 * @date 2022/4/6 19:29
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 * <p>
 * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
 * 实现 NumMatrix 类：
 * <p>
 * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
 *  
 * <p>
 * 示例 1：
 * 输入:
 * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 * 输出:
 * [null, 8, 11, 12]
 * <p>
 * 解释:
 * NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-2d-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 区域和检索前缀和二维数组版304 {
    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
    private final int[][] preSums;

    public 区域和检索前缀和二维数组版304(int[][] matrix) {
        preSums = new int[matrix.length][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                preSums[i][j] = preSums[i][j - 1] + matrix[i][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int i = row1; i <= row2; i++) {
            res += preSums[i][col2 + 1] - preSums[i][col1];
        }
        return res;
    }

    public static void main(String a[]) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        区域和检索前缀和二维数组版304 numMatrix = new 区域和检索前缀和二维数组版304(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
        NumMatrix aa = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }


    /**
     *
     */
     static class NumMatrix {
        int[][] pre;

        public NumMatrix(int[][] matrix) {
            pre = new int[matrix.length+1][matrix[0].length+1];
            for(int i = 1 ; i<=matrix.length;i++){
                for(int j = 1;j<=matrix.length;j++){
                    pre[i][j] = pre[i-1][j]+pre[i][j-1]-pre[i-1][j-1]+matrix[i-1][j-1];
                }
            }
            System.out.println("");
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            /*TODO 未解决：
            ["NumMatrix","sumRegion","sumRegion","sumRegion"]
                [[[[-4,-5]]],[0,0,0,0],[0,0,0,1],[0,1,0,1]]
                输出：
                [null,-4,0,4]
                预期结果：
                [null,-4,-9,-5]
            * */
            return pre[row2+1][col2+1] - pre[row1][col2+1] - pre[row2+1][col1]+pre[row1][col1];
        }
    }
}
