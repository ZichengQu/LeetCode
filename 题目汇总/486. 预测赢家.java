/**
 * 动态规划思路(从官解按自己的习惯改写)
 * O(n^2), S(n^2)
 */
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len]; // 当某一玩家轮次时，当前dp[left][right]代表该玩家的最大收益与另一玩家的最大收益之差

        for(int i = 0; i < len; i++){
            dp[i][i] = nums[i]; // 长度为1的
        }
        
        for(int i = 2; i <= len; i++){ // 从长度为2到长度为总长的
            for(int left = 0; left < len; left++){ // 左边界
                int right = left + i - 1; // 右边界
                if(right >= len){
                    break;
                }
                int planA = nums[left] - dp[left + 1][right]; // 先选择最左侧 - (2号玩家获得的最大收益 - 1号最大值)
                int planB = nums[right] - dp[left][right - 1]; // 博弈论，同理
                dp[left][right] = Math.max(planA, planB); // 看哪个方案(选 队首 or 队尾)能最大化目前玩家的收益，同时后续玩家仍然能优化自己的最大化收益
            }
        }
        return dp[0][len - 1] >= 0;
    }
}

/**
 * 官解递归 (没有最下面评论里的思想好)
 * O(2^n), S(n)
 * https://leetcode-cn.com/problems/predict-the-winner/solution/yu-ce-ying-jia-by-leetcode-solution/
 */
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }

    public int total(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }
        int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }
}




/**
 * 评论里的思想：
 * O(2^n), S(n)
 * 首先，这题有一个标签是极小化极大（Minimax），带这个标签的题目并不多，但通常有一个范式：
 * A，B轮流进行某种决策，且决策间是相互竞争关系。假定A先进行决策。
 * A，B都符合理性人假设。更确切地说，每一次决策都会选出当前最优的方案（而不会在意已经做过的决策）。
 * 1和2共同反映了极小化极大（Minimax）的定义，即此消彼长。
 *      第一轮A决策极大化A自己的收益，同时间接极小化了B的收益；
 *      第二轮B则相反，如此往复；
 *      2间接确保了最优子结构的存在。
 *      仔细思考会发现这里面有一件事是重复进行的，那就是决策本身——决策者只有两个可能的决策，并且他会选择让自己优势最大化的那个。
 * 因此，方法1中过度强调的轮次（turn）的概念，其实是没有必要的。决策本身和轮次完全无关。A，B就是要从头尾两种决策中选出自己分数最大的一种，仅此而已。
*/
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int difference = dfs(nums, 0, nums.length - 1);
        return difference >= 0;
    }

    private int dfs(int[] nums, int left, int right){ // 当前若某玩家轮次时可获最大收益与另一玩家收益之差
        if(left > right){
            return 0;
        }
        if(left == right){ // 可提前枝剪
            return nums[left];
        }
        int planA = nums[left] - dfs(nums, left + 1, right); // 先选择最左侧 - 2号玩家获得的最大收益
        int planB = nums[right] - dfs(nums, left, right - 1); // 同理
        return Math.max(planA, planB); // 返回最大的收益方案
    }
}