/**
 * 基于选择排序。
 * 与标准选择排序的逻辑类似，外层for循环控制选择的最大或最小元素的个数。
 * 内层控制选择最大或最小。
 * O(n^2), S(1)
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int maxIndex = 0;
        int res = 0;
        for(int i = nums.length - 1; i > nums.length - 1 - k; i--){
            maxIndex = i;
            for(int j = i - 1; j >= 0; j--){
                if(nums[maxIndex] < nums[j]){
                    maxIndex = j;
                }
            }
            res = nums[maxIndex];

            // nums[maxIndex] = nums[i];
            // nums[i] = res;
            nums[maxIndex] ^= nums[i];
            nums[i] ^= nums[maxIndex];
            nums[maxIndex] ^= nums[i];
        }
        return res;
        // Arrays.sort(nums);
        // return nums[nums.length - k];
    }
}

/**
 * 其他排序方法，暂未整理
 * 官解链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode-/
 */
/**
 * 快排
 * 更多快排相关参考，见 题目汇总/912. 排序数组.java
 */
class Solution {
    Random random;
    public int findKthLargest(int[] nums, int k) {
        random = new Random();
        quickSort(nums, 0, nums.length - 1, k);
        return nums[nums.length - k]; // 返回第k大的元素
    }

    private void quickSort(int[] nums, int left, int right, int k){
        if(left >= right){
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
        if(nums[pivot] < nums[l]){ // 第一种
            swap(nums, pivot, l - 1);
            pivot = l - 1;
        }else{ // 第二种
            swap(nums, pivot, l);
            pivot = l;
        }
        if(pivot == nums.length - k){ // 判断是否已经找到第k大的元素
            return;
        }
        quickSort(nums, left, pivot - 1, k);
        quickSort(nums, pivot + 1, right, k);
    }

    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}

 /**
  * 基于最大堆，使用Java官方优先队列(堆排)的API
  * O(n * log(n))
  */
  class Solution {
    public int findKthLargest(int[] nums, int k) {
        List<Integer> numList = new ArrayList<>();
        for(int num: nums){ // O(n)
            numList.add(num);
        }
        Queue<Integer> pq = new PriorityQueue<>(numList); // O(n)

        int res = 0;
        while(pq.size() >= k){ // O(n * log(n))
            res = pq.poll();
        }

        return res;
    }
}