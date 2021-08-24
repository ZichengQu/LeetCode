/**
 * 自己的思路
 * O(maxRow * maxCol), S(maxRow * maxCol)
 */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int maxRow = triangle.size(); // 最大行数
        int maxCol = triangle.get(triangle.size() - 1).size(); // 最大列数

        int[][] dp = new int[maxRow][maxCol];

        dp[0][0] = triangle.get(0).get(0);
        for(int i = 1; i < maxRow; i++){
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0); // 每行的第一个

            int curCol = triangle.get(i).size(); // 其实这里可以优化，第1行，最多只有1个元素；第2行，最多2个元素，依次递推，不需要求每行的列数
            dp[i][curCol - 1] = dp[i - 1][curCol - 2] + triangle.get(i).get(curCol - 1); // 每行的最后一个
        }
        
        // for(int i = 0; i < maxRow; i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }

        for(int i = 2; i < maxRow; i++){
            int curCol = triangle.get(i).size();
            for(int j = 1; j < curCol - 1; j++){
                dp[i][j] = Math.min(dp[i - 1][j -  1], dp[i - 1][j]) + triangle.get(i).get(j); // 从上面两个邻居中选择较小的，本位是必须加的
            }
        }

        int minResult = Integer.MAX_VALUE;
        for(int j = 0; j < maxCol; j++){
            minResult = Math.min(minResult, dp[maxRow - 1][j]); // 最后一行的最小值
        }

        return minResult;
    }
}

/**
 * 官解思路 (完全没改写)
 * O(maxRow * maxCol), S(maxRow * maxCol)
 */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0); // 每行的第一个
            for (int j = 1; j < i; ++j) { // 中间部分
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j); // 从上面两个邻居中选择较小的，本位是必须加的
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i); // 每行的最后一个
        }
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]); // 最后一行的最小值
        }
        return minTotal;
    }
}

/**
 * 按照官解思路改写
 * 利用滚动数组优化空间
 * O(maxRow * maxCol), S(maxCol)
 */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int maxRow = triangle.size();
        int maxCol = triangle.get(triangle.size() - 1).size(); // 其实没必要求这个，这道题中，列数一直是等于行数的。只是为了下面表现更直观

        int[][] dp = new int[2][maxCol]; // 滚动数组

        dp[0][0] = triangle.get(0).get(0);

        for(int i = 1; i < maxRow; i++){
            int cur = i % 2; // 滚动数组中的当前行数
            int prev = (i - 1) % 2; // 滚动数组中的上一行的行数

            dp[cur][0] = dp[prev][0] + triangle.get(i).get(0); // 每行的第一个
            dp[cur][i] = dp[prev][i - 1] + triangle.get(i).get(i); // 每行的最后一个
            for(int j = 1; j < i; j++){ // 中间部分
                dp[cur][j] = Math.min(dp[prev][j - 1], dp[prev][j]) + triangle.get(i).get(j); // 从上面两个邻居中选择较小的，本位是必须加的
            }
        }

        // for(int i = 0; i < 2; i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }

        int cur = (maxRow - 1) % 2; // 减 1 是因为最后一行的下标是 (maxRow - 1)
        int minResult = dp[cur][0];

        for(int j = 1; j < maxCol; j++){
            minResult = Math.min(minResult, dp[cur][j]); // "最后一行"的最小值
        }

        return minResult;
    }
}
