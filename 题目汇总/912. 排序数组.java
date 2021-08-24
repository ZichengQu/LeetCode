// O(n ^ 2)的排序算法
/**
 * 标准选择排序
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
            
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;

            // 这里不能这么简单的直接用位运算，因为假如minIndex和i相等，为1；数组为[1,2,3,5]；
            // nums[minIndex] ^= nums[i]; // 这里将nums[minIndex]变为0的同时，也会改变nums[i]的值，因此不能像通用情况下两数交换的逻辑使用。
            // nums[i] ^= nums[minIndex];
            // nums[minIndex] ^= nums[i];
        }
        return nums;
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