class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1= s1.length();
        int len2= s2.length();
        int len3= s3.length();

        if(len1 + len2 != len3){
            return false;
        }

        // dp[i][j]：s1前缀长度i + s2前缀长度j，能否交错组成s3前缀长度i+j
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;

        // 第0列：s1前缀长度i + s2前缀长度0，能否交错组成s3前缀长度i
        for(int i = 1; i <= len1; i++){ // 之所以 i <= len1 是因为 i = 1 的时候对应第一个字符
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

         // 第0行：s1前缀长度0 + s2前缀长度j，能否交错组成s3前缀长度j
        for(int j = 1; j <= len2; j++){
            dp[0][j] = dp[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }

        // 普通位置 dp[i][j]：s1前缀长度i + s2前缀长度j，能否交错组成s3前缀长度i+j
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                // 考虑s3的最后一个字符s3[i+j-1]来自哪里？做状态转移：
                // 1）如果来自s1[i-1]，则dp[i][j] 为：s1前缀长度i-1 + s2前缀长度j 能否交错组成s3前缀长度i+j-1，即：dp[i-1][j]
                // 2）如果来自s2[j-1]，则dp[i][j] 为：s1前缀长度i + s2前缀长度j-1 能否交错组成s3前缀长度i+j-1，即：dp[i][j-1]
                boolean flag1 = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                boolean flag2 = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                dp[i][j] = flag1 || flag2;
            }
        }

        // for(boolean[] temp: dp){
        //     System.out.println(Arrays.toString(temp));
        // }
        
        return dp[len1][len2];
    }
}

// 也可以采用滚动数组进行空间优化