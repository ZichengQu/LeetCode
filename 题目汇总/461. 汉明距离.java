class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        // 方法一：O(loc(C)), S(1)
        return Integer.bitCount(xor);

        /*
         * 方法二:
         * O(log(C)), 位右移，相当于每次除2. C最大为 2^31 - 1
         * S(1)
         */
        int bitCount = 0;
        while(xor != 0){
            bitCount += xor & 1; // 若最低位为1，则bitCount + 1，否则加0
            // xor = xor >> 1; // 位右移
            xor >>= 1; // 简写
        }
        return bitCount;

        /*
         * 方法三: 
         * Brian Kernighan 算法。可以跳过两个 1 之间的 0，直接对 1 进行计数
         * O(log(C)), C最大为 2^31 - 1
         * S(1)
         */
         int bitCount = 0;
         while(xor != 0){
             // 假设 xor = 10001000，则 xor - 1 = 10000111. 因此 xor & (xor - 1) = 10000000. 忽略所有0，每循环一次，将1的个数减少一个.
            xor &= xor - 1;
            bitCount++;
         }
         return bitCount;
    }
}