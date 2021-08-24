class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; // dp[i]时，最大的组合数
        dp[0] = 1; // amount为0时，都不选，因此一种组合

        // 解释一下为什么外层要对coins循环：
        //     假设coins = {1, 2, 3}，amount = 5。 凑出5的方案有三类：
        //         组合必须以硬币1结尾，且不能包含硬币1之后的其他硬币2， 3。假设这类方案数量为x1。
        //         组合必须以硬币2结尾，且不能包含硬币2之后的其他硬币3。假设这类方案数量为x2。
        //         组合必须以硬币3结尾。假设这类方案数量为x3。
        // 若按完全背包思想，用内层对 coins 循环，会产生重复解。
        for(int coin: coins){ // 需要保证硬币顺序，否则可能重复，与完全背包思路不一致
            for(int i = 1; i <= amount; i++){
                if(i - coin >= 0){
                    dp[i] += dp[i - coin];
                }
            }
        }

        // 按完全背包的错误思路，会产生重复解，比如 23和32 等
        // for(int i = 1; i <= amount; i++){
        //     for(int coin: coins){
        //         if(i - coin >= 0){
        //             dp[i] += dp[i - coin];
        //         }
        //     }
        // }

        System.out.println(Arrays.toString(dp));

        return dp[amount];
    }
}