/**
 * O(m + n), 不符合题意，题中要求O(log(m + n))
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len3 = len1 + len2;

        int index1 = 0;
        int index2 = 0;
        int index3 = 0;

        int[] mergedNums = new int[len3];

        while(index1 < len1 && index2 < len2){
            if(nums1[index1] <= nums2[index2]){
                mergedNums[index3++] = nums1[index1++];
            }else{
                mergedNums[index3++] = nums2[index2++];
            }
        }
        while(index1 < len1){
            mergedNums[index3++] = nums1[index1++];
        }
        while(index2 < len2){
            mergedNums[index3++] = nums2[index2++];
        }
        double mid = 0;
        if(len3 % 2 == 0){
            mid = (double)(mergedNums[len3 / 2] + mergedNums[(len3 / 2) - 1]) / 2;
        }else{
            mid = mergedNums[len3 / 2];
        }
        return mid;
    }
}

/**
 * 官解方法一 (已自我修改)
 * O(log(m+n))
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len3 = len1 + len2;
        double median = 0;
        if(len3 % 2 == 0){
            int medianIndex1 = len3 / 2;
            int medianIndex2 = (len3 / 2) - 1;
            median = (getKIndexElement(nums1, nums2, medianIndex1) +  getKIndexElement(nums1, nums2, medianIndex2)) / 2.0;
        }else{
            int medianIndex = len3 / 2;
            median = getKIndexElement(nums1, nums2, medianIndex);
        }
        return median;
    }

    private int getKIndexElement(int[] nums1, int[] nums2, int k) { // k是综合两个数组，总下标为medianIndex
        /* 主要思路：要找到下标为 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是下标为 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是下标为 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是下标为 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比下标为 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        int len1 = nums1.length;
        int len2 = nums2.length;

        int index1 = 0; // 最左侧需要考虑的元素的下标 for nums1
        int index2 = 0; // 最左侧需要考虑的元素的下标 for nums2

        int kIndex = k;

        while(true){
            // 边界情况 (其实这些不等号，都写成等于就可以)
            if(index1 >= len1){ // 若nums1的所有元素都考虑完了，意味着kIndex不在nums1中
                // return nums2[index2 + k]; // 当前最左侧有效的 + 当前最新的k，因为k是会变化的，每轮都会更新.
                return nums2[kIndex - len1]; // kIndex - len1 ==> (获得下标)
            }else if(index2 >= len2){
                // return nums1[index1 + k];
                return nums1[kIndex - len2];
            }else if(k <= 0){
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            /**
             * 下标  0 1 2 3 4 5 6 7 8
             * half  0 0 1 1 2 2 3 3 4
             * 经过 +1 之后
             * 下标  1 2 3 4 5 6 7 8 9
             * half  0 1 1 2 2 3 3 4 4
             */
            int half = (k + 1) / 2; // 后面把half当下标用，因此 (k+1) / 2 可以得到正确的half的下标
            int newIndex1 = Math.min(index1 + half, len1) - 1; // 最开始index1 = 0，因此 newIndex1 <= (k / 2) - 1 // 右边界
            int newIndex2 = Math.min(index2 + half, len2) - 1;

            int pivot1 = nums1[newIndex1];
            int pivot2 = nums2[newIndex2];

            if(pivot1 <= pivot2){ // 因为pivot1 <= pivot2，因此pivot1所在的newIndex1最多大于等于 2 * [(k / 2) - 1] = k - 2, 因此pivot1最多为第k - 1个元素
                k -= (newIndex1 - index1 + 1); // "删除"前index1个，因为其不符合下标为k的元素的条件，更新k值
                index1 = newIndex1 + 1; // 因为在newIndex1之前都不符合了，因此更新最左侧有效边界index1.
            }else{
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}