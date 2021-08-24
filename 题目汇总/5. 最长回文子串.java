/**
 * 暴力解法
 * O(n^3)
 */
class Solution {
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        for(int i = 0; i < s.length() - 1; i++){
            for(int j = 1; j < s.length(); j++){
                int left = i;
                int right = j;
                boolean flag = true;
                while(left <= right){
                    if(s.charAt(left) != s.charAt(right)){
                        flag = false;
                        break;
                    }
                    left++;
                    right--;
                }
                if(flag && (j - i) > (end - start)){
                    end = j;
                    start = i;
                }
                
            }
        }
        return s.substring(start, end + 1);
    }
}

/**
 * 动态规划
 * O(n^2)
 */
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();

        if(len <= 1){ // 其实不加这个判断也可以，但力扣的运行时间会变长一些。
            return s;
        }

        boolean[][] dp = new boolean[len][len]; // 动态规划，从某个下标到某个下标是否为回文串，是则为true，否则为false

        for(int i = 0; i < len; i++){
            dp[i][i] = true; // 单个字母的地方都为true
        }

        int begin = 0; // 记录最大长度的开始位置
        int maxLen = 1; // 记录最大长度

        // dp(i, j)=dp(i + 1,j − 1) ∧ (Si ​== Sj​)
        // 因为若小字符子串是回文，则该小子串两边加上相同的字母，也是回文。
        for(int L = 2; L <= len; L++){ // 一定要根据长度从小到大遍历，不能通过两个for循环遍历左右边界。因为判断大字串是否回文是通过小字串来判断的，因此要先讲小子穿先求出，并设为正确的true或false
            for(int start = 0; start < len; start++){ // 左边界起始位置
                int end = start + L - 1; // 右边界
                if(end >= len){
                    break;
                }
                if(s.charAt(start) != s.charAt(end)){
                    dp[start][end] = false;
                }else{
                    if(L <= 2){ 
                        // 其实把这里改成2或3都行。
                        // 2的原因：长度为2，且这两个字符相等，则这两个字符一定满足回文的条件。最
                        // 3的原因：若长度为3，则去掉首尾字符，则只有一个中间字符，则其中间字符一定为true，相当于用else里的条件也可以判断。
                        dp[start][end] = true;
                    }else{
                        dp[start][end] = dp[start + 1][end - 1]; // 至少长度为3的 L 才进来，因为 L = 2时，其前面字符下标+1，后面字符下标-1，无法构成有效字符子串。
                    }
                }
                if(dp[start][end] && L > maxLen){ // 如果这个dp为true了，判断是否更新最大子串长度。
                    begin = start;
                    maxLen = L;
                }
            }
        }

        return s.substring(begin, begin + maxLen); // [begin, begin + maxLen) ==> [begin, begin + maxLen - 1]
    }
}

/**
 * 错误示范，不能直接遍历其左右边界
 */
// class Solution {
//     public String longestPalindrome(String s) {
//         int len = s.length();
//         boolean[][] dp = new boolean[len][len]; // 从第i个下标到第j个下标是否为回文子串
//         for(int i = 0; i < len; i++){
//             dp[i][i] = true;
//         }

//         int begin = 0;
//         int longest = 0;

//         for(int i = 0; i < len; i++){
//             for(int j = i + 1; j < len; j++){
//                 if(j - i == 1 || j - i == 2){
//                     if(s.charAt(i) == s.charAt(j)){
//                         dp[i][j] = true;
//                         if(j - i > longest){
//                             begin = i;
//                             longest = j - i;
//                         }
//                     }else{
//                         dp[i][j] = false;
//                     }
//                 }else{
//                     if(!(dp[i + 1][j - 1] || dp[i][j - 1]) || s.charAt(i) != s.charAt(j)){
//                         dp[i][j] = false;
//                     }else{
//                         dp[i][j] = true;
//                         if(j - i > longest){
//                             begin = i;
//                             longest = j - i;
//                         }
//                     }
//                 }
//             }
//         }

//         return s.substring(begin, begin + longest + 1);
//     }
// }

