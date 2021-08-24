/**
 * 动态规划
 * O(n), S(n)
 */
class Solution {
    public int trap(int[] height) {
        int res = 0;

        int len = height.length;
        if(len <= 2){
            return res;
        }

        int[] leftMax = new int[len]; // 下标为i的位置(包括i)，从左看，最大的高度
        int[] rightMax = new int[len]; // 下标为i的位置(包括i)，从右看，最大的高度

        // int left = 0;
        leftMax[0] = height[0];
        for(int i = 1; i < len; i++){ 
            // left = Math.max(left, height[i]);
            // leftMax[i] = left;
            leftMax[i] = Math.max(leftMax[i - 1], height[i]); // 动态规划思想，下标为i的leftMax，要么是自己，要么是i前面
        }

        // int right = 0;
        rightMax[len - 1] = height[len - 1];
        for(int i = len - 2; i >= 0; i--){
            // right = Math.max(right, height[i]);
            // rightMax[i] = right;
            rightMax[i] = Math.max(rightMax[i + 1], height[i]); // 动态规划思想，下标为i的rightMax，要么是自己，要么是i后面
        }

        for(int i = 0; i < len; i++){
            int area = Math.min(leftMax[i], rightMax[i]) - height[i]; // 任何一个位置i，其宽度为1，高度为min(leftMax, rightMax)，再减去自身高度，则为下标i这一位置，可容纳的雨水量。
            res += area;
        }

        return res;
    }
}

/**
 * 单调栈
 * O(n), S(n)
 */
class Solution {
    public int trap(int[] height) {
        Deque<Integer> stack = new LinkedList<>(); // 单调递减栈(其实可以严格递减，也可以非递增)

        int rain = 0; // 结果
        
        int len = height.length;
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && height[stack.peekLast()] < height[i]){ // 目前元素比栈中大，则弹栈，一直至当前元素 <= 栈中下标对应的元素。这是非递增栈。如果改成 height[stack.peekLast()] <= height[i]，就变成了递减栈。
            // 逻辑不同，但结果一致。
            // 非递增栈: height[stack.peekLast()] < height[i] 是后者比前者大，形成坑洼，才会有rain的增量。
            // 递减栈: height[stack.peekLast()] <= height[i] 是后者>=前者，哪怕是平齐的，但计算currHeight时，其值为0，不影响rain的值。
                int index = stack.pollLast(); // 取出当前值
                if(stack.isEmpty()){ // 如果前面没有比当前值大的(栈为空)，则以为着没有雨水能积累在其上，直接break
                    break;
                }
                int left = stack.peekLast(); // 取出index左侧 >= 下标为index的高度的
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[index];
                rain += currWidth * currHeight;
            }
            stack.offerLast(i);
        }

        return rain;
    }
}

/**
 * 双指针
 * O(n), S(1)
 * 
 * 当两个指针没有相遇时,进行如下操作:
 * 使用 height[left] 和 height[right] 的值更新 leftMax 和 rightMax 的值;
 * 如果 height[left] < height[right], 则必有 leftMax < rightMax, 下标 left 处能接的雨水量等于 leftMax - height[left], 更新rain值, 然后将 left 加1 (即向右移动一位);
 * 如果 height[left] ≥ height[right], 则必有 leftMax ≥ rightMax, 下标 right 处能接的雨水量等于 rightMax − height[right], 更新rain值, 然后将 right 减1 (即向左移动一位)。 
 */
class Solution {
    public int trap(int[] height) {
        int rain = 0;

        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;

        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                // rain += Math.min(leftMax, rightMax) - height[left];
                // rain += Math.min(leftMax, height[right]) - height[left];
                rain += leftMax - height[left]; // leftMax一定不大于height[right]，
                left++;
            } else {
                // rain += Math.min(leftMax, rightMax) - height[right];
                // rain += Math.min(height[left], rightMax) - height[right];
                rain += rightMax - height[right];
                right--;
            }
        }
        return rain;
    }
}