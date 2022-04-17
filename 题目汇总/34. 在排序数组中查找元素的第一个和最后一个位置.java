/**
 * 题解思路
 * O(log(n)), S(1)
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-su-de-di-3-4/
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int leftIndex = binarySearch(nums, target, true);
        int rightIndex = binarySearch(nums, target, false) - 1;

        if(rightIndex < leftIndex){
            return new int[]{-1, -1};
        }

        return new int[]{leftIndex, rightIndex};
        
    }

    private int binarySearch(int[] nums, int target, boolean lower){
        // 假设只有一个数时，比如 int[] nums = new int[]{5}, target = 5, 
        // 则无法找到比5大的下标。因此设index = nums.length，方便之后在主函数中减1，得到rightIndex.
        int index = nums.length; 

        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;

            // 二分查找中，寻找leftIdx, 即为在数组中寻找第一个大于等于target的下标；
            // 寻找rightIdx, 即为在数组中寻找第一个大于target的下标，然后将下标减一。
            if(nums[mid] > target || (lower && nums[mid] == target)){
                right = mid - 1;
                index = mid;
            }else{
                left = mid + 1;
            }
        }

        return index;
    }
}

/**
 * 自己的思路
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {

        int left = binarySearchLeft(nums, target);
        int right = binarySearchRight(nums, target);

        if(left > right){
            return new int[]{-1, -1};
        }

        return new int[]{left, right};
    }

    private int binarySearchLeft(int[] nums, int target){ // 找到max(num < target)
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] >= target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return right + 1; // return right 是找到max(num < target)，加 1 是找到第一个 target
    }

    private int binarySearchRight(int[] nums, int target){ // 找到min(num > target)
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] <= target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        return left - 1;
    }
}

/**
 * 自己的递归思路，没有题解好
 * O(log(n)), S(log(n))
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};

        if(nums == null || nums.length == 0){
            return res;
        }

        int left = 0;
        int right = nums.length - 1;
        res[0] = Integer.MAX_VALUE;
        searchRange(nums, target, left, right, res);
        if(res[1] == -1){
            res[0] = -1;
        }
        return res;
    }

    private void searchRange(int[] nums, int target, int left, int right, int[] res){
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                searchRange(nums, target, left, mid - 1, res);
                res[0] = Math.min(res[0], mid);
                searchRange(nums, target, mid + 1, right, res);
                res[1] = Math.max(res[1], mid);
                return;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
    }
}