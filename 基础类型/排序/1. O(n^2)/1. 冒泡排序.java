// O(n ^ 2)的排序算法

/**
 * 如果按从小到大，则每次都把最大的数，冒到最右边
 */
class Solution {
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        for(int i = 0; i < len - 1; i++){
            // boolean flag = true;
            for(int j = 0; j < len - 1 - i; j++){ // -i 的目的是：每次冒泡，当前循环的最后一位已经是最大的数了，因此无需再次判断最大数所在的范围。
                if(nums[j] > nums[j + 1]){ // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    swap(nums, j, j + 1);
                    // flag = false;
                }
            }
            // if(flag){ // 如果内层for循环一整轮都没有更换，说明已有序，可提前退出
            //     break;
            // }
        }
        return nums;
    }

    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
