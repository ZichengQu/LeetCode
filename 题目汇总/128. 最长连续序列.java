/**
 * O(n * log_n) S(n), 不推荐这个方法，没有下面的方法好
 */
// class Solution {
//     public int longestConsecutive(int[] nums) {
//         if(nums == null || nums.length == 0){
//             return 0;
//         }
//         int len = 1;
//         int res = 1;

//         TreeSet<Integer> treeSet = new TreeSet<>(); // 这里必须声明TreeSet，不能是Set。因为向上造型，能点出来什么得看类型，怎么使用看实现类。Set中没有first()、pollFirst()等方法。
//         for(int num: nums){ // O(n * log_n)
//             treeSet.add(num); // 有序，不重复。// 每插入一次都是O(log_n)
//         }
        
//         int first = treeSet.first() != null? treeSet.pollFirst(): Integer.MIN_VALUE;
//         while(!treeSet.isEmpty()){
//             int second = treeSet.first() != null? treeSet.pollFirst(): Integer.MIN_VALUE;
//             if(first != Integer.MIN_VALUE && second != Integer.MIN_VALUE && second - first == 1){
//                 len++; // 当前序列满足条件时
//             }else{
//                 len = 1; // 不满足时，重置
//             }
//             first = second; // 依次替换
//             res = Math.max(res, len);
//         }
//         return res;
//     }
// }

/**
 * O(n), S(n)
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        int res = 0;
        Set<Integer> hashSet = new HashSet<>();
        for(int num: nums){
            hashSet.add(num); // 去重
        }
        /*
         * 相当于选取全部的 "开头数字"，遍历以每个开头数字所在的子序列，计算所有子序列的长度。
         * 因此"开头数字" + "子序列元素" = nums.length
         * 因此这个for循环及其中的while循环的时间复杂度为 O(n)
         */
        for(int num: hashSet){ // 遍历该Set的所有元素
            if(hashSet.contains(num - 1)){
                continue; // 若是该num有其前置数字，则不考虑该num
            }
            int next = num + 1; // 若是该num无前置数字，则说明该num是某一个子序列的开头数字
            int length = 1; // 初始化或重置当前长度为1，因为该子序列至少有个开头数字，因此length至少为1
            while(hashSet.contains(next)){ // 如果该num的下一个数字存在
                next ++; // 则继续更新
                length ++; // 刷新当前子序列的长度
            }
            res = Math.max(res, length);
        }
        return res;
    }
}