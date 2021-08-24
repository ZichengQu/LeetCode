/**
 * 213. 动态规划，O(n), S(1), 滚动数组的方法
 * 递推公式：dp[i]=max(dp[i−2]+nums[i], dp[i−1])
 *          dp[0] = nums[0]
 *          dp[1] = Math.max(dp[0], nums[1])
 * 由于第一间和最后一间也相连，因此将原nums分割为两个array，一个为nums[0:-2]，一个为nums[1:-1]
 * 分别求出这两个array的最高金额(方法完全照搬 "198. 动态规划")
 */
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        if(len == 1){
            return nums[0];
        }else if(len == 2){
            return Math.max(nums[0], nums[1]);
        }

        int maxFirst = rob(nums, 0, len - 1); // 去掉最后一个num，这样就可以按照 "198. 打家劫舍"的思路做了
        int maxLast = rob(nums, 1, len - 1); // 去掉第一个num

        maxFirst = robRange(nums, 0); // 去掉最后一个num，这样就可以按照 "198. 打家劫舍"的思路做了
        maxLast = robRange(nums, 1); // 去掉第一个num

        return Math.max(maxFirst, maxLast);

    }

    /*
     * 常规动态规划方式
     * nums.length >= 3, 才会执行到此方法
     */
    private int rob(int[] nums, int left, int len){
        int[] dp = new int[len];
        dp[0] = nums[left];
        dp[1] = Math.max(dp[0], 0 + nums[left + 1]);

        for(int i = 2; i < len; i++){
            dp[i] = Math.max(dp[i - 2] + nums[left + i], dp[i - 1]);
        }
        return dp[len - 1];
    }

    /*
     * 滚动数组节约空间
     * nums.length >= 3, 才会执行到此方法
     */
    private int robRange(int[] nums, int start){
        // 假设start为0，则舍去最后一位。若start为1，则取全长，但因为start而舍掉第一位
        int end = start == 0? (nums.length - 1): nums.length;
        int first = nums[start];
        int second = Math.max(first, nums[start + 1]);
        for(int i = start + 2; i < end; i++){
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

}