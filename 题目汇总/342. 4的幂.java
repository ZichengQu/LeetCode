class Solution {
    public boolean isPowerOfFour(int n) {
        return (n > 0) && ((n & (n - 1)) == 0) && (n % 3 == 1);
        // int mask = 0b10101010101010101010101010101010;
        // mask = 0xaaaaaaaa;
        // return (n > 0) && ((n & (n - 1)) == 0) && ((n & mask) == 0);
    }
}