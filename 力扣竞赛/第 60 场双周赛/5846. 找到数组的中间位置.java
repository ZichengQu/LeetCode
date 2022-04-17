// https://leetcode-cn.com/problems/find-the-middle-index-in-array/

/**
 * 利用两侧的前缀和进行模拟
 * O(n), S(n)
 */
class Solution {
    public int findMiddleIndex(int[] nums) {
        int len = nums.length;

        int[] leftSum = new int[len];
        int[] rightSum = new int[len];

        for(int i = 0; i < nums.length; i++){
            if(i > 0){
                leftSum[i] = leftSum[i - 1] + nums[i];
            }else{
                leftSum[0] = nums[0];
            }
        }

        for(int i = len - 1; i >= 0; i--){
            if(i < len - 1){
                rightSum[i] = rightSum[i + 1] + nums[i];
            }else{
                rightSum[len - 1] = nums[len - 1];
            }
        }

        for(int i = 0; i < len; i++){
            if(leftSum[i] == rightSum[i]){
                return i;
            }
        }

        return -1;
    }
}

/**
 * O(n), S(1)
 */
class Solution {

	public int findMiddleIndex(int[] nums) {
		int right = 0;
		for (int num : nums) {
			right += num;
		}
		for (int i = 0, left = 0; i < nums.length; i++) {
			if (left == (right -= nums[i])) {
				return i;
			}
			left += nums[i];
		}
		return -1;
	}
}