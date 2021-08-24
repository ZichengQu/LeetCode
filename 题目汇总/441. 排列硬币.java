class Solution {
    public int arrangeCoins(int n) {
        // 方法一：
        // int i = 1;

        // while(n >= i){
        //     n -= i;
        //     i++;
        // }

        // return i - 1;

        // 方法二：二分
        int left = 1;
        int right = Math.min(n, 66000); // 当 n 为整形最大值时，最多可以66000层。也可以设 right 为 n，但是要注意其余变量的类型，全部需要改为 long。
        while(left <= right){
            int mid = (left + right) / 2;
            long total = (long)(1 + mid) * mid / 2;
            if(total > n){ // 若要找 max(num < target), 则 total >= n; 若要找 max(num <= target), 则 total > n
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return right;

        // 方法三：数学
        // https://leetcode-cn.com/problems/arranging-coins/solution/shu-xue-gui-lu-hua-tu-by-zhouzihong-2ikx/
    }
}