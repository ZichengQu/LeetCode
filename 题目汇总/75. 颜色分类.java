/**
 * O(n), S(1)
 * 
 * 官解链接：https://leetcode-cn.com/problems/sort-colors/solution/yan-se-fen-lei-by-leetcode-solution/
 * 可以考虑使用指针 p0 ​ 来交换 0，p2 ​ 来交换 2。此时，p0 ​ 的初始值仍然为 0，而 p2​ 的初始值为 n−1。
 * 在遍历的过程中，我们需要找出所有的 0 交换至数组的头部，并且找出所有的 2 交换至数组的尾部。 
 * 由于此时其中一个指针 p2 ​是从右向左移动的，因此当我们在从左向右遍历整个数组时，如果遍历到的位置超过了 p2，那么就可以直接停止遍历了。 
 * 具体地，我们从左向右遍历整个数组，设当前遍历到的位置为 i，对应的元素为 nums[i]； 
 * 如果找到了 0，那么与前面两种方法类似，将其与 nums[p0] 进行交换，并将 p0 ​向后移动一个位置； 
 * 如果找到了 2，那么将其与 [p2] 进行交换，并将 p2 ​向前移动一个位置。 
 * 这样做是正确的吗？可以发现，对于第二种情况，当我们将 nums[i] 与 nums[p2] 进行交换之后，
 * 新的 nums[i] 可能仍然是 2，也可能是 0。
 * 然而此时我们已经结束了交换，开始遍历下一个元素 nums[i+1]，不会再考虑 nums[i] 了，这样我们就会得到错误的答案。 
 * 因此，当我们找到 2 时，我们需要不断地将其与 nums[p2] 进行交换，直到新的 nums[i] 不为 2。
 * 此时，如果 nums[i] 为 0，那么对应着第一种情况；
 * 如果 nums[i] 为 1，那么就不需要进行任何后续的操作。
 */

class Solution {
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        for(int i = 0; i < nums.length; i++){
            while(nums[i] == 2 && i < right){
                nums[i] ^= nums[right];
                nums[right] ^= nums[i];
                nums[i] ^= nums[right];
                right--;
            }
            if(nums[i] == 0){
                nums[i] ^= nums[left];
                nums[left] ^= nums[i];
                nums[i] ^= nums[left];
                left++;
            }
        }
    }
}