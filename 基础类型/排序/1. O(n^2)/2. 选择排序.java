// O(n ^ 2)的排序算法

/**
 * 标准选择排序
 * 每一轮把最小的元素，放到未排序部分的开头
 */
class Solution {
    public int[] sortArray(int[] nums) {
        int minIndex = 0;
        for(int i = 0; i < nums.length - 1; i++){
            minIndex = i;
            for(int j = i + 1; j < nums.length; j++){
                if(nums[j] < nums[minIndex]){
                    minIndex = j;
                }
            }
            swap(nums, minIndex, i);
        }
        return nums;
    }

    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;

        // 这里不能这么简单的直接用位运算，因为假如minIndex和i相等，为1；数组为[1,2,3,5]；
        // nums[minIndex] ^= nums[i]; // 这里将nums[minIndex]变为0的同时，也会改变nums[i]的值，因此不能像通用情况下两数交换的逻辑使用。
        // nums[i] ^= nums[minIndex];
        // nums[minIndex] ^= nums[i];
    }
}

/**
 * 二元选择排序
 */
class Solution {
    public int[] sortArray(int[] nums) {
        int minIndex = 0;
        int maxIndex = 0;
        // i 只需要遍历一半
        for(int i = 0; i < nums.length / 2; i++){ // 这里可以优化为"nums.length / 2"，因为每次排一前一后两个元素。
            minIndex = i;
            maxIndex = i;
            for(int j = i + 1; j < nums.length - i; j++){ // 这里必须是"nums.length - i"，要把已排好序的最大元素排除在计算范围之内。同理，排好序的最小元素也要排除在外。
                if(nums[j] < nums[minIndex]){
                    minIndex = j; // 记录最小值的下标
                }else if(nums[j] > nums[maxIndex]){
                    maxIndex = j; // 记录最大值的下标
                }
            }
            // 如果 minIndex 和 maxIndex 都相等，那么他们必定都等于 i，且后面的所有数字都与 arr[i] 相等，此时已经排序完成
            if(minIndex == maxIndex){
                break;
            }
            // 将最小元素交换至首位
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;

            // 如果最大值的下标刚好是 i，由于 arr[i] 和 arr[minIndex] 已经交换了，所以这里要更新 maxIndex 的值。
            if(maxIndex == i){
                maxIndex = minIndex;
            }
            // 将最大元素交换至末尾
            int lastIndex = nums.length - 1 - i;
            temp = nums[maxIndex];
            nums[maxIndex] = nums[lastIndex];
            nums[lastIndex] = temp;
        }
        return nums;
    }
}