/**
 * 自认为的最佳解法
 * 本解法不是169题的专属解法，两题略有不同，因此将这个解法放到了这里。
 * 题目链接：https://leetcode-cn.com/problems/find-majority-element-lcci/
 * 解法链接：https://leetcode-cn.com/problems/find-majority-element-lcci/solution/zhu-yao-yuan-su-by-leetcode-solution-xr1p/
 * O(n), S(1)
 */
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = -1;
        int count = 0;
        // for(int num: nums){
        //     if(count == 0){
        //         candidate = num;
        //         count++;
        //     }else if(candidate == num){
        //         count++;
        //     }else if(candidate != num){
        //         count--;
        //     }
        // }
        for(int num: nums){
            if(count == 0){
                candidate = num;
            }
            count = count + (candidate == num? 1: -1);
        }
        count = 0;
        for(int num: nums){
            if(candidate == num){
                count++;
            }
        }
        return count > nums.length / 2? candidate: -1;
    }
}

/**
 * 排序
 * O(n * log(n)), S(n * log(n))
 */
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}

/**
 * 哈希表
 * O(n), S(n)
 */
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> hashMap = new HashMap<>(); // 使用HashMap存储nums数组里的数字和次数
        for(int num: nums){
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        int maxCount = 0;
        int num = Integer.MIN_VALUE;
        Set<Map.Entry<Integer, Integer>> entrySet = hashMap.entrySet();
        for(Map.Entry<Integer, Integer> entry: entrySet){
            if(entry.getValue() > maxCount){
                maxCount = entry.getValue();
                num = entry.getKey();
            }
        }
        return num;
    }
}

/**
 * 随机法
 * 最大的时间复杂度可能是无穷，但是根据期望，每选两次，就能选中一次"多数元素"，则O(n), S(1)
 */
class Solution {
    public int majorityElement(int[] nums) {
        Random rand = new Random();
        while(true){
            int random = rand.nextInt(nums.length);
            int choose = nums[random];
            if(countOccurrence(nums, choose) > nums.length / 2){
                return choose;
            }
        }
    }
    private int countOccurrence(int[] nums, int choose){
        int count = 0;
        for(int num: nums){
            if(num == choose){
                count++;
            }
        }
        return count;
    }
}


