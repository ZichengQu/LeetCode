class Solution {
    public int findMaxLength(int[] nums) {
        int sum = 0;
        int length = 0;
        Map<Integer, Integer> hashMap = new HashMap<>(); // key存的是sum，value存的是坐标
        hashMap.put(0, -1);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                sum--; // 和560题类似，将其转为子列和为0的特例，并将个数改为坐标
            }else{
                sum++;
            }
            if(hashMap.containsKey(sum - 0)){ // 560题中的sum - k
                length = Math.max(length, i - hashMap.get(sum));
            }else{
                hashMap.put(sum, i);
            }
        }
        return length;
    }
}