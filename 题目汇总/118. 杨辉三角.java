/**
 * 自己的思路，没用官解直观
 */
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(new ArrayList<Integer>());
        dp.get(0).add(1); // dp[0][0] = 1
        for (int i = 1; i < numRows; i++) {
            dp.add(new ArrayList<Integer>());
            for (int j = 0; j <= i; j++) {
                if (j - 1 < 0 && j < dp.get(i - 1).size()) { // 每一层的左边界
                    dp.get(i).add(0 + dp.get(i - 1).get(j)); // dp[i][j] = 0 + dp[i - 1][j]
                } else if (j - 1 >= 0 && j >= dp.get(i - 1).size()) { // 每一层的右边界
                    dp.get(i).add(dp.get(i - 1).get(j - 1) + 0); // dp[i][j] = dp[i - 1][j - 1] + 0
                } else { // 每一层的中间
                    dp.get(i).add(dp.get(i - 1).get(j - 1) + dp.get(i - 1).get(j)); // dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
                }
            }
        }
        return dp;
    }
}

/**
 * 官解思路
 */
class Solution {
    public List<List<Integer>> generate(int numRows) {
        int[][] dp = new int[numRows][numRows];
        dp[0][0] = 1;
        for (int i = 1; i < numRows; i++) {
            dp[i][0] = dp[i][i] = 1;
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> buf = new ArrayList<>();
            for (int j = 0; j < i + 1; j++)
                buf.add(dp[i][j]);
            ans.add(buf);
        }
        return ans;
    }
}
