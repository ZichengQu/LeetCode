// 官解视频很全面，文字解释没必要看
// https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/zhu-zhuang-tu-zhong-zui-da-de-ju-xing-by-leetcode-/

/**
 * 暴力解法
 * O(n^2), S(1)
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }
        int maxArea = 0;
        for(int i = 0; i < heights.length; i++){
            int minHeight = Integer.MAX_VALUE;
            for(int j = i; j < heights.length; j++){
                minHeight = Math.min(minHeight, heights[j]);
                int area = minHeight * (j - i + 1);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}

/**
 * 单调栈，空间换时间
 * O(n), S(n)
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        if(heights == null){
            return 0;
        }

        Stack<Integer> heightStack = new Stack<>();

         int len = heights.length;
        for(int i = 0; i < len; i++){
            int lastIndex = heightStack.isEmpty()? -1: heightStack.peek(); // 取stack中的最后一个index
            int lastHeight = lastIndex == -1? 0: heights[lastIndex]; // 将index转换为相应的高度
            if(lastHeight <= heights[i]){
                heightStack.push(i); // 如果后续大于当前的lastHeight，则说明这个lastHeight没到求面积的时候，因为后面的高度比这个还大，说明宽度可以延申。
            }else{
                while(!heightStack.isEmpty() && lastHeight > heights[i]){ // 否则，lastHeight大于后面，则说明，这个lastHeight是当前最大的高度，后面比它小，因此对这个lastHeight求一下它能表示的最大面积。
                    heightStack.pop();
                    int lastSecondIndex = heightStack.isEmpty()? 0: heightStack.peek() + 1; // 取倒数第二个，因为倒数第二个的高度比这个lastHeight的高度小，说明宽度的计算可以从i到lastSecondIndex，而不只是lastIndex
                    maxArea = Math.max(maxArea, lastHeight * (i - lastSecondIndex));

                    lastIndex = heightStack.isEmpty()? -1: heightStack.peek();
                    lastHeight = lastIndex == -1? 0: heights[lastIndex];
                }
                heightStack.push(i);
            }
        }
        
        while(!heightStack.isEmpty()){
            int lastIndex = heightStack.pop();
            int lastSecondIndex = heightStack.isEmpty()? 0: heightStack.peek() + 1;
            maxArea = Math.max(maxArea, heights[lastIndex] * (len - lastSecondIndex));
        }
        return maxArea;
    }
}

/**
 * 单调栈，空间换时间
 * 哨兵思想，避免特殊情况
 * O(n), S(n)
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        if(heights == null){
            return maxArea;
        }

        // 在原数组heights的首尾分别加上哨兵0.
        int len = heights.length;
        int[] newHeights = new int[len + 2];
        for(int i = 0; i < len; i++){
            newHeights[i + 1] = heights[i];
        }
        len = newHeights.length;
        
        Stack<Integer> indexStack = new Stack<>();
        // 首哨兵的作用：一直不用考虑某些情况该stack是否为null.
        indexStack.push(newHeights[0]); 
        // 尾哨兵的作用：所有真实元素均大于等于0，因此最终stack中除了首哨兵或其余高度0(高度0，则面积一定为0)，在for循环结束前均会出栈.

        for(int i = 1; i < len; i++){
            while(newHeights[indexStack.peek()] > newHeights[i]){ // 如果当前height小于stack中的下标对应的height，则此下标对应的height对应的area已经可以确定
                    int lastIndex = indexStack.pop();
                    int lastSecondIndex = indexStack.peek() + 1;
                    int area = newHeights[lastIndex] * (i - lastSecondIndex); // area = height * width
                    maxArea = Math.max(maxArea, area);
            }
            indexStack.push(i); // 此时stack中除哨兵0外，要么其余元素均小于该index i对应的height，因此，无法计算stack中的height。要么stack除哨兵0外，为空。
        }
        return maxArea;
    }
}
