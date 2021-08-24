/**
 * 动态规划算法
 * O(m * n), S(m * n)
 */
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        Arrays.fill(dp[0], 1);
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                // 不用判断是否会越界，因为第一行和第一列都赋值了，一定 不会越界
                // int top = 0;
                // int left = 0;
                // if(i - 1 >= 0){
                //     top = dp[i - 1][j];
                // }
                // if(j - 1 >= 0){
                //     left = dp[i][j - 1];
                // }
                int top = dp[i - 1][j];
                int left = dp[i][j - 1];
                dp[i][j] = top + left;
            }
        }
        return dp[m - 1][n - 1];
    }
}

/**
 * 数学方法 (未整理)
 * O(m), S(1)
 * 参考官解链接：https://leetcode-cn.com/problems/unique-paths/solution/bu-tong-lu-jing-by-leetcode-solution-hzjf/
 */