/**
 * 198. 动态规划，O(n), S(n), 常规方法
 * 递推公式：dp[i]=max(dp[i−2]+nums[i], dp[i−1])
 *          dp[0] = nums[0]
 *          dp[1] = Math.max(dp[0], nums[1])
 */
// class Solution {
//     public int rob(int[] nums) {
//         if(nums == null || nums.length == 0){
//             return 0;
//         }else if(nums.length == 1){
//             return nums[0];
//         }

//         int size = nums.length;

//         int[] states = new int[size];
//         states[0] = nums[0];
//         states[1] = Math.max(states[0], nums[1]);

//         for(int i = 2; i < size; i++){
//             states[i] = Math.max(states[i - 2] + nums[i], states[i - 1]);
//         }
//         return states[size - 1];
//     }
// }

/**
 * 198. 动态规划，O(n), S(n), 使用滚动数组来降低空间复杂度
 * 递推公式：dp[i]=max(dp[i−2]+nums[i], dp[i−1])
 *          dp[0] = nums[0]
 *          dp[1] = Math.max(dp[0], nums[1])
 */
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }else if(nums.length == 1){
            return nums[0];
        }

        int firstState = nums[0];
        int secondState = Math.max(firstState, nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int temp = secondState; // 重新往前更新，first，second，和new变为新的first(second), second(new)
            secondState = Math.max(firstState + nums[i], secondState);
            firstState = temp;
        }

        return secondState;
    }
}