/**
 * 动态规划：使用普通数组
 * O(n^2), S(n * 2 * maxDiffSituations)
 */
class Solution {
    public int longestArithSeqLength(int[] nums) {
        int res = Integer.MIN_VALUE; // 返回值

        int len = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int num: nums){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        int minDifference = 2; // 只有当两个数时，才存在等差，因此等差数列的最小长度为2.
        int maxDifference = max - min; // 最大与最小元素的差值，代表最大差值
        int maxDiffSituations = maxDifference + 1; // 加一是因为存在等差为0的。等差数列的总可能情况的个数。
        
        int[][] dp = new int[len][2 * maxDiffSituations]; // 判断到第 i 个数时，其差值为 j 的等差数列的元素个数。乘2的解释在后面
        
        for(int i = 1; i < len; i++){ // 每个数与它前面所有的数，进行等差个数的判断
            for(int j = 0; j < i; j++){
                int difference = nums[i] - nums[j];
                if(difference < 0){
                    difference = Math.abs(difference) + maxDiffSituations; // 因为差若为复数，则数组无法表示，因此用等差的最大可能的数加上abs(difference)来代表等差为负的情况。因此在声明dp数组时要乘以2.
                }
                dp[i][difference] = Math.max(minDifference, dp[j][difference] + 1);
                res = Math.max(res, dp[i][difference]);
            }
        }
        return res;
    }
}

/**
 * 动态规划，使用Map数组，避免等差的差值为负数用数组表示的问题。
 * O(n^2)
 * S(n^2): 每个数都有一个map，每个map存放这个数与其之前的所有数的差值和个数的key-value对。
 * 这个题目真的坑爹，这个等差是可以有间隔的，并不是连续等差数组
 * [9,4,7,2,10] -> [4,7,10] 是等差，所以长度为 3
 */
class Solution {
    public int longestArithSeqLength(int[] nums) {
        int res = 2; // 两个数才能构成的等差,所以结果值初始化为 2

        int minDifference = 2; // 只有当两个数时，才存在等差，因此等差数列的最小长度为2.

        int len = nums.length;
        
        Map<Integer, Integer>[] dp = new HashMap[len]; // dp[i] => map.key = difference, map.value = count

        for(int i = 0; i < len; i++){ // 每个数与它前面所有的数，进行等差个数的判断
            dp[i] = new HashMap<Integer, Integer>(); // 用来存储所有的 i 和 j 的差的个数
            for(int j = 0; j < i; j++){
                int difference = nums[i] - nums[j]; // i 与 j 的差
                if(dp[j].containsKey(difference)){ // 如果 j 中也存在该等差，(同时j 与 i 也是这个等差)
                    dp[i].put(difference, dp[j].get(difference) + 1); // 则对于第 i 个数的等差为 difference 时的等差数列的个数是 j 对应的值，再把最新的 i 加上，因此是加1。
                }else{
                    dp[i].put(difference, minDifference); // 如果不存在的话，只是 nums[i] 与 nums[j] 构成的等差数列，其长度为2
                }
                res = Math.max(res, dp[i].get(difference)); // 一直保存最大的等差数列的个数
            }
        }
        
        return res;
    }
}