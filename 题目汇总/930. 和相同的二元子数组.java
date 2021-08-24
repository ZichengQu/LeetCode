// 官解链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum/solution/he-xiang-tong-de-er-yuan-zi-shu-zu-by-le-5caf/

/**
 * O(n), S(n)
 */
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0;
        int res = 0;
        Map<Integer, Integer> hashMap = new HashMap<>(); // sum, occurrence
        for(int num: nums){
            // goal 一定等于 sum[j] - sum[i], from (i, j]
            hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1); // 先将旧的sum和其occurrence放到map里
            sum += num; // 再更新sum至最新
            res += hashMap.getOrDefault(sum - goal, 0); // 查看旧sum (sum - goal)的occurrence次数
        }
        return res;
    }
}

/**
 * 滑动窗口
 * O(n), S(1)
 */
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int ret = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) { // ＞的这个while执行完是使sum恰好等于goal的第一个下标
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) { // ≥这个while执行完是使sum恰好小于goal的第一个下标
                sum2 -= nums[left2];
                left2++;
            }
            ret += left2 - left1; // 所以left2-left1是当前r指针对应的滑窗范围，如果用前缀和数组做的话，left1和left2应该分别对应lowerbound和upperbound
            right++;
        }
        return ret;
    }
}