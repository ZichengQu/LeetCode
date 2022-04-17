/**
 * 暴力法
 * O(n^3), S(1)
 */
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;
        for(int start = 0; start < n; start++){
            for(int len = 1; start + len <= n; len += 2){ // 不能是 < n，否则最后的元素可能无法加到
                int end = start + len - 1; // 因为上一行的 <=
                for(int i = start; i <= end; i++){
                    sum += arr[i];
                }
            }
        }

        return sum;
    }
}

/**
 * 前缀和
 * O(n^2), S(n)
 */
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;
        int[] prefixSum = new int[n + 1];

        for(int i = 0; i < n; i++){
            prefixSum[i + 1] = prefixSum[i] + arr[i]; // prefixSums[i] 表示数组 arr 从下标 0 到下标 i−1 的元素和。
        }

        for(int start = 0; start < n; start++){
            for(int len = 1; start + len <= n; len += 2){ // 由于每次自加2，因此可能会丢失掉最后的元素，因此要 <=，而不是 <
                int end = start + len - 1;
                sum += prefixSum[end + 1] - prefixSum[start];
            }
        }

        return sum;
    }
}
