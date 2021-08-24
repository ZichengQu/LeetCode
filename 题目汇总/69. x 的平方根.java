/**
 * 二分
 */
class Solution {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        while(left <= right){
            // long mid = (left + right) / 2; // mid虽然是int，但mid*mid等操作可能超过int范围，因此也可以直接将其设为long
            int mid = (left + right) / 2;
            if((long)mid * mid <= x && ((long)mid + 1) * (mid + 1) > x){ // 因为mid*mid可能超过int范围，因此转成long
                // return (long)mid;
                return mid;
            }else if((long)mid * mid < x){
                // left = (long)mid + 1;
                left = mid + 1;
            }else{
                // right = (long)mid - 1;
                right = mid - 1;
            }
        }
        return 0;
    }
}