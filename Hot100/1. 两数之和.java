/**
 * O(n), S(n)
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>(); // key是nums里的值，value是该值的下标
        for(int i = 0; i < nums.length; i++){
            if(hashMap.containsKey(target - nums[i])){ // 如果target - nums[i]已存在于该map中
                return new int[]{hashMap.get(target - nums[i]), i}; // 则返回这两个数的下标
            }
            hashMap.put(nums[i], i);
        }
        return null;
    }
}

// 也可以用排序，O(nlog_n), S(nlog_n)
// 然后通过nums[i]，利用binary search去查找target - nums[i]是否存在 O(n) * O(log_n) = O(nlog_n)

// 暴力破解，O(n^2), S(1)