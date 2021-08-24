class Solution {
    public int search(int[] nums, int target) {
        int res = -1;
        
        if(nums == null || nums.length == 0){
            return res;
        }

        int left = 0;
        int right = nums.length - 1;
        while(left <= right){ // 注意这里是 <=，否则比如int[] nums = [5], target = 5时，程序会执行错误.
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return res;
    }
}