/**
 * 没有官方题解 未整理完全部思路，目前这两个代码不是最优的。
 */

class Solution {
    public String[] findRelativeRanks(int[] score) {
        int len = score.length;
        int[] sortedScore = Arrays.copyOf(score, len); // 复制并排序数组，为了后续使用binarySearch找到其rank
        Arrays.sort(sortedScore);
        String[] res = new String[len];
        for (int i = 0; i < len; i++) {
            int index = Arrays.binarySearch(sortedScore, score[i]); // 根据score[i]，在sortedScore中找到其index
            if (index == len - 1) { // 若score[i]是最大的，则其index则为最大的下标
                res[i] = "Gold Medal";
            } else if (index == len - 2) {
                res[i] = "Silver Medal";
            } else if (index == len - 3) {
                res[i] = "Bronze Medal";
            } else {
                res[i] = "" + (len - index); // 需注意index和答案需要的rank是相反的
            }
        }
        return res;
    }
}

class Solution {
    public String[] findRelativeRanks(int[] score) {
        int[] clone = Arrays.copyOf(score, score.length);
        Map<Integer, String> hashMap = new HashMap<>(); // num, rank
        Arrays.sort(clone);
        String rank;
        for (int i = 0; i < clone.length; i++) {
            if (i == clone.length - 1) {
                rank = "Gold Medal";
            } else if (i == clone.length - 2) {
                rank = "Silver Medal";
            } else if (i == clone.length - 3) {
                rank = "Bronze Medal";
            } else {
                rank = "" + (clone.length - i);
            }
            hashMap.put(clone[i], rank); // 将该num 和其 rank放入map中
        }
        String[] res = new String[score.length];
        for (int i = 0; i < score.length; i++) {
            res[i] = hashMap.get(score[i]); // 取出该num对应的rank
        }
        return res;
    }
}