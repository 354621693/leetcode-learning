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

    //冒泡排序
    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }
    //选择排序
    public static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }
    //插入排序
    public static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > temp) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = temp;
        }
    }
    //希尔排序
    public static void shellSort(int[] nums) {
        int gap = nums.length / 2;
        while (gap > 0) {
            for (int i = gap; i < nums.length; i++) {
                int temp = nums[i];
                int j = i - gap;
                while (j >= 0 && nums[j] > temp) {
                    nums[j + gap] = nums[j];
                    j -= gap;
                }
                nums[j + gap] = temp;
            }
            gap /= 2;
        }
    }
    //归并排序
    public static void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private static void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= high) {
            temp[k++] = nums[j++];
        }
        for (int l = 0; l < temp.length; l++) {
            nums[low + l] = temp[l];
        }
    }
    //堆排序
    public static void heapSort(int[] nums) {
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);
            adjustHeap(nums, 0, i);
        }
    }

    private static void adjustHeap(int[] nums, int low, int high) {
        int temp = nums[low];
        int i = low;
        int j = 2 * i + 1;
        while (j < high) {
            if (j + 1 < high && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[j] > temp) {
                nums[i] = nums[j];
                i = j;
                j = 2 * i + 1;
            } else {
                break;
            }
        }
        nums[i] = temp;
    }
    //计数排序
    public static void countSort(int[] nums) {
        int max = getMax(nums);
        int[] count = new int[max + 1];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                nums[index++] = i;
                count[i]--;
            }
        }
    }
    //基数排序
    public static void radixSort(int[] nums) {
        int max = getMax(nums);
        for (int i = 1; max / i > 0; i *= 10) {
            countSort(nums, i);
        }
    }

    private static void countSort(int[] nums, int digit) {
        int max = getMax(nums);
        int[] count = new int[10];
        for (int i = 0; i < nums.length; i++) {
            count[(nums[i] / digit) % 10]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        int[] temp = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            temp[count[(nums[i] / digit) % 10] - 1] = nums[i];
            count[(nums[i] / digit) % 10]--;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];

        }
    }
    //桶排序
    public static void bucketSort(int[] nums) {
        int max = getMax(nums);
        int bucketNum = 10;
        int[][] bucket = new int[bucketNum][];
        for (int i = 0; i < bucketNum; i++) {
            bucket[i] = new int[nums.length];
        }
        for (int i = 0; i < nums.length; i++) {
            bucket[(nums[i] * bucketNum) / max][i] = nums[i];
        }
        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            for (int j = 0; j < bucket[i].length; j++) {
                if (bucket[i][j] != 0) {
                    nums[index++] = bucket[i][j];
                }
            }
        }
    }
    public static int getMax(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}
