/**
 * 官方题解，和15题的3数之和同一思路.
 * O(n^3), S(n * log_n)
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }
}

// /**
//  * 全排列，用dfs的思路
//  * O(n * n!)，时间复杂度太高
//  * 还是用官方题解吧
//  */
// class Solution {
//     public List<List<Integer>> fourSum(int[] nums, int target) {
//         Set<List<Integer>> res = new HashSet<>();
//         if(nums == null || nums.length == 0){
//             return new ArrayList<List<Integer>>(res);
//         }
//         // Arrays.sort(nums);
//         dfs(nums, res, new boolean[nums.length], 0, new ArrayList<Integer>(), target);
//         return new ArrayList<List<Integer>>(res);
//     }

//     private void dfs(int[] nums, Set<List<Integer>> res, boolean[] used, int depth, List<Integer> path, int target){
//         if(depth == 4){
//             int sum = 0;
//             for(int num: path){
//                 sum += num;
//             }
//             if(sum == target){
//                 List<Integer> tempPath = new ArrayList(path);
//                 Collections.sort(tempPath);
//                 res.add(tempPath);
//             }
//             return;
//         }
//         for(int i = 0; i < nums.length; i++){
//             if(used[i]){
//                 continue;
//             }
//             path.add(nums[i]);
//             used[i] = true;
//             dfs(nums, res, used, depth + 1, path, target);
//             path.remove(path.size() - 1);
//             used[i] = false;
//         }
//     }
// }