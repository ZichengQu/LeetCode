/**
 * 198. 动态规划，O(n), S(n), 常规方法
 * 递推公式：dp[i]=max(dp[i−2]+nums[i], dp[i−1])
 *          dp[0] = nums[0]
 *          dp[1] = Math.max(dp[0], nums[1])
 */
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }else if(nums.length == 1){
            return nums[0];
        }

        int size = nums.length;

        int[] states = new int[size]; // 第 i 个的最大盗窃金额
        states[0] = nums[0]; // 第 0 个肯定拿比不拿大
        states[1] = Math.max(states[0], 0 + nums[1]); // 第 1 个的最大是要么拿第0个，要么不拿第0个，去拿第1个。

        for(int i = 2; i < size; i++){
            states[i] = Math.max(states[i - 2] + nums[i], states[i - 1]); // 不拿上一个，拿大上一个的情况下，拿这个。拿上一个，不要这个
        }
        return states[size - 1];
    }
}

/**
 * 198. 动态规划，O(n), S(n), 使用滚动数组来降低空间复杂度
 * 递推公式：dp[i]=max(dp[i−2]+nums[i], dp[i−1])
 *          dp[0] = nums[0]
 *          dp[1] = Math.max(dp[0], nums[1])
 */
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }else if(nums.length == 1){
            return nums[0];
        }

        int firstState = nums[0];
        int secondState = Math.max(firstState, nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int temp = secondState; // 重新往前更新，first，second，和new变为新的first(second), second(new)
            secondState = Math.max(firstState + nums[i], secondState);
            firstState = temp;
        }

        return secondState;
    }
}

/**
 * 自己的思路
 * O(2 * n), S(2 * n)
 */
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;

        int[][] dp = new int[len][2];

        dp[0][0] = 0; // 第0间房屋不偷
        dp[0][1] = nums[0]; // 第0间房屋偷

        for(int i = 1; i < len; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]); // 第i间房屋不偷
            int twoDayAgo = 0;
            if(i - 2 >= 0){
                twoDayAgo = Math.max(dp[i - 2][0], dp[i - 2][1]);
            }
            dp[i][1] = Math.max(twoDayAgo, dp[i - 1][0]) + nums[i]; // 第i间房屋偷
        }
        // for(int i = 0; i < len; i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        return Math.max(dp[len - 1][0], dp[len - 1][1]); // 到最后一间时，判断偷与不偷谁大。

    }
}