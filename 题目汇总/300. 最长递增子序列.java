/**
 * 动态规划
 * O(n^2), S(n)
 */
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; // 结尾是 index 为 i 时的最长的递增子序列
        Arrays.fill(dp, 1); // 哪怕只有本身，因此最少是1

        int max = 1;

        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]); // 作用相当于是在最后再遍历了一遍dp数组，并取最大的
        }

        // System.out.println(Arrays.toString(dp));
        
        return max;

    }
}

/**
 * O(n * log(n)) 贪心思想未整理
 */