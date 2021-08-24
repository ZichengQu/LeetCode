/**
 * 动态规划
 * O(m * n), S(m * n)
 */
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        // 初始状态
        dp[0][0] = grid[0][0]; // 左上角（起点）
        for(int i = 1; i < m; i++){ // 第一列
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for(int j = 1; j < n; j++){ // 第一行
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        // 其实上面这三个for循环可以合并在一起，具体参考力扣 120 题 （已整理）

        // for(int i = 0; i < m; i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        return dp[m - 1][n - 1];
    }
}