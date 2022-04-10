package 二十一天刷题20220405.day6二分搜索的边界问题;

public class 在排序数组中查找元素的第一个和最后一个位置34 {
    //定义区间为：[left,right)
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int start = 0,end=0;
        int left = 0,right = nums.length;
        //先找左边界,当left=right时终止循环
        while(left<right){
            int mid = left+(right-left)/2;
            if(nums[mid]==target){
                right = mid;
            }else if(nums[mid]>target){
                right = mid;
            }else if(nums[mid]<target){
                left = mid+1;
            }
        }
        if(left==nums.length||nums[left]!=target){
            res[0]=-1;
            res[1] = -1;
            return res;
        }else{
            start = left;
        }
        left = 0;
        right = nums.length;
        //再重新循环找又边界
        while(left<right){
            int mid = left+(right-left)/2;
            if(nums[mid]==target){
                left = mid+1;
            }else if(nums[mid]>target){
                right = mid;
            }else if(nums[mid]<target){
                left = mid+1;
            }
        }
        end = left-1;
        res[0] = start;
        res[1] = end;
        return res;
    }
}
