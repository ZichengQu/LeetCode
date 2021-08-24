/**
 * 非官解，但也是高vote思路。
 * 这两个解法，逻辑思想一致，实现方式不同。
 */
class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; i--){
            digits[i]++;
            digits[i] %= 10;
            if(digits[i] != 0){ // 如果有一项不是0，则说明它之前不是9。
                return digits;
            }
        }
        digits = new int[digits.length + 1]; // 如果都是0，则意味着原数组全部是9，因此进位为1，其余位为0.
        digits[0] = 1;
        return digits;
    }
}

class Solution {
    public int[] plusOne(int[] digits) {
        boolean allNine = true;
        for(int num: digits){ // 判断是否都是9
            if(num != 9){
                allNine = false;
                break;
            }
        }
        if(allNine){ // 如果都是9，则意味着新增一个最高位，为1，其余位为0.
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }else{
            for(int i = digits.length - 1; i >= 0; i--){
                if(digits[i] == 9){ // 遇到9就变0
                    digits[i] = 0;
                }else{
                    digits[i]++; // 不是9，就直接加1。因为不是9了，所以不会继续影响前面的进位，因此可以在此break。
                    break;
                }
            }
        }
        
        return digits;
    }
}