class Solution {
    public void moveZeroes(int[] nums) {
        int nonZeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i > nonZeroCount) { // 说明当前元素之前有0
                    nums[nonZeroCount] = nums[i];
                    nums[i] = 0;
                }
                nonZeroCount++;
            }
        }
    }
}

// class Solution {
//     public void moveZeroes(int[] nums) {
//         Queue<Integer> zeroIndex = new LinkedList<>();
//         for(int i = 0; i < nums.length; i++){
//             if(nums[i] == 0){
//                 zeroIndex.offer(i);
//             }else{
//                 if(!zeroIndex.isEmpty()){
//                     int index = zeroIndex.poll();
//                     nums[index] = nums[i];
//                     zeroIndex.offer(i);
//                 }
//             }
//         }
//         int zeroCount = zeroIndex.size();
//         for(int i = nums.length - zeroCount; i < nums.length; i++){
//             nums[i] = 0;
//         }
//     }
// }

// class Solution {
//     public void moveZeroes(int[] nums) {
//         int nonZeroIndex = 0;
//         int zeroCount = 0;
//         for(int i = 0; i < nums.length; i++){
//             if(nums[i] != 0){
//                 nums[nonZeroIndex] = nums[i];
//                 nonZeroIndex++;
//             }else{
//                 zeroCount++;
//             }
//         }
//         while(zeroCount > 0){
//             nums[nums.length - zeroCount] = 0;
//             zeroCount--;
//         }
//     }
// }

