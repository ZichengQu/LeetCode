// 完全背包问题

/**
 * 自己的方法，但是没有官解简练，而且官解有个优化的思路很好
 */
class Solution {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);

        int[] dp = new int[amount + 1]; // 假设最初有个amount为0的子解
        dp[0] = 0; // 初始化子解
        for(int i = 1; i <= amount; i++){ // 一直用子解去求当前解，直至最终解
            int count = Integer.MAX_VALUE; // 记录最小的硬币数量
            boolean flag = false; // 是否有组成方案

            for(int j = 0; j < coins.length; j++){
                int coin = coins[j];
                int oldAmount = i - coin;
                if(oldAmount >= 0 && dp[oldAmount] != -1){
                    flag = true;
                    count = Math.min(count, dp[oldAmount]);
                }
            }

            dp[i] = flag? count + 1: -1;
        }
        // System.out.println(Arrays.toString(dp));
        return dp[amount] < 0? -1: dp[amount];
    }
}

/**
 * 官解
 * https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
 * O(S * n)， S(S)，其中 S 是金额 (amount)，n 是面额数 (coints.length)
 */
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max); // 官解的优化之处，最多可能需要amount个，肯定达不到max个
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1); // 一直取小的个数的
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}