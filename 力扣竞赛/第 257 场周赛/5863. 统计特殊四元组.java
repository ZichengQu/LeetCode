// https://leetcode-cn.com/problems/count-special-quadruplets/

class Solution {
    public int countQuadruplets(int[] nums) {
        int cnt = 0;
        
        int len = nums.length;
        for(int i = 0; i < len; i++){
            int num1 = nums[i];
            for(int j = i + 1; j < len; j++){
                int num2 = nums[j];
                for(int k = j + 1; k < len; k++){
                    int num3 = nums[k];
                    for(int m = k + 1; m < len; m++){
                        int num4 = nums[m];
                        if(num1 + num2 + num3 == num4){
                            cnt++;
                        }
                    }
                }
            }
        }
        
        return cnt;
    }
}