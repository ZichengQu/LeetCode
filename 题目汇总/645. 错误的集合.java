// class Solution {
//     public int[] findErrorNums(int[] nums) {
//         Arrays.sort(nums);
//         int num1 = -1;
//         int num2 = (1 + nums.length) * nums.length / 2; // 数组和
//         for(int i = 1; i < nums.length; i++){
//             if(nums[i - 1] == nums[i]){
//                 num1 = nums[i];
//             }
            
//             num2 -= nums[i]; // 数组和 - 每一项
//         }
//         num2 -= nums[0]; // 数组和 - 每一项的时候，漏掉了第0项
//         return new int[]{num1, num2 + num1}; // 被漏掉的项应为: 剩余的num2(差值)+num1(重复部分)
//     }
// }

// class Solution {
//     public int[] findErrorNums(int[] nums) {
//         int duplicated = -1;
//         int missing = -1;
//         for(int i = 0; i < nums.length; i++){
//             if(nums[Math.abs(nums[i]) - 1] > 0){
//                 nums[Math.abs(nums[i]) - 1] *= -1;
//             }else{
//                 duplicated = Math.abs(nums[i]);
//             }
//         }
//         for(int i = 0; i < nums.length; i++){
//             if(nums[i] > 0){
//                 missing = i + 1;
//                 break;
//             }
//         }
//         return new int[]{duplicated, missing};
//     }
// }

class Solution {
    public int[] findErrorNums(int[] nums) {
        int duplicated = -1;
        int missing = (1 + nums.length) * nums.length / 2; // 和

        HashSet<Integer> hashSet = new HashSet<>();

        for(int i = 0; i < nums.length; i++){
            boolean flag = hashSet.add(nums[i]); // 如果已重复，会返回false
            if(!flag){
                duplicated = nums[i];
            }
            missing -= nums[i]; // 和 - 所有项的值 = 差值
        }
        missing += duplicated; // 差值 + 重复值 = 缺失值

        return new int[]{duplicated, missing};

    }
}