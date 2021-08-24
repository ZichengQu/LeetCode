class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int cur = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                cur++;
                continue;
            }else if(cur > max){
                max = cur;
            }
            cur = 0;
        }
        if(cur > max){
            max = cur; // 最后一个为1时，cur可能大约max
        }
        return max;
    }
}

// class Solution {
//     public int findMaxConsecutiveOnes(int[] nums) {
//         int maxCount = 0, count = 0;
//         int n = nums.length;
//         for (int i = 0; i < n; i++) {
//             if (nums[i] == 1) {
//                 count++;
//             } else {
//                 maxCount = Math.max(maxCount, count);
//                 count = 0;
//             }
//         }
//         maxCount = Math.max(maxCount, count);
//         return maxCount;
//     }
// }

// 作者：LeetCode-Solution
// 链接：https://leetcode-cn.com/problems/max-consecutive-ones/solution/zui-da-lian-xu-1de-ge-shu-by-leetcode-so-252a/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。