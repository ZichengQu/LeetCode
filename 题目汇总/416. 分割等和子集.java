// 0-1背包问题

/**
 * 自己的方法
 */
class Solution {
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if(len < 2){ // 如果长度不足2，肯定不能分割开
            return false;
        }
        int sum = 0; // 求和
        for(int num: nums){
            sum += num;
        }

        if(sum % 2 != 0){ // 如果和不是偶数，肯定不能平等分割
            return false;
        }

        int haflSum = sum / 2; // 考虑0-1背包问题，一半的和作为背包的容量，不超过此容量的前提下，存储的越多越好。如果最后最多能达到的正好为和的一半，则符合题意平等分割
        int[][] dp = new int[len + 1][haflSum + 1]; // 对于每个子容量，分别考虑是否选择第i个物体，若都不选，物体从0开始。子容量从0开始。行是物体，列是容量。

        for(int i = 1; i <= len; i++){ // 对于每个物体
            for(int j = 1; j <= haflSum; j++){ // 对于每个子容量
                int weight = nums[i - 1];  // nums[i - 1]是因为nums的下标和这里的下标i差1. 不是指上一个物体
                int value = nums[i - 1];
                if(j - weight < 0){ // 如果当前容量，减去重量weight，得到子容量的过程中，子容量小于0了，则子容量非法，不能选择第i个
                    dp[i][j] = dp[i - 1][j];
                }else{
                    int notChoice = dp[i - 1][j]; // 不选择第i个，则其和上一行结果相同
                    int choice = dp[i - 1][j - weight] + value; // 选择第i个，则其相当于没选择i，并且容量正好比j小weight的那个值的基础上，选择了i变成了容量为j，值为dp[i - 1][j - weight] + value;
                    dp[i][j] = Math.max(notChoice, choice); // 选择 or 不选择？哪个大，选哪个
                }
            }
        }
        // System.out.println(dp[len - 1][haflSum - 1]);
        // for(int i = 0; i < len + 1; i++){
        //     System.out.println(Arrays.toString(dp[i]));
        // }
        return dp[len][haflSum] == haflSum;
    }
}

/**
 * 官解
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
 * 可以参考官解视频，说的很详细
 */
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true; // 容量为0时，所有都满足，因为都不选就ok了
        }
        dp[0][nums[0]] = true; // 初始化条件，不重复，因此只有第0行，只有正好相同的那项为true
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                // if(nums[i] == j){ // dp[0][nums[0]] = true; 可替代
                //     dp[i][j] = true;
                //     continue;
                // }
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }
}

/**
 * 官解
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
 * 在计算 dp 的过程中，每一行的 dp 值都只与上一行的 dp 值有关，因此只需要一个一维数组即可将空间复杂度降到 O(target)。
 * 此时的转移方程为：dp[j] = dp[j] | dp[j - nums[i]]
 * 可以参考官解视频，从二维数组到一维数组为什么可以这么映射，说的很详细
 */
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; --j) { // 从后往前，因为后一行的boolean，从后往前，不断用上一行的boolean进行判断。如果从前往后，目前只有一维数组，因此若先更新前面，则无法再更新后面。
                dp[j] |= dp[j - num]; 
            }
        }
        return dp[target];
    }
}