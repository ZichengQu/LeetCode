/**
 * 二分法
 * O(log(n))
 */
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(!((mid - 1 >= 0 && nums[mid - 1] == nums[mid]) || (mid + 1 < nums.length && nums[mid] == nums[mid + 1]))){
                return nums[mid]; // 与左侧或右侧都不相等时 return
            }
            if(mid % 2 == 0){ // mid 是偶数 index 时
                if(mid + 1 < nums.length && nums[mid] == nums[mid + 1]){ // 偶数 index 应和右侧对比，若一致，则前面没问题
                    left = mid + 1;
                }else{
                    right = mid - 1; // 若不一致则前面有问题
                }
            }else{ // mid 是奇数 index 时
                if(mid - 1 >= 0 && nums[mid - 1] == nums[mid]){ // 奇数 index 应和左侧对比，若一致，则前面没问题
                    left = mid + 1;
                }else{
                    right = mid - 1; // 若不一致则前面有问题
                }
            }
        }

        return -1;
    }
}

/**
 * 异或
 * O(n)
 */
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int res = 0; // 0 与任何数亦或都是本身
        for(int num: nums){
            res ^= num;
        }
        return res;
    }
}

// 二分，另一种二分思路
int singleNonDuplicate(int* nums, int numsSize) {
    int left = 0;
    int right = numsSize - 1;

    /*
     * 正常情况下，重复元素应该按照下面的方式两两配对：
     *
     * index:  0 1 | 2 3 | 4 5 | 6 7
     * value:  A A | B B | C C | D D
     *
     * 即：
     * 偶数下标和它右边的奇数下标是一对。
     *
     * 如果中间出现一个单独元素：
     *
     * index:  0 1 | 2 3 | 4 | 5 6 | 7 8
     * value:  A A | B B | X | C C | D D
     *
     * 单独元素之前：配对规律正常，为“偶数 + 奇数”
     * 单独元素之后：配对整体错位，变成“奇数 + 偶数”
     *
     * 因此可以通过二分查找，寻找配对规律开始被破坏的位置。
     */
    while (left < right) {
        int mid = (left + right) / 2;

        /*
         * 为了让判断逻辑统一，强制让 mid 指向偶数下标。
         *
         * 因为正常情况下，一对元素应该是：
         *
         * nums[mid] == nums[mid + 1]
         *
         * 这样就不用分别记：
         * “偶数下标看右边、奇数下标看左边”。
         */
        if (mid % 2 == 1)
            mid--;

        /*
         * mid 是偶数下标，并且 nums[mid] == nums[mid + 1]：
         *
         * 说明这一对仍然符合正常的
         * “偶数下标 + 右侧奇数下标”配对规律。
         *
         * 因此单独元素一定在这一对的右边。
         * 当前这一对已经确认正常，可以全部排除，
         * 所以 left 直接移动到 mid + 2。
         */
        if (nums[mid] == nums[mid + 1])
            left = mid + 2;

        /*
         * nums[mid] != nums[mid + 1]：
         *
         * 说明从这里开始，“偶数 + 右侧奇数”的正常配对规律
         * 已经被单独元素破坏。
         *
         * 单独元素可能就是 nums[mid]，
         * 也可能位于 mid 左边，
         * 因此 mid 不能排除，令 right = mid。
         */
        else
            right = mid;
    }

    /*
     * 最终 left == right，
     * 二分区间收敛到唯一的单独元素。
     */
    return nums[left];
}
