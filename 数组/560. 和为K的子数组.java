/**
 * O(n^2)
 */
// class Solution {
//     public int subarraySum(int[] nums, int k) {
//         int sum = 0;
//         int count = 0;
//         for(int i = 0; i < nums.length; i++){
//             sum += nums[i];
//             if(sum == k){
//                 count++;
//             }
//             for(int j = i + 1; j < nums.length; j++){
//                 sum += nums[j];
//                 if(sum == k){
//                     count++;
//                 }
//             }
//             sum = 0;
//         }
//         return count;
//     }
// }

/**
 * O(n)
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0;
        int count = 0;
        Map<Integer, Integer> hashMap = new HashMap<>(); // key存的是sum，value存的是个数
        hashMap.put(0, 1); // 因为最少是1个
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(hashMap.containsKey(sum - k)){
                count += hashMap.get(sum - k);
            }
            hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}

// 0 0 0 0 0 0 0 0 0 0