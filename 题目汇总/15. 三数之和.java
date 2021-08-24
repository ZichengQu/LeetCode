class Solution {
    public List<List<Integer>> threeSum(int[] nums) {// 总时间复杂度：O(n^2)
        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return res;
        }

        Arrays.sort(nums); // O(nlogn) 必须先排序，否则本题中没法用双指针左右夹逼

        for (int i = 0; i < nums.length - 2; i++) { // O(n^2)
            int left = i + 1;
            int right = nums.length - 1;

            if (nums[i] > 0 || nums[i] + nums[left] > 0) {
                break; // 第一个数大于 0，后面的数都比它大，肯定不成立了. 同理，下标为i和left的两个数大于0，right的数肯定大于0
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去掉重复情况
            }

            while (left < right) {
                if (nums[left] + nums[right] == -nums[i]) {

                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3],
                    // i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    left++;
                    right--; // 首先无论如何先要进行加减操作
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < -nums[i]) {
                    left++;
                } else { // nums[left] + nums[right] > target
                    right--;
                }
            }
        }

        return res;
    }
}