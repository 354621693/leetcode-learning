package seek;

/**
 * @author leewencan
 * @date 2022/3/24 10:02
 */
public class BinarySearch {
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (target < nums[mid]) {
                right = mid - 1;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] a) {
        int[] ints = {1, 2, 4, 6, 8, 9, 31, 45, 53, 77, 77, 77, 77, 77};
        int search = search(ints, 77);
        System.out.println(search);
        System.out.println(ints[search]);
    }
}
