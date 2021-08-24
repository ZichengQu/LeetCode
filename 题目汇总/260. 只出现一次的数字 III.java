/**
 * 位运算，异或
 */
class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0; // 0和任意数异或为任意数本身。因此初始化xor为0。
        for(int num: nums){
            xor ^= num; // 先求出所有数的异或，最终 xor 是两个 single number 的异或
        }
        int lowBit = 1; // 找到xor中最低位为1的，因为xor的某一位为1，代表这两个数的这一位不同，否则相同。其实找到任意一个为1的都可以，只是这个找最低位。
        while((lowBit & xor) == 0){
            lowBit <<= 1;
        }
        int a = 0; // 初始化两个数为0，0和任意数异或为任意数本身。因此初始化为0
        int b = 0;
        for(int num: nums){
            if((lowBit & num) != 0){ // 对任意数，要么lowBit为1，要么为0.按照a和b，将所有数分为两组
                a ^= num; // 分成两组之后，除了a，其余进入该组的数，必定出现两次
            }else{
                b ^= num; // 同理
            }
        }
        return new int[]{a, b};
    }
}