// O(n * log(n))的排序算法

/**
 * 快速排序
 * O(n * log(n)), 但若每次选择的主元都是第一个，则会退化到O(n^2)，因此可采用随机，来避免达到极端情况
 */
class Solution {
    Random random = new Random();

    public int[] sortArray(int[] nums) {
        quickPivot(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickPivot(int[] nums, int left, int right){
        if(right <= left){
            return;
        }

        int pivot = random.nextInt(right - left + 1) + left; // 随机选主元，最大可能避免最坏情况。假设r=2，l=1，则取值范围是 (2-1+1) + l

        swap(nums, pivot, left); // 将pivot元素交换至目前数组[left, right]范围内的最左侧
        pivot = left; // 因为主元的下标已经是下标为left的位置了, 目前数组[left, right]范围内的最左侧
        int l = pivot + 1; // 从pivot的右侧第一个
        int r = right; // 和目前数组[left, right]范围内的最右侧
        while(l < r){ // 开始遍历
            while(l < r && nums[l] <= nums[pivot]){ // 一直找到第一个比主元大的元素
                l++;
            }
            while(l < r && nums[r] >= nums[pivot]){ // 一直找到一个比主元小的元素
                r--;
            }
            swap(nums, l, r); // 交换其位置, 将比主元小的这个元素换至前面，比主元大的换至后面
        }

        // while循环结束后，可能有两种情况
        // 第一种，l++导致l => r，使循环条件不满足，但是r是大于等于主元的范围，则nums[l]的位置应 >=  主元，所以将主元换到 l - 1 的位置
        // 第二种，r--导致r => l，使循环条件不满足，但是l是小于等于主元的范围，则nums[l]的位置应 <=  主元，所以直接将主元换到 l 的位置
        if(nums[pivot] <= nums[l]){ // 第一种
            swap(nums, pivot, l - 1);
            pivot = l - 1;
        }else{ // 第二种
            swap(nums, pivot, l);
            pivot = l;
        }

        // 分治
        quickPivot(nums, left, pivot - 1); // 左边界到主元之前
        quickPivot(nums, pivot + 1, right); // 主元之后到右边界

    }

    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}