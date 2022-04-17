/**
 * 前缀和
 * O(n + m), S(n)
 * n 为要求的数组长度，m 为预定记录的数量
 */
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] incease = new int[n]; // 差分数组

        for(int[] booking: bookings){ // O(n)
            // 对原数组的某一个区间 [l,r] 施加一个增量 inc 时，差分数组 incease 对应的改变是：incease[l] 增加 inc，incease[r + 1] 减少 inc。
            int lower = booking[0] - 1; // incease[l]; 减 1 是因为航班从 1 开始，数组从 0 开始
            int higher = booking[1]; // 不减 1 是因为 incease[r + 1]
            int inc = booking[2]; // 增量部分
            incease[lower] += inc;
            if(higher < n){
                incease[higher] -= inc;
            }
        }

        for(int i = 1; i < incease.length; i++){ // O(m)
            incease[i] += incease[i - 1]; // 用每一项与前一项的差，求该项的值
        }

        return incease;
    }
}

/**
 * 暴力解
 * O(n * m), S(n)
 * n 为要求的数组长度，m 为预定记录的数量
 */
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int largest = Integer.MIN_VALUE;

        for(int[] booking: bookings){
            largest = Math.max(largest, booking[1]);
        }

        int[] total = new int[n];

        for(int[] booking: bookings){
            int small = booking[0] - 1;
            int large = booking[1] - 1;
            int count = booking[2];
            for(int i = small; i <= large; i++){
                total[i] += count;
            }
        }

        return total;
    }
}