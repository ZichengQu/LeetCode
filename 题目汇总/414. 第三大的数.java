/**
 * O(n * log(n)), S(1) -- > 因为TreeSet中只会存三个数
 */
class Solution {
    public int thirdMax(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>(); // 必须用TreeSet来声明，不能用Set，因为Set中没有first()、last()或pollFirst()等相关方法
        for(int num: nums){
            // if(treeSet.isEmpty() || treeSet.size() <= maxN || num > treeSet.first()){
            //     treeSet.add(num);
            // }
            treeSet.add(num); // 一直往里加，超出3个，就删除最小的就完事了，效率可能稍微低一些，但时间复杂度是一样的。
            while(treeSet.size() > 3){
                // treeSet.remove(treeSet.first());
                treeSet.pollFirst();
            }
        }
        return treeSet.size() >= 3? treeSet.first(): treeSet.last();
    }
}

/**
 * 官解：
 * O(n), S(1)
 * 链接：https://leetcode-cn.com/problems/third-maximum-number/solution/414-kong-jian-o1-shi-jian-onliang-chong-fang-fa-he/
 */
class Solution {
    public int thirdMax(int[] nums) {
        int firstMax = nums[0];
        long secondMax = Long.MIN_VALUE; // 要注意secondMax和thirdMax应为long。因为nums里的范围是int范围。
        long thirdMax = Long.MIN_VALUE; // 同时若只有前两个最大，无第三大元素，应保持thirdMax仍小于int的最小值，因此在值的传递中，secondMax也要为long.

        for(int num: nums){
            if(num == firstMax || num == secondMax || num == thirdMax){
                continue; // 如果存在过就跳过不看
            }
            if(num > firstMax){
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = num;
            }else if(num > secondMax){
                thirdMax = secondMax;
                secondMax = num;
            }else if(num > thirdMax){
                thirdMax = num;
            }
        }

        return thirdMax == Long.MIN_VALUE? firstMax: (int)thirdMax; // 没有第三大的元素，就返回最大值
    }
}