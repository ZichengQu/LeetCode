// O(n ^ 2)的排序算法

/**
 * 类似于扑克牌，一直和之前的比较，如果比之前的小，就互换
 */
class Solution {
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        for(int i = 1; i < len; i++){
            int curNum = nums[i];
            int preIndex = i - 1;
            while(preIndex >= 0 && curNum < nums[preIndex]){
                // 两种方式都可以
                // nums[preIndex + 1] = nums[preIndex]; // 方式一：一直往后挪，最后换
                swap(nums, preIndex + 1, preIndex); // 方式二：过程中就一直交换

                preIndex--;
            }
            // nums[preIndex + 1] = curNum; // 方式一：一直往后挪，最后换
        }

        return nums;
    }

    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}