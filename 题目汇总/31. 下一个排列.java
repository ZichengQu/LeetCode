/**
 * 官解1：https://leetcode-cn.com/leetbook/read/leetcamp/r361b2/ （力扣第一期算法集训营）
 * 官解2：https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/
 * O(n), S(1)
 */
class Solution {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if(len <= 1){ // O(1)
            return; // 长度小于等于1，则永远都只有一种组合
        }
        
        // 1. 一堆数字，找到某个组合，是大于这个组合的所有组合中的最小的那个组合。
        // 2. 因此从后向前遍历，找到某一个数(下标i)，比后面这个数小。O(n)
        // 3. 则从这个数之后找所有比这个数大的数中最小的那个数(下标j)。O(n)
        // 4. 将这两个数(下标i和j)替换。O(1)
        // 5. 然后可以发现下标i之后的数，都是降序排列的。因为步骤2，找到第一个符合条件的，其下标为i，因此下标i之后的数，都大于其后一个数，因此是降序。哪怕步骤4中有额外替换操作。
        // 6. 将下标为i之后的降序序列调整成升序，则可达到题目要求。O(n)
        int i = len - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) { // 步骤2
            i--;
        }

        if (i >= 0) { // 步骤3
            int j = len - 1;
            while(j > i){
                if(nums[j] > nums[i]){ // 因为是降序，从后往前，第一个比下标i的数大的，就符合min(nums[j] > nums[i])。
                    break;
                }
                j--;
            }
            swap(nums, i, j); // 步骤4
        }

        increaseSort(nums, i + 1, len - 1); // 步骤6, 不是普通的升序排列，是将降序改为升序，O(n)
    }

    private void swap(int[] nums, int index1, int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    private void increaseSort(int[] nums, int from, int end){ // from 和 end 都是下标
        while(from < end){ // 步骤6
            swap(nums, from, end);
            from++;
            end--;
        }
    }
}