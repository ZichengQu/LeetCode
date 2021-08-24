// O(n * log(n))的排序算法

/**
 * 归并排序
 * 除待排序数组本身外，还需要使用额外的空间
 * 最好最坏的时间复杂度都是 O(n * log(n)), S(n)
 */
class Solution {
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        mergeSort(nums, 0, len - 1, result);
        return nums;
    }

    private void mergeSort(int[] nums, int start, int end, int[] result){ // 对 arr 的 [start, end] 区间归并排序
        if(start > end){ // 非法情况。可以和下面的合并在一起，但这里为了逻辑展示方便而分开写。
            return;
        }
        if(start == end){ // 只剩下一个数字，停止拆分
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(nums, start, mid, result); // 拆分左边区域，并将归并排序的结果保存到 result 的 [start, middle] 区间
        mergeSort(nums, mid + 1, end, result); // 拆分右边区域，并将归并排序的结果保存到 result 的 [middle + 1, end] 区间

        merge(nums, start, end, result); // 合并左右区域到 result 的 [start, end] 区间
    }

    private void merge(int[] nums, int start, int end, int[] result){ // 将 result 的 [start, middle] 和 [middle + 1, end] 区间合并
        int mid = (start + end) / 2;

        // 数组 1 的首尾位置
        int start1 = start;
        int end1 = mid;

        // 数组 2 的首尾位置
        int start2 = mid + 1;
        int end2 = end;

        // 用来遍历数组的指针
        // int index1 = start1; // 没必要，直接用start1和start2就可以
        // int index2 = start2;

        // 结果数组的指针
        int resultIndex = start;

        while(start1 <= end1 && start2 <= end2){
            if(nums[start1] <= nums[start2]){
                result[resultIndex++] = nums[start1++];
            }else{
                result[resultIndex++] = nums[start2++];
            }
        }
        // 将剩余数字补到结果数组之后
        while(start1 <= end1){
            result[resultIndex++] = nums[start1++];
        }
        while(start2 <= end2){
            result[resultIndex++] = nums[start2++];
        }
        // 将 result 操作区间的数字拷贝到 arr 数组中，以便下次比较
        for(int i = start; i <= end; i++){
            nums[i] = result[i];
        }
    }
}

/**
 * 归并排序
 * In-Place Merge Sort，原地归并排序：除S(n)外，不使用附加的额外空间
 * 其时间复杂度应为O(n^2)，具体详见下面说明
 * 
 * 这种思路看起来都很美好，但这真的实现了原地归并排序吗？
 * 分析代码可以看出，这样实现的归并本质上是插入排序！
 * 前文已经说到，在插入排序中，腾出位置是一个比较复杂的过程，而且这个过程必然导致增加一轮遍历。
 * 在这份代码中，每一次合并数组时，都使用了两层循环，目的就是不断腾挪位置以插入新数字，可以看出这里合并的效率是非常低的。
 * 这两种排序算法的时间复杂度都达到了 O(n^2)级，不能称之为归并排序。它们只是借用了归并排序的递归框架而已。
 * 也就是说，所谓的原地归并排序事实上并不存在，许多算法书籍中都没有收录这种算法。
 * 它打着归并排序的幌子，卖的是插入排序的思想，实际排序效率比归并排序低得多。
 */
class Solution {
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        mergeSort(nums, 0, len - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int start, int end){ // 对 arr 的 [start, end] 区间归并排序
        if(start == end){
            return; // 只剩下一个数字，停止拆分
        }

        int mid = (start + end) / 2;

        mergeSort(nums, start, mid); // 拆分左边区域
        mergeSort(nums, mid + 1, end); // 拆分右边区域

        merge(nums, start, end); // 合并左右区域
    }

    private void merge(int[] nums, int start, int end){ // 将 arr 的 [start, middle] 和 [middle + 1, end] 区间合并
        int mid = (start + end) / 2;

        // 数组 1 的首尾位置
        int start1 = start;
        int end1 = mid;

        // 数组 2 的首尾位置
        int start2 = mid + 1;
        int end2 = end;

        while(start1 <= end1 && start2 <= end2){
            if(nums[start1] <= nums[start2]){
                start1++;
            }else{ // 右边区域的这个数字比左边区域的数字小，于是它站了起来
                int curNum = nums[start2];
                int index = start2;
                while(index > start1){ // 当index = start1 时，就会将 nums[start1] = nums[start1 - 1]了，因此 index = start1时应跳出while，并将start1的位置赋值为curNum
                    nums[index] = nums[index - 1]; // 前面的数字不断地后移
                    index--;
                }
                // nums[start1] = curNum;
                nums[index] = curNum; // 此时 index = start1。这个数字坐到 start1 所在的位置上

                // 更新所有下标，使其前进一格
                start1++;
                end1++; // 因为前半部分数组的最末尾的元素向后移动了一位
                start2++;
            }
        }
    }
}