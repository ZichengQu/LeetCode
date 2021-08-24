class Solution {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, int[]> hashMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int[] data = hashMap.getOrDefault(nums[i], new int[]{0, i, i});
            int occurrences = data[0] + 1;
            int first = data[1];
            int last = i;
            hashMap.put(nums[i], new int[]{occurrences, first, last});
        }
            
        int max = Integer.MIN_VALUE;
        int minLen = Integer.MAX_VALUE;
        for(Map.Entry<Integer, int[]> entrySet: hashMap.entrySet()){
            int[] data = entrySet.getValue();
            if(data[0] > max){
                max = data[0];
                minLen = data[2] - data[1];
            }else if(data[0] == max){
                minLen = Math.min(minLen, data[2] - data[1]);
            }
        }
        return minLen + 1;
    }
}