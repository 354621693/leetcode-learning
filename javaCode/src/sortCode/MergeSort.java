package javaCode.src.sortCode;

/**
 * @author Leemanshow
 * @description ${DESCRIPTION}
 * @date 2020-06-29-19:06
 */

import java.util.Arrays;

/**
 * https://www.cnblogs.com/chengxiao/p/6194356.html
 * 归并排序，是创建在归并操作上的一种有效的排序算法。算法是采用分治法（Divide and Conquer）的一个非常典型的应用，且各层分治递归可以同时进行。归并排序思路简单，速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列。
 * 1. 基本思想
 * 归并排序是用分治思想，分治模式在每一层递归上有三个步骤：
 * 分解（Divide）：将n个元素分成个含n/2个元素的子序列。
 * 解决（Conquer）：用合并排序法对两个子序列递归的排序。
 * 合并（Combine）：合并两个已排序的子序列已得到排序结果。
 * 2. 实现逻辑
 * 2.1 迭代法
 * ① 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 * ② 设定两个指针，最初位置分别为两个已经排序序列的起始位置
 * ③ 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 * ④ 重复步骤③直到某一指针到达序列尾
 * ⑤ 将另一序列剩下的所有元素直接复制到合并序列尾
 * 2.2 递归法
 * ① 将序列每相邻两个数字进行归并操作，形成floor(n/2)个序列，排序后每个序列包含两个元素
 * ② 将上述序列再次归并，形成floor(n/4)个序列，每个序列包含四个元素
 * ③ 重复步骤②，直到所有元素排序完毕
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] res) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, res);
            sort(arr, mid + 1, right, res);
            merge(arr, left, mid, right, res);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        //临时数组指针
        int m = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[m++] = arr[i++];
            } else {
                temp[m++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[m++] = arr[i++];
        }
        while (j <= right) {
            temp[m++] = arr[j++];
        }

        m = 0;
        while (left <= right) {
            arr[left++] = temp[m++];
        }

    }
}
