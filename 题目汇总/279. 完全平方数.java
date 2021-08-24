/**
 * 完全背包思路
 * O(n * sqrt(n)), S(n)
 */
class Solution {
    public int numSquares(int n) {
        List<Integer> list = getSequence(n);
        int size = list.size();

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 0; j < size; j++){
                if(i - list.get(j) >= 0){
                    dp[i] = Math.min(dp[i], dp[i - list.get(j)] + 1); // 完全背包问题
                }
            }
        }
        return dp[n];
    }

    private List<Integer> getSequence(int n){
        List<Integer> list = new ArrayList<>();
        for(int i = 1; Math.pow(i, 2) <= n; i++){
            list.add((int)Math.pow(i, 2));
        }
        return list;
    }
}