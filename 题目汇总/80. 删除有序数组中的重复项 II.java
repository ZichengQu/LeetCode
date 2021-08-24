// 与 26. 删除有序数组中的重复项 有很大雷同
/**
 * 自己的思路，没有下面的官解思路好
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        int index = 1;
        boolean flag = true;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i - 1]){
                if(flag){
                    nums[index] = nums[i];
                    index++;
                    flag = false;
                }
            }else{
                nums[index] = nums[i];
                index++;
                flag = true;
            }
        }
        return index;
    }
}

/**
 * 双指针问题，快慢指针
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if(len <= 2){
            return len;
        }
        int slow = 2;
        int fast = 2;
        while(fast < len){
            if(nums[slow - 2] != nums[fast]){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}