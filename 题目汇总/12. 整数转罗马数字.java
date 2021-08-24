/**
 * 由于传入的参数num在题中给出明确范围，因此其长度最大为4位数
 * 因此，时间复杂度为 O(1), 空间复杂度为 O(1)
 */
class Solution {
    public String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuffer romanNum = new StringBuffer();
        for(int i = 0; i < nums.length; i++){
            if(num >= nums[i]){
                romanNum.append(romans[i]);
                num -= nums[i];
                i--;
            }
            if (num == 0) { // 与官解的区别，官解的这里可以提高性能优化
                break;
            }
        }
        return romanNum.toString();

    }
}