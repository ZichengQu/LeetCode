/**
 * O(n), S(1)
 */
// class Solution {
//     public int findPeakElement(int[] nums) {
//         for(int i = 0; i < nums.length - 1; i++){
//             if(nums[i] > nums[i + 1]){
//                 return i;
//             }
//         }
//         return nums.length - 1;
//     }
// }

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