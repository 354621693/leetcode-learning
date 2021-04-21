package 面试题;

/**
 * @author Leemanshow
 * @description ${DESCRIPTION}
 * @date 2021-04-21-17:31
 */
public class Solution {
    /**
     * 搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmlwi1/
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int c = matrix[0].length-1;
        int r = 0;
        while(c>=0&&r<=matrix.length-1){
            if(matrix[r][c]==target){
                return true;
            }
            if(matrix[r][c]>target){
                c--;
            }else{
                r++;
            }
        }
        return false;
    }

    public static void main(String a[]){
        int[][] b = {{1,1}};
        Solution s = new Solution ();
        s.searchMatrix (b,0);
    }

    /**
     * 合并两个有序数组
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
     *
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmi2l7/
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        m--;
        n--;
        int index = nums1.length-1;
        while(m>=0&&n>=0){
            if(nums1[m]>nums2[n]){
                nums1[index--] = nums1[m--];
            }else{
                nums1[index--] = nums2[n--];
            }
        }
        while(n>=0){
            nums1[n] = nums2[n];
            n--;
        }
    }
}
