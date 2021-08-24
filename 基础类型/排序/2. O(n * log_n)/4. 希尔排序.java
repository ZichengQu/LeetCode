// O(n * log(n))的排序算法

/**
 * 带gap的插入排序
 * 相比于 n^2 级的排序算法，希尔排序可利用gap，一次性消除多个逆序对
 */
class Solution {
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        
        for(int gap = len / 2; gap > 0; gap /= 2){ // 间隔序列，在希尔排序中我们称之为增量序列
            for(int i = gap; i < len; i++){ // 从 gap 开始，按照顺序将每个元素依次向前插入自己所在的组
                int curNum = nums[i]; // currentNumber 站起来，开始找位置
                int preIndex = i - gap; // 该组前一个数字的索引
                while(preIndex >= 0 && curNum < nums[preIndex]){
                    // 两种方式都可以
                    // nums[preIndex + gap] = nums[preIndex]; // 方式一：一直往后挪，最后换
                    swap(nums, preIndex + gap, preIndex); // 方式二：过程中就一直交换

                    preIndex -= gap;
                }
                // nums[preIndex + gap] = curNum; // 方式一：一直往后挪，最后换。最终currentNumber 找到了自己的位置，坐下
            }
        }

        return nums;
    }

    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}