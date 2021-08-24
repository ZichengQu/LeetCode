// 1级台阶：1种
// 2级台阶：2种
// 3级台阶：1级台阶+2步 或 2级台阶+1步 = f(1) + f(2)
// 4级台阶：2级台阶+2步 或 3级台阶+1步 = f(2) + f(3)
// n级台阶：f(n) = f(n - 2) + f(n - 1), Fibonacci数列

// /**
//  * 斐波那契数列的递归
//  * 时间复杂度为O(2^n)，每递归一层，分成左右两个子结点(2倍)，共有2^n个结点
//  * 空间复杂度为O(n)，递归n层
//  */
// class Solution {
//     public int climbStairs(int n) {
//         if(n <= 2){
//             return n;
//         }
//         return climbStairs(n - 1) + climbStairs(n - 2);
//     }
// }

/**
 * 最近重复子问题，滚动数组
 */
class Solution {
    public int climbStairs(int n) {
        int first = 1;
        int second = 2;
        int third = n;
        for(int i = 3; i <= n; i++){
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }
}