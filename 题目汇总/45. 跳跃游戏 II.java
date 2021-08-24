/**
 * 官解 (贪心算法为主，动态规划为辅)
 */
class Solution {
    public int jump(int[] nums) {
        int steps = 0; // 跳跃次数
        int maxPosition = 0; // 目前能跳到的最远位置
        int end = 0; // 上次跳跃可达范围右边界（下次的最右起跳点）

        // 在遍历数组时，我们不访问最后一个元素，这是因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置，否则就无法跳到最后一个位置了。如果访问最后一个元素，在边界正好为最后一个位置的情况下，我们会增加一次「不必要的跳跃次数」，因此我们不必访问最后一个元素。
        for(int i = 0; i < nums.length - 1; i++){
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if(i == end){ // 到达上次跳跃能到达的右边界了
                end = maxPosition; // 目前能跳到的最远位置变成了下次起跳位置的有边界
                steps++; // 进入下一次跳跃
            }
        }
        return steps;
    }
}

/**
 * 自己的答案 (动态规划思路)
 */
class Solution {
    public int jump(int[] nums) {
        int[] steps = new int[nums.length]; // 跳到第i个位置，所需要的step

        for(int i = 1; i < steps.length; i++){ // 除下标0的元素外 (因为动态规划需要提前求出子解，steps[0] = 0)
            steps[i] = Integer.MAX_VALUE; // 初始化为最大值
        }

        for(int i = 0; i < nums.length; i++){
            int maxStep = nums[i]; // 下标为i时能跳跃的最大距离
            for(int j = 1; j <= maxStep && (i + j) < nums.length; j++){ // 最大距离内，并且steps数组不越界的情况下
                steps[i + j] = Math.min(steps[i + j], 1 + steps[i]); // 哪个小，用哪个。加1是因为从steps[i]到这个steps[i + j]只需要1步跳跃，因为在最大跳跃范围之内。
            }
        }

        return steps[steps.length - 1];
    }
}