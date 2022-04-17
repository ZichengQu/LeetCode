// 贪心
class Solution {
    public int wiggleMaxLength(int[] nums) {
        // 1. 长度为1的都是摆动序列
        if(nums.length <= 1){
            return nums.length;
        }
        // 2. 初始化
        int preDiff = nums[1] - nums[0];
        int cnt = preDiff == 0? 1: 2; // 前两个元素是否有重复

        for(int i = 2; i < nums.length; i++){
            int curDiff = nums[i] - nums[i - 1];
            if((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)){ // 判断当前序列的上升下降趋势
                cnt++; // 如果出现了「峰」或「谷」，答案加一
                preDiff = curDiff; // 更新当前序列的上升下降趋势
            }
        }

        return cnt;
    }
}

// 动态规划
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length <= 1){
            return nums.length;
        }

        int len = nums.length;

        int[] up = new int[len]; // 到位置 i 时，最后是上升的最大摆动长度
        int[] down = new int[len]; // 到位置 i 时，最后是下降的最大摆动长度
        up[0] = 1; // i = 0 时，一定存在一个元素
        down[0] = 1;

        for(int i = 1; i < len; i++){
            int diff = nums[i] - nums[i - 1];
            if(diff > 0){ // 如果这次上升
                up[i] = Math.max(up[i - 1], down[i - 1] + 1); // max(继承上次的上升，通过上次的下降加 1)
                down[i] = down[i - 1]; // 最后不是下降，因此 down 不变
            }else if(diff < 0){ // 如果这次下降
                down[i] = Math.max(down[i - 1], up[i - 1] + 1);
                up[i] = up[i - 1];
            }else{ // 持平
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }

        return Math.max(up[len - 1], down[len - 1]);
    }
}