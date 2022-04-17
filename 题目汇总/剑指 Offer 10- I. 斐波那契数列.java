/**
 * 动态规划
 * O(n), S(n)
 */
class Solution {
    public int fib(int n) {
        if(n < 2){
            return n;
        }

        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;

        for(int i = 2; i <= n; i++){
            arr[i] = (arr[i - 1] + arr[i - 2]) % (int)(1e9 + 7);
        }

        return arr[n];
    }
}

/**
 * 动态规划
 * O(n), S(1)
 */
class Solution {
    public int fib(int n) {
        if(n < 2){
            return n;
        }

        int[] arr = new int[3]; // arr[0] = fib(n - 2); arr[1] = fib(n - 1); arr[2] = fib(n)
        arr[0] = 0;
        arr[1] = 1;

        for(int i = 2; i <= n; i++){
            arr[2] = (arr[0] + arr[1]) % (int)(1e9 + 7);
            arr[0] = arr[1];
            arr[1] = arr[2];
        }

        return arr[2];
    }
}

/**
 * 递归的方式
 * O(n), S(n)
 */
class Solution {
    public int fib(int n) {
        long[] arr = new long[n]; // 记录状态
        Arrays.fill(arr, -1);
        return (int)fib(n, arr);
    }

    private long fib(int n, long[] arr){
        if(n == 0){
            return 0;
        }else if(n == 1){
            return 1;
        }
        if(arr[n - 1] == -1){
            arr[n - 1] = fib(n - 1, arr);
        }
        if(arr[n - 2] == -1){
            arr[n - 2] = fib(n - 2, arr);
        }
        
        return (arr[n - 1] + arr[n - 2]) % (int)(1e9 + 7);
    }
}