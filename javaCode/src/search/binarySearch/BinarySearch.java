package search.binarySearch;

/**
 * @author leewencan
 * @date 2022/3/24 10:02
 * 定义区间：左闭右闭
 */
public class BinarySearch {
    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            System.out.println(mid);
            if (nums[mid] == target) {
                return mid;
            }
            if (target > nums[mid]) {
                low = mid + 1;
            }
            if (target < nums[mid]) {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] a) {
        int[] ints = {1, 2, 4, 6, 8, 9, 31, 45, 53, 77, 77, 77, 77, 77, 78, 79};
        System.out.println(ints.length);
        int search = search(ints, 1000);
        System.out.println(search);
        System.out.println(search>0?ints[search]:"no");
    }
}
