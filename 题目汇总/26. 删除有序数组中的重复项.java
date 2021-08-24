/**
 * 快慢指针
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int len = nums.length;
        int slow = 1; // 下标为0的数的位置不需要动，从下标为1开始.
        int fast = 1;
        while(fast < len){
            if(nums[fast] != nums[fast - 1]){ // 也可以写成 nums[slow - 1] // 最后一个uniqueNum，fast与其不等，则证明fast要么是unique，要么是第一次出现(因为nums已排好序)
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}

class Solution {
    public int removeDuplicates(int[] nums) {
        int uniqueCount = 1; // 至少第一个元素是unique的
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != nums[i - 1]){
                nums[uniqueCount++] = nums[i];
            }
        }
        return uniqueCount;
    }
}