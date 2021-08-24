class Solution {
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] <= 0){
                nums[i] = nums.length + 1; // 将 <= 0 的都改成 n+1
            }
        }
        int index;
        for(int i = 0; i < nums.length; i++){
            index = Math.abs(nums[i]) - 1;
            if(index < nums.length && nums[index] > 0){ // 将在[1, length-1]的范围内标记为负
                nums[index] *= -1;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                return i + 1; // 第一个值大于0的，则其index未被设置过, 则不存在该整数
            }
        }
        return nums.length + 1;
    }
}