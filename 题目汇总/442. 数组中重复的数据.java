class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new LinkedList<>();
        int index;
        for(int i = 0; i < nums.length; i++){
            index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0){
                nums[index] *= -1;
            }else{
                list.add(index + 1);
            }
        }
        return list;
    }
}