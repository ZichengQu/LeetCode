/**
 * 官解
 * 方法二：单调队列
 * O(n), S(k)
 * https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> slideWindow = new LinkedList<>(); // 双端队列作为滑动窗口，存储的是下标。其值通过nums[下标]查找

        int len = nums.length;

        for(int i = 0; i < k; i++){ // 将前k个都放入滑动窗口
            while(!slideWindow.isEmpty() && nums[i] >= nums[slideWindow.peekLast()]){ // 因为i是待加入元素，因此i是最后一个。所以如果在该滑动窗口中，待加入元素的值 >= 之前的元素的值，则之前的元素就不起作用了
                slideWindow.pollLast(); // 将其删除
            }
            slideWindow.offerLast(i); // 将待加入元素加入队尾
        }

        int[] res = new int[len - k + 1];

        res[0] = nums[slideWindow.peekFirst()]; // 在前面的while循环中，如果后面加入的比前面大，则前面的会一直被poll，因此最大的是队头。

        for(int i = k; i < len; i++){ // 从下标为k的元素开始遍历
            while(!slideWindow.isEmpty() && nums[i] >= nums[slideWindow.peekLast()]){ // 同理
                slideWindow.pollLast();
            }
            slideWindow.offerLast(i);
            while(!slideWindow.isEmpty() && slideWindow.peekFirst() < i - k + 1){ // 如果其下标不在当前滑动窗口的有效范围内，则删除。
                slideWindow.pollFirst();
            }
            res[i - k + 1] = nums[slideWindow.peekFirst()]; // 通过有效的最大元素的下标，找到值，并赋给res
        }

        return res;
    }
}


/**
 * 官解
 * 方法一：优先队列
 * O(n * log(n)), S(n)
 * https://leetcode-cn.com/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;

        // int[], 0 -> num, 1 -> index;
        PriorityQueue<int[]> slideWindow = new PriorityQueue<>(new Comparator<int[]>(){ // 如果是Integer的话，在compare中也要写Integer，写int会报错
            public int compare(int[] pair1, int[] pair2){
                return pair1[0] != pair2[0]? -(pair1[0] - pair2[0]): -(pair1[1] - pair2[1]); // 先按index为0大的排，若一致，则按index为1的大的排序
            }
        });

        for(int i = 0; i < k; i++){
            slideWindow.offer(new int[]{nums[i], i}); // 将前k个加入到最大堆中
        }

        int[] res = new int[len - k + 1];

        res[0] = slideWindow.peek()[0]; // 前k个可以选出来一个最大的

        for(int i = k; i < len; i++){
            slideWindow.offer(new int[]{nums[i], i}); // 把当前元素加入到最大堆中
            while(slideWindow.peek()[1] <= i - k){ // 从当前最大堆中选择一个最大的元素，如果它的index不在目前的滑动窗口内部，则删除，继续找下一个最大的
                slideWindow.poll();
            }
            res[i - k + 1] = slideWindow.peek()[0]; // 将找到的位于当前滑动窗口内部的最大值加入到res中。
        }

        return res;
    }
}

/**
 * 自己的解
 * O(n * log(n)), S(k)
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k <= 1){
            return nums;
        }
        
        int[] res = new int[nums.length - k + 1];
        
        TreeMap<Integer, Integer> window = new TreeMap<>(); // num, occurrence

        int discard = nums[0]; // 滑动窗口中最前面的元素

        for(int i = 0; i < k - 1; i++){ // 把前k-1个都放进滑动窗口
            window.put(nums[i], window.getOrDefault(nums[i], 0) + 1); // 同时记录每个值的出现次数
        }

        int current;
        
        for(int i = k - 1; i < nums.length; i++){
            current = nums[i]; // 当前第k个元素的值
            int lastKey = window.lastKey(); // 滑动窗口中前k-1个元素的最大的值
            int max = Math.max(current, lastKey); // 比较
            res[i - k + 1] = max;
            window.put(current, window.getOrDefault(current, 0) + 1); // 将当前元素加入到滑动窗口
            // 将滑动窗口中最前面的元素删除
            if(window.get(discard) == 1){
                window.remove(discard);
            }else{
                window.put(discard, window.get(discard) - 1);
            }
            discard = nums[i - (k - 1) + 1]; // 更新滑动窗口中最前面的元素
        }

        return res;
    }
}

/**
 * 暴力法
 * O(n^2), S(1)
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        for(int i = 0; i <= nums.length - k; i++){
            int max = nums[i];
            for(int j = i + 1; j < i + k; j++){
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }
        return res;
    }
}