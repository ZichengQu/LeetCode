// O(n * log(n))的排序算法

/**
 * 堆排
 * O(n * log(n)), S(1)
 * 参考视频链接：https://leetcode-cn.com/leetbook/read/leetcamp/rqc453/
 * 参考代码链接：https://leetcode-cn.com/leetbook/read/sort-algorithms/eu7ux3/
 */
class Solution {
    public int[] sortArray(int[] nums) {
        int len = nums.length;

        buildMaxHeap(nums, 0, len); // O(n), 建立最大堆

        // O(n * log(n))
        for(int i = len - 1; i > 0; i--){ // O(n)
            swap(nums, 0, i); // 将最大值交换到数组最后
            shiftDown(nums, 0, i); // O(log(n), 将新的根结点调整到在剩余数组中适当的位置，使其满足最大堆
        }

        return nums;
    }

    private void buildMaxHeap(int[] nums, int rootIndex, int heapSize){ // O(n), 建立最大堆，从哪里开始，持续的size是多少。但其实如果是全部的话，可以不需要后面两个参数
        // int lastNotLeaf = nums.length / 2 - 1; // 最后一个非叶子结点下标
        // for(int i = lastNotLeaf; i >= 0; i--){
        //     shiftDown(nums, i, heapSize);
        // }
        int lastNotLeaf = heapSize / 2 - 1; // 最后一个非叶子结点下标
        for(int i = lastNotLeaf; i >= rootIndex; i--){
            shiftDown(nums, i, heapSize);
        }
    }

    private void shiftDown(int[] nums, int rootIndex, int heapSize){ // O(log(n), 调整大顶堆，第三个参数表示剩余未排序的数字的数量，也就是剩余堆的大小
        int left = 2 * rootIndex + 1; // 左子结点下标
        int right = 2 * rootIndex + 2; // 右子结点下标

        int largest = rootIndex; // 记录根结点、左子树结点、右子树结点三者中的最大值下标

        if(left < heapSize && nums[largest] < nums[left]){ // 与左子树结点比较
            largest = left;
        }

        if(right < heapSize && nums[largest] < nums[right]){ // 与右子树结点比较
            largest = right;
        }

        if(largest != rootIndex){ // 如果此时根的位置不是最大的
            swap(nums, rootIndex, largest); // 将最大的值与根的值互换
            shiftDown(nums, largest, heapSize); // 将原先根的值，一路向下比较，直到找到合适的位置，满足最大堆
        }
    }

    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}