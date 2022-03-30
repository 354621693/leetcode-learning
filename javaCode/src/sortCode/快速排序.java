package sortCode;

import java.util.Arrays;

/**
 * @author leewencan
 * @date 2022/3/22 16:07
 */
public class 快速排序 {
    public static int[] sort(int[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void sort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int p = partition(nums, low, high);
        sort(nums, low, p - 1);
        sort(nums, p + 1, high);
    }

    private static int partition(int[] nums, int low, int high) {
        int pivo = nums[low];
        int i = low + 1;
        int j = high;
        while (i <= j) {
            while (i <= high && nums[i] <= pivo) {
                i++;
            }
            while (j > low && nums[j] > pivo) {
                j--;
            }
            System.out.println(i + "," + j);
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, low, j);
        System.out.println("p:" + j);
        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void shuffle(int[] num) {

    }


    public static void main(String[] s) {
        int[] n = {2, 45, 9, 6, 9, 3, 5, 2, 4, 2, 6};
        int[] m = {2, 45, 9, 6, 9, 3, 5, 2, 4, 2, 6};
        int[] sort = sort(n);
        for (int i : sort) {
            System.out.print(i);
            System.out.print(",");
        }
        quickSort(m, 0, n.length - 1);
        System.out.println(Arrays.toString(m));
        for (int i : m) {
            System.out.print(i);
            System.out.print(",");
        }
    }


    public static void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int index = getIndex(nums, low, high);
        quickSort(nums, low, index - 1);
        quickSort(nums, index + 1, high);
    }

    private static int getIndex(int[] nums, int low, int high) {
        if (low == high) {
            return nums[0];
        }
        int temp = nums[low];
        while (low < high) {
            while (nums[high] > temp) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] <= temp) {//这里多了一个等号，因为temp取的是左边第一个数字，所以左向右是闭区间
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = temp;
        return low;
    }


}
