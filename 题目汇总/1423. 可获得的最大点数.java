/**
 * 滑动窗口
 * 记数组 cardPoints 的长度为 len，由于只能从开头和末尾拿 k 张卡牌，所以最后剩下的必然是连续的 len - k 张卡牌。
 * 我们可以通过求出剩余卡牌点数之和的最小值，来求出拿走卡牌点数之和的最大值。
 * O(n), S(1)
 */
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;

        int windowSize = len - k; // 滑动窗口大小

        int sum = 0;

        // 选前 windowSize 个作为滑动窗口的初始值
        for(int i = 0; i < windowSize; i++){
            sum += cardPoints[i];
        }

        int windowSum = sum; // 当前滑动窗口的和
        int minWindowSum = windowSum; // 所以滑动窗口里最小的和

        for(int i = windowSize; i < len; i++){
            windowSum = windowSum + cardPoints[i] - cardPoints[i - windowSize]; // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            if(windowSum < minWindowSum){
                minWindowSum = windowSum; // 更新最小值
            }
            sum += cardPoints[i]; // 一直求该数组的和
        }

        return sum - minWindowSum; // 总和 - 某一长度为 windowSize 的连续片段的最小值 => 两边的最大值之和
    }
}

/**
 * DFS做法
 * 这道题会超时 O(2^k)
 */
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;

        int max = dfs(cardPoints, k, 0, 0, 0, len - 1);

        return max;
    }

    private int dfs(int[] cardPoints, int k, int depth, int sum, int leftIndex, int rightIndex){
        if(depth == k){
            return sum;
        }
        int left = dfs(cardPoints, k, depth + 1, sum + cardPoints[leftIndex], leftIndex + 1, rightIndex); // 先选左侧，在参数里改变参数值，相当于回溯了
        int right = dfs(cardPoints, k, depth + 1, sum + cardPoints[rightIndex], leftIndex, rightIndex - 1); // 先选右侧

        return Math.max(left, right); // 哪个大，就按哪个方案来
    }
}