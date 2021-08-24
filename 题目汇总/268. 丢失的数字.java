/**
 * 位运算，异或
 * 原理：因为异或满足交换律和结合律
 * O(n), S(1)
 */
class Solution {
    public int missingNumber(int[] nums) {
        int missing = 0; // 初始设为0，因为任何数和0异或都为本身。
        int len = nums.length;
        for(int i = 0; i <= len; i++){
            missing ^= i; // 把所有的[0, n]都异或起来
        }
        for(int num: nums){
            missing ^= num; // 把每个出现的数再参与异或，因为一个数和本身异或，其值是0.
        }
        return missing;
    }
}

/**
 * 数学方式
 * O(n), S(1)
 */
class Solution {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int expectedSum = (0 + len) * (1 + len) / 2; // 如果不缺值，则其sum应为 (首项 + 尾项) * 项数 / 2
        for(int num: nums){
            expectedSum -= num; // 减去每个出现过的
        }
        return expectedSum; // 剩下的那个就是未出现的
    }
}

