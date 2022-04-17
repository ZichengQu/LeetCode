/**
 * 贪心思想
 * O(n), S(1)
 */
class Solution {
    public int balancedStringSplit(String s) {
        int len = s.length();

        int cnt = 0;

        // int L = 0;
        // int R = 0;
        int d = 0;

        for(int i = 0; i < len; i++){
            if(s.charAt(i) == 'L'){
                // L++;
                d++;
            }else{
                // R++;
                d--;
            }
            // if(L == R){
            //     cnt++;
            //     L = 0;
            //     R = 0;
            // }
            if(d == 0){
                cnt++;
            }
        }

        return cnt;
    }
}