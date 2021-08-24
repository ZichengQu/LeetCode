/**
 * COMP9101 Lecture, 7. Dynamic Programming, Page 33
 * O(len1 * len2), S(len1 * len2)
 */
class Solution {
    public int minDistance(String word1, String word2) {
        char[] wordChar1 = word1.toCharArray();
        char[] wordChar2 = word2.toCharArray();

        int len1 = wordChar1.length;
        int len2 = wordChar2.length;

        int[][] dp = new int[len1 + 1][len2 + 1];

        // 逻辑上看起来不如将这一个for循环拆分成3个，3个for循环看起来更直观
        // for(int i = 0; i <= len1 || i <= len2; i++){ // 第0行是0123..., 第一列是012345..，因为通过删除的最小操作数。其余全部是最大值
        //     if(i < len1 + 1){ // 不知道len1和len2谁更大
        //         Arrays.fill(dp[i], Integer.MAX_VALUE);
        //         dp[i][0] = i; // 初始化第0列
        //     }
        //     if(i < len2 + 1){ // 不知道len1和len2谁更大
        //         dp[0][i] = i; // 初始化第0行
        //     }
        // }

        for(int i = 0; i <= len1; i++){ // 除了下面重新规定的初始值，其余全部是最大值
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        // 下面初始化的原理：当len1或len2为0时，其值是通过的最小操作数（纯删除或纯插入）将word1转为word2的
        for(int i = 0; i <= len1; i++){ // 第0列是012345...
            dp[i][0] = i; // 初始化第0列
        }
        for(int i = 0; i <= len2; i++){ // 第0行是0123...
            dp[0][i] = i; // 初始化第0行
        }

        // for(int[] temp: dp){
        //     System.out.println(Arrays.toString(temp));
        // }

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                // the first option corresponds to first transforming A[1..i − 1] to B[1..j − 1] 
                int cost = Integer.MAX_VALUE;
                if(wordChar1[i - 1] == wordChar2[j - 1]){ // 相等。for循环和数组下标相差1，因此wordChar[i - 1]。
                    cost = dp[i - 1][j - 1] + 0; // 在A[0...i-1] == B[0...j-1]的前提下，A[i] 与 B[j]还相等
                }else{ // 不等
                    cost = dp[i - 1][j - 1] + 1; // 在A[0...i-1] == B[0...j-1]的前提下，将A[i]替换为B[j]
                }
                int delete = dp[i - 1][j] + 1; // 加1是代表先删除A[i]，dp[i - 1][j]是将A[0...i-1]转换成B[0...j]
                int insert = dp[i][j - 1] + 1; // dp[i][j - 1]是将A[0...i]转换成B[0...j-1]，加1是代表在最后再插入一个B[j]
                dp[i][j] = Math.min(cost, Math.min(delete, insert));
            }
        }

        // System.out.println();
        // for(int[] temp: dp){
        //     System.out.println(Arrays.toString(temp));
        // }

        return dp[len1][len2];
    }
}