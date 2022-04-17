import java.util.*;

/**
 * 动态规划
 * 给定一个数组，每个区间所有数字的 & 运算结果为 0 的区间的个数
 */
public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 返回答案值
     * @param n int整型 数组长度
     * @param Array int整型一维数组 数组Array
     * @return long长整型
     */
    public long ans (int n, int[] Array) {
        // write code here
        long cnt = 0;
        
        int[][] dp = new int[n][n]; // 从某一下标到另一下标的区间，& 运算的结果
        
        for(int i = 0; i < n; i++){// 初始化：确定长度为 1 的区间中，其结果就是 Array 数组本身
            dp[i][i] = Array[i];
        }
        
        for(int len = 2; len <= n; len++){ // 从长度为 2 开始进行 dp
            for(int left = 0; left < n; left++){ // 左边界
                int right = left + len - 1; // 右边界
                if(right >= n){
                    break;
                }
                for(int index = left; index < right; index++){ // 左右边界里的每个小分区
                    dp[left][right] = (dp[left][index] & dp[index + 1][right]);
                    if(dp[left][right] == 0){ // 已得到想要的值，提前终止此次分区判断
                        cnt++;
                        break;
                    }
                }
            }
        }
        
        return cnt;
    }
}