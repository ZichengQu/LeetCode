/**
 * O(n * 2^n), S(n)
 * 官解思路 (方法二)：https://leetcode-cn.com/problems/increasing-subsequences/solution/di-zeng-zi-xu-lie-by-leetcode-solution/
 */
class Solution {
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(0, Integer.MIN_VALUE, nums);
        return ans;
    }

    public void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() >= 2) {
                ans.add(new ArrayList<Integer>(temp));
            }
            return;
        }

        // 1. 选择当前元素的条件
        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            temp.remove(temp.size() - 1);
        }

        // 2. 不选择当前元素的条件：为避免重复，如果后续存在元素相等，就要么只选第一个，要么全选，要么全不选，避免“挑选”相等的数字，造成结果的重复
        if (nums[cur] != last) {
            dfs(cur + 1, last, nums);
        }
    }
}

/**
 * 自己的思路
 * O(n * 2^n), S(n * 2^n)
 */
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<>(); // 为了去重
        // List<List<Integer>> res = new LinkedList<>();

        dfsBackTrack(nums, 0, new LinkedList<>(), res);

        return new LinkedList(res);
    }

    private void dfsBackTrack(int[] nums, int depth, List<Integer> subList, Set<List<Integer>> res){
        if(nums.length == depth){
            if(subList.size() >= 2){
                res.add(new LinkedList<>(subList));
            }
            return;
        }

        // 不选当前数
        dfsBackTrack(nums, depth + 1, subList, res);

        // 选择当前数
        if(subList.size() == 0 || subList.get(subList.size() - 1) <= nums[depth]){
            subList.add(nums[depth]);
            dfsBackTrack(nums, depth + 1, subList, res);
            subList.remove(subList.size() - 1);
        }
    }
}
