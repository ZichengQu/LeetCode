class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            max = Math.max(max, sum);
            if(sum < 0){
                sum = 0; // 当当前子序列和小于0时，重置该子序列，因为若子序列和小于0，用这个子序列去加后面的元素，还不如重新开始计算一个子序列和。
            }
        }
        return max;
    }
}