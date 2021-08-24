class Solution {
    public int shiftMatrix(int[][] source, int[][] target) {
        /*
         * 大致思路：
         * 每个 source 有 12 种状态转换，将这 12 种状态作为该 source 的邻居
         * 使用 BFS 找到从 source 到状态为 target 的最少变换次数
         * 分析：每个二维数组如何在 BFS 里标志为访问过？Python可以，但Java中如何实现？Hash可以吗？不确定
         * 状态转换：第一行 "传送带" 往左，往右共两种。因此同理, 第一、二、三行，第一、二、三列，共 12 种。
         * 预期：是否会因常数过大？导致超时？
         * 与 COMP9814 Assignment 1 几乎完全一致。grid从一个状态转换到另一个状态的最短距离。
         */
         return 0;
    }

}