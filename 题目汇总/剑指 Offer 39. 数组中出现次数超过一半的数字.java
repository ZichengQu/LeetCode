class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int curNum = nums[0];
        for(int num: nums){
            if(curNum == num){
                count++;
            }else{
                count--;
            }
            if(count == 0){
                curNum = num;
                count++;
            }
        }

        // 判断当前选出来的是否为多数元素
        // count = 0;
        // for(int num: nums){
        //     if(curNum == num){
        //         count++;
        //     }
        // }

        // return count > nums.length / 2 ? curNum : -1;

        return curNum; // 因为题中说一定存在，所有无需验证
    }
}


class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int curNum = nums[0];
        for(int num: nums){
            if(count == 0){
                curNum = num;
            }
            count = count + (curNum == num? 1: -1);
        }

        return curNum;
    }
}