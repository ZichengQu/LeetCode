// class Solution {
//     public int maximumProduct(int[] nums) {
//         Arrays.sort(nums);
//         int num1 = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
//         int num2 = nums[nums.length - 1] * nums[0] * nums[1];
//         return num1 > num2? num1: num2;
//     }
// }

class Solution {
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE; // min1 最小
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE; // max1 最大
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for(int value: nums){
            if(value < min1){
                min2 = min1;
                min1 = value;
            }else if(value < min2){
                min2 = value;
            }
            if(value > max1){
                max3 = max2;
                max2 = max1;
                max1 = value;
            }else if(value > max2){
                max3 = max2;
                max2 = value;
            }else if(value > max3){
                max3 = value;
            }
        }
        int num1 = max1 * max2 * max3;
        int num2 = max1 * min1 * min2;
        return num1 > num2? num1: num2;
    }
}