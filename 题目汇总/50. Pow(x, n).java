/**
 * 官解：https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
 * O(log(n)), S(1)
 */
class Solution {
    public double myPow(double x, int n) {
        double res = 1;

        if(n == 0){
            return res;
        }
        if(n < 0){
            x = 1 / x;
        }
        
        long exp = Math.abs((long)n); // n的范围是整个整型范围，因此整型最小值的abs，会超过整型最大值的上界
        // long exp = Math.abs(Long.valueOf(n));

        while(exp > 0){
            if(exp % 2 != 0){
                res *= x;
            }
            x *= x;
            exp /= 2;
        }

        return res;
    }
}