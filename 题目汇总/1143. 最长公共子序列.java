/**
 * 官解思路：https://leetcode-cn.com/problems/longest-common-subsequence/solution/zui-chang-gong-gong-zi-xu-lie-by-leetcod-y7u0/
 * O(text1.length() * text2.length())
 */
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0){
            return 0;
        }
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for(int i = 1; i < len1 + 1; i++){
            char ch1 = text1.charAt(i - 1);
            for(int j = 1; j < len2 + 1; j++){
                char ch2 = text2.charAt(j - 1);
                if(ch1 == ch2){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[len1][len2];
    }
}