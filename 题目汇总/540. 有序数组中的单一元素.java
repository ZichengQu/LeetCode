/**
 * 二分法
 * O(log(n))
 */
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(!((mid - 1 >= 0 && nums[mid - 1] == nums[mid]) || (mid + 1 < nums.length && nums[mid] == nums[mid + 1]))){
                return nums[mid]; // 与左侧或右侧都不相等时 return
            }
            if(mid % 2 == 0){ // mid 是偶数 index 时
                if(mid + 1 < nums.length && nums[mid] == nums[mid + 1]){ // 偶数 index 应和右侧对比，若一致，则前面没问题
                    left = mid + 1;
                }else{
                    right = mid - 1; // 若不一致则前面有问题
                }
            }else{ // mid 是奇数 index 时
                if(mid - 1 >= 0 && nums[mid - 1] == nums[mid]){ // 奇数 index 应和左侧对比，若一致，则前面没问题
                    left = mid + 1;
                }else{
                    right = mid - 1; // 若不一致则前面有问题
                }
            }
        }

        return -1;
    }
}

/**
 * 异或
 * O(n)
 */
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int res = 0; // 0 与任何数亦或都是本身
        for(int num: nums){
            res ^= num;
        }
        return res;
    }
}