/**
 * O(n), S(1)
 */
class Solution {
    public int findPeakElement(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] > nums[i + 1]){
                return i;
            }
        }
        return nums.length - 1;
    }
}

/**
 * O(log_n), S(1)
 */
class Solution {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;

        while(left <= right){
            int mid = (left + right) / 2;
            if(mid < len - 1 && nums[mid] < nums[mid + 1]){ // 不能越界，同时如果mid小于其左右任意一个值的话，则mid不可能是峰值。则从大于mid的左右任意选择一个，这个优先选右侧，但左右实际上无所谓。
                left = mid + 1;
            }else if(mid >= 1 && nums[mid] < nums[mid - 1]){
                right = mid - 1;
            }else{
                return mid; // mid在中间时，到这只能是mid比左右大。当mid为端点时，比如mid为len-1，则此时肯定大于右侧那个不存在的值，同时，如果比左侧大(第二个if)，则也会执行到这里
            }
        }

        return -1;
    }
}

/**
 * O(log_n), S(1)
 */
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = (left + right) / 2;
            if(nums[mid] > nums[mid + 1]){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return right;
    }
}

//   0
//  0 0
// 0