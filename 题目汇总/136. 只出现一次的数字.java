/**
 * 异或思想 (官解)
 * 原因：异或满足交换律和结合律
 * O(n), S(1)
 * https://leetcode-cn.com/problems/single-number/solution/zhi-chu-xian-yi-ci-de-shu-zi-by-leetcode-solution/
 */
class Solution {
    public int singleNumber(int[] nums) {
        int single = 0; // 0和任何数的异或都为任何数本身，因此初始化为0。
        for(int num: nums){
            single ^= num; // 任何数和0取异或都是本身，任何数和本身取异或都是0. 因此两两的会变成0，只有一个出现一次的，会和0异或，并保留下来。
        }
        return single;
    }
}

/**
 * O(n), S(n)
 */
class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> single = new HashSet<>(); //利用HashSet，没出现过，添加，出现过，删除。最后只会留下出现奇数次的元素。根据题意，奇数次只能为1，并有且只有一个.
        for(int num: nums){
            if(single.contains(num)){
                single.remove(num);
            }else{
                single.add(num);
            }
        }
        int res = 0;
        for(int num: single){
            res = num; // 根据题意，这个set中肯定有且只有一个元素
        }
        return res;
    }
}