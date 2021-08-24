/**
 * 根据官解思路，自己默写的
 * O(log(n)), S(1)
 */
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) >> 1;

            if(nums[mid] == target){
                return mid;
            }else if(nums[0] <= nums[mid]){ // 如果此时mid在前部分
                if(nums[0] <= target && target < nums[mid]){ // 如果target的下标在下标mid之前 (需用两个条件判断)
                    right = mid - 1;
                }else{ // 如果target的下标在mid之后
                    left = mid + 1;
                }
            }else{ // 如果此时mid在后部分
                if(nums[mid] < target && target <= nums[nums.length - 1]){ // 如果target的下标在下标mid之后 (需用两个条件判断)
                    left = mid + 1;
                }else{ // 如果target的下标在mid之前
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
}


/**
 * 自己的思路，没有官解好，里面的实现也是错的
 * O(log(n)), S(1)
 */
class Solution {
    public int search(int[] nums, int target) {
        // 思路是对的，但是方法是错的，比如[3,1,3]，找到的smallestIndex是nums[0] = 3，只不过是LeetCode的case没判断出来而已
        // 先用二分法找到最小元素的下标，因为这个最小元素就是分割点
        int left = 0;
        int right = nums.length - 1;
        int curEnd = nums[right];
        while(left <= right){ // 基于第34题思想
            int mid = (left + right) / 2;
            if(nums[mid] > curEnd){ // 如果此时中间的值大于最后面的值，则证明这个中间的值是分割点前面的，因此中间值要往后挪
                left = mid + 1;
            }else{ // 否则则证明这个mid值就是这个分割点或者是分割点后面的
                right = mid - 1;
            }
        }
        int smallestIndex = left; // 找到了最小元素的下标

        // 根据target值，判断是从前半部分找，还是从后半部分找
        if(target <= curEnd){ // 从后半部分找
            left = smallestIndex;
            right = nums.length - 1;
        }else{ // 从前半部分找
            left = 0;
            right = smallestIndex - 1;
        }

        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return -1;
    }
}