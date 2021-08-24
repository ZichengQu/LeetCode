/**
 * O(n)
 * 自己的思路: 
 *  最大子序列和。
 *  从前向后遍历该数组，sum等于每对的两位数之差的和。
 *  若sum已经小于0了，则若用此时的sum继续去加后面的，不会比直接用后面的新的sum更大。因此若sum<0，则重置sum。
 *  每次sum更新后，与之前记录的最大的sum（max）作比较，取最大的。
 */
class Solution {
    public int maxProfit(int[] prices) {
        int sum = 0;
        int max = 0;
        for(int i = 1; i < prices.length; i++){
            sum += prices[i] - prices[i- 1];
            max = Math.max(max, sum);
            if(sum < 0){
                sum = 0;
            }
        }
        return max;
    }
}

/**
 * O(n)
 * 力扣官方解答
 */
public class Solution {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i]; // 取当前遍历的位置之前的最小的元素
            } else if (prices[i] - minprice > maxprofit) { // 如果目前元素减去之前的最小元素大于记录的最大差值
                maxprofit = prices[i] - minprice; // 则更新最大差值
            }
        }
        return maxprofit;
    }
}